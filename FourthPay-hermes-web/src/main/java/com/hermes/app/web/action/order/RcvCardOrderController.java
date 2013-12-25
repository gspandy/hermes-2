package com.hermes.app.web.action.order;


import com.hermes.app.domain.order.ExportRcvcardOrder;
import com.hermes.app.domain.order.RcvcardOrder;
import com.hermes.app.form.card.CardForm;
import com.hermes.app.form.card.CardResult;
import com.hermes.app.form.order.RcvCardOrderForm;
import com.hermes.app.service.order.OrderService;
import com.hermes.app.util.XmlStringParse;
import com.hermes.app.util.order.ExportExcel;
import com.hermes.app.util.order.StateCode;
import com.hermes.app.web.action.BaseController;
import com.hermes.app.web.util.DateUtil;
import com.hermes.app.web.util.PaginationSupport;
import com.hermes.app.web.util.RequestUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 收卡订单controller
 *
 * @author tujianying/of821
 */
@Controller
@RequestMapping("/order")
public class RcvCardOrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    /**
     * 访问提现订单明细
     *
     * @return 登录页面
     */
    @RequestMapping(value = "/cardorder", method = RequestMethod.GET)
    public String index() {
        return "order/cardorder-index";
    }

    /**
     * ajax url
     */
    @RequestMapping(value = "/cardorder/list", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> list(RcvCardOrderForm form, HttpServletRequest request) {
        form.setSortInfo(request);
        form.setUsercode(getSessionUserCode(request));
        String key = form.getKeywordQuery();
        String keyValue = form.getKeywordQueryValue();
        if(null != keyValue && !"".equals(keyValue.trim())){
            if("1".equals(key)){
                form.setCardno(keyValue);
            }
            else if("2".equals(key)){
                form.setMerorderno(keyValue);
            }
            else if("3".equals(key)){
                form.setBillid(keyValue);
            }
        }
        logger.debug("Action.URL={},param={}", RequestUtil.getRestURL(request), form.toString());

        //修改时间，加上时:分:秒
        String startTag = " 00:00:00";
        String endTag = " 23:59:59";
        form.setStartTime(form.getStartTime() + startTag);
        form.setEndTime(form.getEndTime() + endTag);

        //赋值states，界面上传过来的state的订单状态值，只是界面上简单的三个值：成功（1），失败（2），充值中（0）
        String state = form.getState();
        if(null != state && !"".equals(state.trim())){
            form.setStates(StateCode.generalStateList(state));
        }

        // 查询列表
        PaginationSupport ps = orderService.queryRcvCardOrders(form);

        Map<String, Object> dataMap = dataTableJson(ps.getTotalCount(), ps.getItems());
        return dataMap;
    }

    /**
     * 导出excel表格
     */
    @RequestMapping(value = "/cardorder/export")
    public
    void export(RcvCardOrderForm form, HttpServletRequest request, HttpServletResponse response) {
        form.setSortInfo(request);
        form.setUsercode(getSessionUserCode(request));
        String key = form.getKeywordQuery();
        String keyValue = form.getKeywordQueryValue();
        if(null != keyValue && !"".equals(keyValue.trim())){
            if("1".equals(key)){
                form.setCardno(keyValue);
            }
            else if("2".equals(key)){
                form.setMerorderno(keyValue);
            }
            else if("3".equals(key)){
                form.setBillid(keyValue);
            }
        }
        logger.debug("Action.URL={},param={}", RequestUtil.getRestURL(request), form.toString());

        //修改时间，加上时:分:秒
        String startTag = " 00:00:00";
        String endTag = " 23:59:59";
        form.setStartTime(form.getStartTime() + startTag);
        form.setEndTime(form.getEndTime() + endTag);

        //赋值states，界面上传过来的state的订单状态值，只是界面上简单的三个值：成功（1），失败（2），充值中（0）
        String state = form.getState();
        if(null != state && !"".equals(state.trim())){
            form.setStates(StateCode.generalStateList(state));
        }

        // 查询列表，excel表格中的body信息
        List<ExportRcvcardOrder> exports = translate2ExportInfo(orderService.queryExportRcvCardOrders(form));

        //excel表格中的头信息
        String[] headers = new String[]{"商户订单号", "殴飞流水号", "充值卡号", "申明面值", "结算面值",
                "手续费", "账户余额", "运营商", "支付状态", "订单时间（用户交易时间）"};

        String fileName = form.getUsercode() + "_" + DateUtil.getDateString(new Date(), "yyyy-MM-dd")+ ".xls";

        OutputStream out = null;
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("content-disposition", "attachment;filename=\""
                    + new String(fileName.getBytes(), response.getCharacterEncoding()) + "\"");
            out = response.getOutputStream();
            ExportExcel<ExportRcvcardOrder> ex = new ExportExcel<ExportRcvcardOrder>();
            ex.exportExcel("收卡订单信息", headers, exports, out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(null != out){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 将获取到的RcvcardOrder对象转化为需要export为excel的对象
     * @param list
     * @return
     */
    private List<ExportRcvcardOrder> translate2ExportInfo(List<RcvcardOrder> list){
        List<ExportRcvcardOrder> exports = new ArrayList<ExportRcvcardOrder>();

        if(null != list && list.size() != 0){
            for(RcvcardOrder rcvcardOrder : list){
                ExportRcvcardOrder export = new ExportRcvcardOrder(rcvcardOrder.getMerorderno(), rcvcardOrder.getBillid(),
                        rcvcardOrder.getCardno(), rcvcardOrder.getFaceval(), rcvcardOrder.getRealval(),
                        rcvcardOrder.getMercommission(), rcvcardOrder.getMerleftmoney(), rcvcardOrder.getCardname(),
                        rcvcardOrder.getRetresultmsg(), rcvcardOrder.getOrdertime());
                exports.add(export);
            }
        }
        return exports;
    }

    /**
     * 回调结果
     */
    @RequestMapping(value = "/cardorder/callback")
    public @ResponseBody Map<String, Object> callback(@RequestParam String billid, HttpServletRequest request,
                         HttpServletResponse response, Model model) {
        CardResult cardResult = null;

        try {
            String usercode = getSessionUserCode(request);
            // 提交卡密
            cardResult = orderService.cardCallbackMerchant(usercode, billid);
            logger.info(cardResult.getInfo());

            return  data2json(cardResult);
        } catch (Exception e) {
            cardResult = new CardResult();
            cardResult.setResult(FAIL);
            cardResult.setInfo("回调异常");
            return data2json(cardResult);
        }
    }
}
