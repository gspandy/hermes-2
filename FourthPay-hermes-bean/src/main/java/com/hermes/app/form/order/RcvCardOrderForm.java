package com.hermes.app.form.order;

import com.hermes.app.form.BaseTableForm;

import java.util.Date;
import java.util.List;

/**
 * 收卡订单form
 *
 * @author tujianying/of821
 */
public class RcvCardOrderForm extends BaseTableForm {
    private String startTime;    // 查询开始时间
    private String endTime;      // 查询结束时间
    private String state;    // 状态
    private String corpcode;  //发卡公司编号
    private String merorderno;   //商户订单号
    private String billid;       //殴飞流水号，殴飞订单号
    private String cardno;      //充值卡卡号
    private String keywordQuery;  //关键字选了什么。1：充值卡号  2：商户订单号 3:殴飞流水号
    private String keywordQueryValue; //查询关键子的值
    private List<String> states;  //与数据库中的订单状态对应关系

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCorpcode() {
        return corpcode;
    }

    public void setCorpcode(String corpcode) {
        this.corpcode = corpcode;
    }

    public String getMerorderno() {
        return merorderno;
    }

    public void setMerorderno(String merorderno) {
        this.merorderno = merorderno;
    }

    public String getBillid() {
        return billid;
    }

    public void setBillid(String billid) {
        this.billid = billid;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
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

    public List<String> getStates() {
        return states;
    }

    public void setStates(List<String> states) {
        this.states = states;
    }
}
