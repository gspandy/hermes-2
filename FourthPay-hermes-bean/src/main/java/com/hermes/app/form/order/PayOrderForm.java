package com.hermes.app.form.order;

import com.hermes.app.form.BaseTableForm;

import java.util.List;

/**
 * 收卡订单form
 *
 * @author tujianying/of821
 */
public class PayOrderForm extends BaseTableForm {
    private String startTime;    // 查询开始时间
    private String endTime;      // 查询结束时间
    private String dealstat;    // 状态
    private String paymethodcode; //支付类型编号
    private String orderno;  //商户订单号
    private String bankorderno; //网银订单号
    private String transactionid; //流水号
    private String keywordQuery;  //关键字选了什么。1：殴飞订单号  2：网银订单号 3:流水号
    private String keywordQueryValue; //查询关键子的值

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDealstat() {
        return dealstat;
    }

    public void setDealstat(String dealstat) {
        this.dealstat = dealstat;
    }

    public String getPaymethodcode() {
        return paymethodcode;
    }

    public void setPaymethodcode(String paymethodcode) {
        this.paymethodcode = paymethodcode;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getBankorderno() {
        return bankorderno;
    }

    public void setBankorderno(String bankorderno) {
        this.bankorderno = bankorderno;
    }

    public String getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(String transactionid) {
        this.transactionid = transactionid;
    }

    public String getKeywordQuery() {
        return keywordQuery;
    }

    public void setKeywordQuery(String keywordQuery) {
        this.keywordQuery = keywordQuery;
    }

    public String getKeywordQueryValue() {
        return keywordQueryValue;
    }

    public void setKeywordQueryValue(String keywordQueryValue) {
        this.keywordQueryValue = keywordQueryValue;
    }
}
