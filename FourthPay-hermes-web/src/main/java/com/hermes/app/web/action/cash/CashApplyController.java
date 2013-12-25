package com.hermes.app.web.action.cash;


import com.hermes.app.domain.cash.Bind;
import com.hermes.app.domain.cash.UserCount;
import com.hermes.app.domain.user.User;
import com.hermes.app.exception.BussinessExceptionBuilder;
import com.hermes.app.form.cash.ApplyForm;
import com.hermes.app.form.cash.BindForm;
import com.hermes.app.service.cash.CashService;
import com.hermes.app.service.user.UserService;
import com.hermes.app.web.action.BaseController;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 申请提现controller
 *
 * @author of644
 */
@Controller
@RequestMapping("/cash")
public class CashApplyController extends BaseController {

    @Autowired
    private CashService cashService;

    @Autowired
    private UserService userService;

    /**
     * 访问申请提现
     *
     * @param request
     * @param model
     * @return 页面
     */
    @RequestMapping(value = "/apply", method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model) {
        String usercode = getSessionUserCode(request);
        // 用户余额
        UserCount userCount = cashService.queryUserCountByCode(usercode);

        // 查询用户可提现的银行卡信息
        BindForm bindForm = new BindForm();
        bindForm.setUsercode(usercode);
        bindForm.setDatastat("2");//审核通过
        List<Bind> binds = cashService.queryBinds(bindForm);

        model.addAttribute("merleftmoney", StringUtils.defaultIfEmpty(userCount.getMerleftmoney(), "0.00"));
        model.addAttribute("binds", binds);
        return "cash/apply/apply-index";
    }

    /**
     * 申请提现
     *
     * @param applyForm
     * @param result
     * @param request
     * @return
     */
    @RequestMapping(value = "/apply/new", method = RequestMethod.POST)
    public String apply(@Valid ApplyForm applyForm, BindingResult result, HttpServletRequest request) {
        // 验证输入参数
        if (result.hasErrors()) {
            FieldError fes = result.getFieldError();
            throw BussinessExceptionBuilder.aBussinessException()
                    .withErrMsg(fes.getDefaultMessage())
                    .withForm(applyForm)
                    .withRedirecturl("/cash/apply")
                    .build();
        }
        // 用户编码
        String usercode = getSessionUserCode(request);
        // 1.用户信息
        User user = userService.queryByCode(usercode);

        // 2.银行信息
        BindForm bindForm = new BindForm();
        bindForm.setUsercode(user.getUsercode());
        bindForm.setAccountflowid(applyForm.getAccountflowid());
        List<Bind> bindBanks = cashService.queryBinds(bindForm);

        // 绑定银行信息是否存在
        if (CollectionUtils.isEmpty(bindBanks) || bindBanks.size() < 1) {
            throw BussinessExceptionBuilder.aBussinessException()
                    .withErrMsg("没有绑定的银行信息")
                    .withForm(applyForm)
                    .withRedirecturl("/cash/apply")
                    .build();
        }

        // 用户余额
        UserCount userCount = cashService.queryUserCountByCode(usercode);
        String leftMoney = userCount.getMerleftmoney();
        if (StringUtils.isEmpty(leftMoney) || "0".equals(leftMoney)) {
            throw BussinessExceptionBuilder.aBussinessException()
                    .withErrMsg("用户余额不足，不能提现")
                    .withForm(applyForm)
                    .withRedirecturl("/cash/apply")
                    .build();
        }
        applyForm.setMerleftmoney(leftMoney);

        // 绑定参数
        bindParames(applyForm, user, bindBanks.get(0));
        // 验证参数
        validParams(applyForm, user);

        // 申请提现金额
        BigDecimal applyMoney = NumberUtils.createBigDecimal(applyForm.getApplyMoney());
        BigDecimal rate = cashService.queryRate(applyForm);

        // 特殊逻辑（快钱不拆单）
        if ("A771813".equals(usercode)) {
            calcAndBindMoney(applyForm, usercode, rate, applyMoney);
            cashService.applyCash(applyForm);
        }
        // 拆单
        else {
            // 拆分订单，大于50000
            List<BigDecimal> splitMoneyList = cashService.getSplitMoneyList(applyForm.getApplyMoney(), ApplyForm.SPLIT);

            // 如果金额大于5W的则分批生成提现单；每批单独减动手续费；
            if (!splitMoneyList.isEmpty() && splitMoneyList.size() > 0) {
                for (BigDecimal splitMoney : splitMoneyList) {
                    calcAndBindMoney(applyForm, usercode, rate, splitMoney);
                    cashService.applyCash(applyForm);
                }
            }
        }
        return "cash/apply/apply-success";
    }

