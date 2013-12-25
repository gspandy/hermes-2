package com.hermes.app.web.action.order;


import com.hermes.app.domain.order.CallbackResult;
import com.hermes.app.domain.order.*;
import com.hermes.app.form.card.CardResult;
import com.hermes.app.form.order.PayOrderForm;
import com.hermes.app.service.order.OrderService;
import com.hermes.app.util.order.ExportExcel;
import com.hermes.app.web.action.BaseController;
import com.hermes.app.web.util.DateUtil;
import com.hermes.app.web.util.PaginationSupport;
import com.hermes.app.web.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * 支付订单controller
 *
 * @author tujianying/of821
 */
@Controller
@RequestMapping("/order")
public class PayOrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    /**
     * 访问提现订单明细
     *
     * @return 登录页面
     */
    @RequestMapping(value = "/payorder", method = RequestMethod.GET)
    public String index() {
        return "order/payorder-index";
    }

    /**
     * ajax url
     */
    @RequestMapping(value = "/payorder/list", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> list(PayOrderForm form, HttpServletRequest request) {
        form.setSortInfo(request);
        form.setUsercode(getSessionUserCode(request));
        String key = form.getKeywordQuery();
        String keyValue = form.getKeywordQueryValue();

        //关键字选了什么。1：殴飞订单号  2：网银订单号 3:流水号
        if(null != keyValue && !"".equals(keyValue.trim())){
            if("1".equals(key)){
                form.setOrderno(keyValue);
            }
            else if("2".equals(key)){
                form.setBankorderno(keyValue);
            }
            else if("3".equals(key)){
                form.setTransactionid(keyValue);
            }
        }
        logger.debug("Action.URL={},param={}", RequestUtil.getRestURL(request), form.toString());

        //修改时间，加上时:分:秒
        String startTag = " 00:00:00";
        String endTag = " 23:59:59";
        form.setStartTime(form.getStartTime() + startTag);
        form.setEndTime(form.getEndTime() + endTag);

        // 查询列表
        PaginationSupport ps = orderService.queryPayOrders(form);

        Map<String, Object> dataMap = dataTableJson(ps.getTotalCount(), ps.getItems());
        return dataMap;
    }

    /**
     * ajax url
     */
    @RequestMapping(value = "/payorder/getbankcodes", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> getbankcodes(HttpServletRequest request) {
        //查询出所有的银行类型
        List<CashBankInfo> bankInfos = orderService.selectBackCodeList();
        Map<String, Object> dataMap = data2json(bankInfos);
        return dataMap;
    }

    /**
     * 导出excel表格
     */
    @RequestMapping(value = "/payorder/export")
    public
    void export(PayOrderForm form, HttpServletRequest request, HttpServletResponse response) {
        form.setSortInfo(request);
        form.setUsercode(getSessionUserCode(request));
        String key = form.getKeywordQuery();
        String keyValue = form.getKeywordQueryValue();

        //关键字选了什么。1：殴飞订单号  2：网银订单号 3:流水号
        if(null != keyValue && !"".equals(keyValue.trim())){
            if("1".equals(key)){
                form.setOrderno(keyValue);
            }
            else if("2".equals(key)){
                form.setBankorderno(keyValue);
            }
            else if("3".equals(key)){
                form.setTransactionid(keyValue);
            }
        }
        logger.debug("Action.URL={},param={}", RequestUtil.getRestURL(request), form.toString());

        //修改时间，加上时:分:秒
        String startTag = " 00:00:00";
        String endTag = " 23:59:59";
        form.setStartTime(form.getStartTime() + startTag);
        form.setEndTime(form.getEndTime() + endTag);

        // 查询列表，excel表格中的body信息
        List<ExportPayOrder> exports = translate2ExportInfo(orderService.queryExportPayOrders(form));

        //excel表格中的头信息
        String[] headers = new String[]{"商户订单号", "网银订单号", "流水号", "订单金额","手续费","分账余额","支付余额",
                "支付方式","支付状态",  "支付时间",  "退款状态",  "退款金额",  "退款时间", "订单时间（用户交易时间）"};

        String fileName = form.getUsercode() + "_" + DateUtil.getDateString(new Date(), "yyyy-MM-dd")+ ".xls";

        OutputStream out = null;
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("content-disposition", "attachment;filename=\""
                    + new String(fileName.getBytes(), response.getCharacterEncoding()) + "\"");
            out = response.getOutputStream();
            ExportExcel<ExportPayOrder> ex = new ExportExcel<ExportPayOrder>();
            ex.exportExcel("支付订单信息", headers, exports, out);
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
    private List<ExportPayOrder> translate2ExportInfo(List<PayOrder> list){
        List<ExportPayOrder> exports = new ArrayList<ExportPayOrder>();

        if(null != list && list.size() != 0){
            for(PayOrder payOrder : list){
                ExportPayOrder export = new ExportPayOrder(payOrder.getOrderno(), payOrder.getBankorderno(),
                        payOrder.getTransactionid(), payOrder.getOrdermoney(),
                        payOrder.getCommission(), payOrder.getMerchantretmoney(), payOrder.getMerleftmoney(),
                        payOrder.getPaymethodname(), payOrder.getDealstat(), payOrder.getDealtime(), payOrder.getRefundstate(),
                        payOrder.getRefundamount(), payOrder.getRefundopttime(), payOrder.getOrdertime());

                exports.add(export);
            }
        }
        return exports;
    }

    /**
     * 回调结果
     */
    @RequestMapping(value = "/payorder/callback")
    public @ResponseBody Map<String, Object> callback(@RequestParam String billid, HttpServletRequest request, HttpServletResponse response,
                           Model model) {
        CallbackResult retMsg;
        CardResult cardResult = new CardResult();
        try {
            retMsg = orderService.callbackMerchant(billid, getSessionUserCode(request));

            if(null == retMsg){
                cardResult.setResult(FAIL);
                cardResult.setInfo("回调异常");
                return data2json(cardResult);
            }

            cardResult.setResult(retMsg.getResult());
            cardResult.setInfo(retMsg.getMsg());
            return data2json(cardResult);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("商户回调请求失败");
            cardResult.setResult(FAIL);
            cardResult.setInfo("回调异常");
            return data2json(cardResult);
        }
    }
}

