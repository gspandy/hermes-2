package com.hermes.app.web.action.card;


import com.hermes.app.domain.card.Category;
import com.hermes.app.domain.order.RcvcardOrder;
import com.hermes.app.form.card.CardForm;
import com.hermes.app.form.card.CardResult;
import com.hermes.app.form.order.RcvCardOrderForm;
import com.hermes.app.service.card.CardService;
import com.hermes.app.service.order.OrderService;
import com.hermes.app.util.AES;
import com.hermes.app.web.action.BaseController;
import com.hermes.app.web.util.DateUtil;
import com.hermes.app.web.util.PaginationSupport;
import com.ofpay.rex.captcha.CaptchaControlHelp;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 卡密提交controller
 *
 * @author of644
 */
@Controller
@RequestMapping("/card")
public class CardController extends BaseController {

    @Autowired
    private CardService cardService;

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/single", method = RequestMethod.GET)
    public String single() {
        return "card/card-index";
    }

    @RequestMapping(value = "/batch", method = RequestMethod.GET)
    public String batch() {
        return "card/cards-index";
    }

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public
    @ResponseBody
    List initCategory() {
        List<Category> categories = cardService.queryCategory();
        return categories;
    }

    @RequestMapping(value = "/category/{corpcode}", method = RequestMethod.GET)
    public
    @ResponseBody
    List init(@PathVariable String corpcode) {
        List<Category> categories = cardService.queryFaceValue(corpcode);
        return categories;
    }

    /**
     * 单张提交卡密
     *
     * @param cardForm
     * @param result
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/postCard", method = RequestMethod.POST)
    public
    @ResponseBody
    Map postCard(@Valid CardForm cardForm, BindingResult result, HttpServletRequest request, HttpSession session) {
        CardResult cardResult = new CardResult();
        try {
            //
            if (!validPostCard(cardForm, result, session, cardResult)) {
                return data2json(cardResult);
            }

            String usercode = getSessionUserCode(request);
            cardForm.setUsercode(usercode);
            // md5
            cardForm.setMd5key(cardService.queryMd5key(usercode));
            // 提交卡密
            cardResult = cardService.postCard(cardForm);
            cardResult.setInfo("卡号：" + cardForm.getCardno() + ">>>" + cardResult.getInfo());
            logger.info(cardResult.getInfo());
            return data2json(cardResult);
        } catch (Exception e) {
            cardResult.setResult(FAIL);
            cardResult.setInfo("提交卡密异常");
            return data2json(cardResult);
        }
    }

    /**
     * 再一次单张提交卡密（用于订单管理中的再一次提交卡信息）
     *
     * @param billid
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/postCardAgain")
    public @ResponseBody Map<String, Object> postCardAgain(@RequestParam String billid, HttpServletRequest request, HttpSession session, Model model) {
        CardResult cardResult = null;
        CardForm cardForm = new CardForm();
        try {
            RcvcardOrder rcvcard = cardService.queryRcvcardBill(billid);
            cardForm.setCardcode(rcvcard.getCardcode());
            cardForm.setCardno(rcvcard.getCardno());
            cardForm.setCardpass(AES.Decrypt(rcvcard.getCardpass(), "cgHDu0wutwtaAXhy"));;
            String usercode = getSessionUserCode(request);
            cardForm.setUsercode(usercode);
            // md5
            cardForm.setMd5key(cardService.queryMd5key(usercode));
            // 提交卡密
            cardResult = cardService.postCard(cardForm);
            cardResult.setInfo(new StringBuilder()
                    .append("卡号：")
                    .append(cardForm.getCardno())
                    .append(">>>")
                    .append(cardResult.getInfo()).toString());
            logger.info(cardResult.getInfo());

            return  data2json(cardResult);
        } catch (Exception e) {
            cardResult = new CardResult();
            cardResult.setResult(FAIL);
            cardResult.setInfo("提交卡密异常");
            return data2json(cardResult);
        }
    }

    /**
     * 批量提交卡密
     *
     * @param cardForm
     * @param result
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/postCards", method = RequestMethod.POST)
    public
    @ResponseBody
    Map postCards(@Valid CardForm cardForm, BindingResult result, HttpServletRequest request, HttpSession session) {
        CardResult cardResult = new CardResult();
        try {
            //
            if (!validPostCards(cardForm, result, session, cardResult)) {
                return data2json(cardResult);
            }

            // 解析卡密
            String cardcontent = cardForm.getCardcontent();
            logger.info("原始串：{}", cardcontent);
            cardcontent = cardcontent.replaceAll("\n|\r\n", "|");
            logger.info("替换串：" + cardcontent);
            String contents[] = cardcontent.split("[|]");
            if (contents.length <= 0) {
                cardResult.setResult(FAIL);
                cardResult.setInfo("解析卡密失败");
                return data2json(cardResult);
            }

            ArrayList<String[]> contentsList = new ArrayList<String[]>();
            for (String content : contents) {
                String cards[] = content.trim().replaceAll("\\s{1,}", ",").split("[,]");
                contentsList.add(cards);
                if (cards.length != 2) {
                    cardResult.setResult(FAIL);
                    cardResult.setInfo("解析卡密失败");
                    return data2json(cardResult);
                }
                for (String card : cards) {
                    if (StringUtils.isEmpty(card)) {
                        cardResult.setResult(FAIL);
                        cardResult.setInfo("解析卡密失败");
                        return data2json(cardResult);
                    }
                }
            }
            // 最多提交十张卡密
            if (contentsList.size() > 10) {
                cardResult.setResult(FAIL);
                cardResult.setInfo("卡密序列超过10");
                return data2json(cardResult);
            }

            String usercode = getSessionUserCode(request);
            String md5key = cardService.queryMd5key(usercode);
            StringBuffer info = new StringBuffer();
            for (int i = 0; i < contentsList.size(); i++) {
                CardForm temForm = new CardForm();
                try {
                    temForm.setUsercode(usercode);
                    // md5
                    temForm.setMd5key(md5key);
                    temForm.setCardcode(cardForm.getCardcode());
                    temForm.setCardno(contentsList.get(i)[0].trim());
                    temForm.setCardpass(contentsList.get(i)[1].trim());
                    logger.info("卡密串:{} -- {}", contentsList.get(i)[0].trim(), contentsList.get(i)[1].trim());
                    // 提交卡密
                    cardResult = cardService.postCard(temForm);
//                    infonew StringBuilder().append("卡号：").append(temForm.getCardno()).
//                            append(" ").append(cardResult.getInfo()).append(";").toString();
                    info.append("卡号：")
                            .append(temForm.getCardno())
                            .append(">>>")
                            .append(cardResult.getInfo())
                            .append("<br />");
                } catch (Exception e) {
                    info.append("卡号：")
                            .append(temForm.getCardno())
                            .append(">>>")
                            .append(cardResult.getInfo())
                            .append(";");
                    continue;
                }
            }
            logger.info(info.toString());
            cardResult.setInfo(info.toString());
            return data2json(cardResult);
        } catch (Exception e) {
            cardResult.setResult(FAIL);
            cardResult.setInfo("提交卡密异常");
            return data2json(cardResult);
        }
    }

    /**
     * @param cardForm
     * @param result
     * @param session
     * @param cardResult
     * @return
     */
    private boolean validPostCard(CardForm cardForm, BindingResult result, HttpSession session, CardResult cardResult) {
        // form 验证
        if (result.hasErrors()) {
            cardResult.setResult(FAIL);
            FieldError fes = result.getFieldError();
            cardResult.setInfo(fes.getDefaultMessage());
            return false;
        }
        // 验证验证码
        if (!CaptchaControlHelp.checkCaptcha(cardForm.getCaptcha(), session)) {
            cardResult.setResult(FAIL);
            cardResult.setInfo("验证码不正确！请刷新验证码后重新输入");
            return false;
        }

        // 提交的卡密
        if (StringUtils.isEmpty(cardForm.getCardno()) || StringUtils.isEmpty(cardForm.getCardpass())) {
            cardResult.setResult(FAIL);
            cardResult.setInfo("请输入卡号和卡密");
            return false;
        }
        return true;
    }