    /**
     * 异步计算商户手续费
     *
     * @return
     */
    @RequestMapping(value = "/apply/rate/", method = RequestMethod.GET)
    public
    @ResponseBody
    String rate(@RequestParam("bankcode") String bankcode,
                @RequestParam("applyMoney") BigDecimal applyMoney,
                HttpServletRequest request) {
        ApplyForm applyForm = new ApplyForm();
        applyForm.setUsercode(getSessionUserCode(request));
        applyForm.setBankcode(bankcode);
        // 1.获取商户提现费率
        BigDecimal rate = cashService.queryRate(applyForm);
        // 2.根据申请金额和费率计算手续费
        BigDecimal commision = cashService.getComision(applyMoney, rate);

        return commision.toString();
    }

    /**
     * 绑定参数
     * @param applyForm
     * @param user
     * @param bindBank
     */
    private void bindParames(ApplyForm applyForm, User user, Bind bindBank) {
        // 用户信息
        applyForm.setUsercode(user.getUsercode());
        applyForm.setUsername(user.getUsername());
        // 银行信息
        applyForm.setBankcode(bindBank.getBankcode());
        applyForm.setBankname(bindBank.getBankname());
        applyForm.setAccountusername(bindBank.getAccountusername());
        applyForm.setAccountno(bindBank.getAccountno());
        applyForm.setAccshortname(bindBank.getAccshortname());
        // 1：申请冻结资金提现 2：申请冻结资金转信用点
        applyForm.setApplytype("1");
        // 页面提现全部自动审核
        applyForm.setAutoAudit(true);
        // 1：正常订单 2：审核通过 3：审核不通过
        applyForm.setDatastat("2");
        // 0：未处理1:已发送交易2：交易成功3：交易失败
        applyForm.setDealstat("0");
        // 订单来源 9：第四方商户
        applyForm.setOrdersource("9");
        // 操作信息
        applyForm.setApprovemanname("system");
        applyForm.setApproveinfo(user.getUsercode() + "提现系统自动审核");

    }
    /**
     * 验证参数
     * @param applyForm
     * @param user
     * @return
     */
    private void validParams(ApplyForm applyForm, User user) {
        // 验证用户是否被锁定
        if ("0".equals(user.getLocked())) {
            throw BussinessExceptionBuilder.aBussinessException()
                    .withErrMsg("该账户被锁定！不能提现")
                    .withForm(applyForm)
                    .withRedirecturl("/cash/apply")
                    .build();
        }

        // 验证用户提现密码
        if (!DigestUtils.md5Hex(applyForm.getCashpwd()).equals(user.getPaypws())) {
            throw BussinessExceptionBuilder.aBussinessException()
                    .withErrMsg("输入的提现密码不正确")
                    .withForm(applyForm)
                    .withRedirecturl("/cash/apply")
                    .build();
        }
        // 验证账户余额
        if (validApplyMoney(applyForm)){
            throw BussinessExceptionBuilder.aBussinessException()
                    .withErrMsg("账户余额不足")
                    .withForm(applyForm)
                    .withRedirecturl("/cash/apply")
                    .build();
        }
    }

    /**
     * 验证提现金额是否小于用户余额
     *
     * @param applyForm
     * @return
     */
    private boolean validApplyMoney(ApplyForm applyForm) {
        // 申请提现金额
        BigDecimal applyMoney = NumberUtils.createBigDecimal(applyForm.getApplyMoney());
        BigDecimal rate = cashService.queryRate(applyForm);
        BigDecimal commision = cashService.getComision(applyMoney, rate);
        BigDecimal merleftmoney = NumberUtils.createBigDecimal(applyForm.getMerleftmoney());
        // 检查账户余额
        return (merleftmoney.subtract(applyMoney.add(commision))).floatValue() < 0;
    }

    /**
     * 计算并且绑定金额（手续费，提现金额，提现后金额etc..）
     *
     * @param applyForm
     * @param usercode
     * @param rate
     * @param applyMoney
     */
    private void calcAndBindMoney(ApplyForm applyForm, String usercode, BigDecimal rate, BigDecimal applyMoney) {
        // 每笔都要计算手续费
        BigDecimal commision = cashService.getComision(applyMoney, rate);

        // 查询银行信息
        List<Map> bankCodes = cashService.queryBankCodes(applyForm.getBankcode());
        // 银行类型：1：银行2：第三方账户
        if (CollectionUtils.isNotEmpty(bankCodes)) {
            applyForm.setAccounttype((bankCodes.get(0).get("BANKTYPE")).toString());
        }
        // 用户余额
        BigDecimal merleftmoney = NumberUtils.createBigDecimal(cashService.queryUserCountByCode(usercode).getMerleftmoney());
        // 需扣除金额
        BigDecimal deduMoney = applyMoney.add(commision);
        // 帐户当前交易剩余金额
        BigDecimal merleftmoneyafter = merleftmoney.subtract(deduMoney);

        // 先从支付账户中减去支付金额和手续费
        applyForm.setDeduMoney(deduMoney.toString());
        applyForm.setApplyMoney(applyMoney.toString());
        applyForm.setCommision(commision.toString());
        applyForm.setFeerate(rate.toString());
        applyForm.setMerleftmoney(merleftmoney.toString());
        applyForm.setMerleftmoneyafter(merleftmoneyafter.toString());
    }
}
