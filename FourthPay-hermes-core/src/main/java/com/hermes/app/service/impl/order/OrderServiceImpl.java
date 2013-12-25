package com.hermes.app.service.impl.order;


import com.hermes.app.domain.order.*;
import com.hermes.app.form.card.CardResult;
import com.hermes.app.form.order.DataAnalyseForm;
import com.hermes.app.form.order.PayOrderForm;
import com.hermes.app.form.order.RcvCardOrderForm;
import com.hermes.app.persistence.pay.order.CardOrderMapper;
import com.hermes.app.persistence.pay.order.DataAnalyseMapper;
import com.hermes.app.persistence.pay.order.PayOrderMapper;
import com.hermes.app.persistence.pay.user.PayaccountsecInfoMapper;
import com.hermes.app.service.order.OrderService;
import com.hermes.app.util.http.HttpClientSupport;
import com.hermes.app.util.order.ErrorCodeMap;
import com.hermes.app.web.util.PaginationSupport;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 提现相关的service
 *
 * @author of644
 *
 */
@Service
public class OrderServiceImpl implements OrderService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CardOrderMapper cardOrderMapper;
    @Autowired
    private PayOrderMapper payOrderMapper;

    @Autowired
    private DataAnalyseMapper dataAnalyseMapper;

    @Autowired
    private PayaccountsecInfoMapper payaccountsecInfoMapper;

    @Autowired
    private HttpClientSupport httpClientSupport;

    @Value("${card.callback.url}")
    private String cardurl;

    @Value("${payapi.bossinterface}")
    private String payapiurl;

    @Override
    public PaginationSupport<RcvcardOrder> queryRcvCardOrders(RcvCardOrderForm rcvCardOrderForm) {
        List<RcvcardOrder> list = addMsg2List(cardOrderMapper.selectRcvCardOrders(rcvCardOrderForm));
        int totalCount = cardOrderMapper.countRcvCardOrders(rcvCardOrderForm);
        return new PaginationSupport(list, totalCount, rcvCardOrderForm.getiDisplayLength(), rcvCardOrderForm.getiDisplayStart());
    }

    @Override
    public List<RcvcardOrder> queryExportRcvCardOrders(RcvCardOrderForm rcvCardOrderForm){
        return addMsg2List(cardOrderMapper.queryExportRcvCardOrders(rcvCardOrderForm));
    }

    @Override
    public PaginationSupport<DataAnalyseStatic> queryRcvCardStatic(DataAnalyseForm dataAnalyseForm) {
        List<DataAnalyseStatic> list = dataAnalyseMapper.queryRcvCardStatic(dataAnalyseForm);
        int totalCount = dataAnalyseMapper.countRcvCardStatic(dataAnalyseForm);
        return new PaginationSupport(list, totalCount, dataAnalyseForm.getiDisplayLength(), dataAnalyseForm.getiDisplayStart());
    }

    /**
     * 添加支付描述信息到对象中
     * @return
     */
    private List<RcvcardOrder> addMsg2List(List<RcvcardOrder> list){
        if(null == list || list.size() == 0){
            return list;
        }

        for(RcvcardOrder rcvcardOrder: list){
            String msg =getRetResultMsg(rcvcardOrder.getRetresult(),rcvcardOrder.getRealval());
            rcvcardOrder.setRetresultmsg(msg);
        }

        return list;
    }

    private String getRetResultMsg(String retresult,Float retvalue) {
        if (retresult == null || retresult.equals("")) {
            retresult = "0";
        }
        return ErrorCodeMap.getErrorMsgs(Integer.parseInt(retresult), retvalue);
    }

    /**
     * 查询用户的密钥
     * @param usercode  用户编号
     * @return
     */
    @Override
    public String queryUserMd5key(String usercode){
        return payaccountsecInfoMapper.selectUserMd5key(usercode);
    }

    /**
     * 查询出所有的银行信息
     *
     * @return
     */
    public List<CashBankInfo> selectBackCodeList(){
        return payOrderMapper.selectBackCodeList();
    }

    /**
     * 查询支付订单，包含界面分页信息，取出一部分
     * @param payOrderForm
     * @return
     */
    public PaginationSupport<PayOrder> queryPayOrders(PayOrderForm payOrderForm){
        List<PayOrder> list = payOrderMapper.selectPayOrders(payOrderForm);
        int totalCount = payOrderMapper.countPayOrders(payOrderForm);
        return new PaginationSupport(list, totalCount, payOrderForm.getiDisplayLength(), payOrderForm.getiDisplayStart());
    }

    @Override
    public List<PayOrder> queryExportPayOrders(PayOrderForm payOrderForm){
        return payOrderMapper.queryExportPayOrders(payOrderForm);
    }

    /**
     * 支付订单，回调商户
     * @param oforderno  殴飞订单号
     * @param usercode 用户编号
     * @return
     */
    public CallbackResult callbackMerchant(String oforderno, String usercode){
        CallbackResult callbackResult= new CallbackResult();
        if (oforderno == null || usercode == null || oforderno.isEmpty() || usercode.isEmpty()) {
            return new CallbackResult("fail", "商户回调失败");
        }

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String postdate = dateFormat.format(date);
        String retStr = null;

        HttpClientParams params = new HttpClientParams();
        params.setContentCharset("GBK");
        HttpClient hc = new HttpClient();
        params.setSoTimeout(30000);
        hc.setParams(params);
        PostMethod pm = new PostMethod(payapiurl);
        pm.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=gbk");
        String flowid = UUID.randomUUID().toString().replaceAll("-", "");

        try {
            StringBuffer strBuf = new StringBuffer();
            strBuf.append(flowid);
            strBuf.append("callbackMerchant");
            strBuf.append(usercode);
            strBuf.append(oforderno);
            strBuf.append("7OiM8g8JrbysyTQWSdTBh3L84zrcmsoc");
            String sign = DigestUtils.md5Hex(strBuf.toString());

            pm.setParameter("flowid", flowid);
            pm.setParameter("oforderno", oforderno);
            pm.setParameter("usercode", usercode);
            pm.setParameter("mode", "callbackMerchant");
            pm.setParameter("sign", sign);

            hc.executeMethod(pm);
            String retmsg = pm.getResponseBodyAsString();
            if(StringUtils.isEmpty(retmsg)){
                return new CallbackResult("fail", "返回结果为空");
            }
            String result = this.getDataFromXml(retmsg, "<result>", "</result>");
            String msg = this.getDataFromXml(retmsg, "<msg>", "</msg>");
            pm.releaseConnection();
            callbackResult.setMsg(msg);
            if ("1".equals(result.trim())) {
                callbackResult.setResult("success");
            } else {
                callbackResult.setResult("fail");
            }
        } catch (HttpException e) {
            e.printStackTrace();
            return new CallbackResult("fail", "商户回调失败");
        } catch (IOException e) {
            e.printStackTrace();
            return new CallbackResult("fail", "商户回调失败");
        } finally {
            if (pm != null) {
                pm.releaseConnection();
            }
        }

        return callbackResult;
    }

    private String getDataFromXml(String xmlStr, String startLabel, String endLabel) {
        if (StringUtils.isEmpty(startLabel) || StringUtils.isEmpty(endLabel) || StringUtils.isEmpty(xmlStr)) {
            return null;
        }
        return xmlStr.substring(xmlStr.indexOf(startLabel) +startLabel.length(), xmlStr.indexOf(endLabel));
    }

    public CardResult cardCallbackMerchant(String usercode, String billid){
        //获取用户密钥
        String mode = "callback";
        String md5key = queryUserMd5key(usercode);
        String sign = DigestUtils.md5Hex(mode + usercode + billid + md5key);
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("mode", mode);
        paramMap.put("usercode", usercode);
        paramMap.put("billid", billid);
        paramMap.put("sign", sign);
        logger.info("发送给收卡的参数：{}", paramMap);
        String returnStr = httpClientSupport.sendPost(cardurl, paramMap);
        logger.info("收卡接口返回：{}", returnStr);

        if(StringUtils.isEmpty(returnStr)){
            CardResult errorResult = new CardResult();
            errorResult.setResult("fail");
            errorResult.setInfo("获取到的结果为空");

            return errorResult;
        }

        CardResult cardResult = new CardResult();
        cardResult.setResult(this.getDataFromXml(returnStr, "<result>", "</result>"));
        cardResult.setInfo(this.getDataFromXml(returnStr, "<msg>", "</msg>"));
        return cardResult;
    }

    @Override
    public List<Map> queryOuterOrders(RcvCardOrderForm rcvCardOrderForm) {
        return cardOrderMapper.selectOuterOrders(rcvCardOrderForm);
    }
}