    /**
     * @param cardForm
     * @param result
     * @param session
     * @param cardResult
     * @return
     */
    private boolean validPostCards(CardForm cardForm, BindingResult result, HttpSession session, CardResult cardResult) {
        // form 验证
        if (result.hasErrors()) {
            cardResult.setResult(FAIL);
            FieldError fes = result.getFieldError();
            cardResult.setInfo(fes.getDefaultMessage());
            return false;
        }
        // 验证验证码
        if (!CaptchaControlHelp.checkCaptcha(cardForm.getCaptcha(), session)) {
            cardResult.setResult(FAIL);
            cardResult.setInfo("验证码不正确！请刷新验证码后重新输入");
            return false;
        }
        // 批量提交的卡密
        if (StringUtils.isEmpty(cardForm.getCardcontent())) {
            cardResult.setResult(FAIL);
            cardResult.setInfo("请输入批量提交的卡号和卡密");
            return false;
        }
        return true;
    }

    /**
     * ajax url
     */
    @RequestMapping(value = "/order/list", method = RequestMethod.POST)
    public
    @ResponseBody
    java.util.Map<String, Object> list(RcvCardOrderForm form, HttpServletRequest request) {
        form.setSortInfo(request);
        form.setUsercode(getSessionUserCode(request));
        form.setStartTime(DateUtil.dateAdd(new Date(), -7));
        form.setEndTime(DateUtil.dateAdd(new Date(), 1));
        // 查询列表
        PaginationSupport ps = orderService.queryRcvCardOrders(form);
        return dataTableJson(ps.getTotalCount(), ps.getItems());
    }
}
