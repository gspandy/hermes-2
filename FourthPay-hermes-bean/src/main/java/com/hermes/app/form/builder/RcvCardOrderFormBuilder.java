package com.hermes.app.form.builder;

import com.hermes.app.form.order.RcvCardOrderForm;

import java.util.List;

/**
 * form builder
 *
 * @author of644
 */
public class RcvCardOrderFormBuilder {
    public String usercode;          // 用户编码
    public String username;          // 用户名
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

    private RcvCardOrderFormBuilder() {
    }

    public static RcvCardOrderFormBuilder aRcvCardOrderForm() {
        return new RcvCardOrderFormBuilder();
    }

    public RcvCardOrderFormBuilder withStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    public RcvCardOrderFormBuilder withEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    public RcvCardOrderFormBuilder withState(String state) {
        this.state = state;
        return this;
    }

    public RcvCardOrderFormBuilder withCorpcode(String corpcode) {
        this.corpcode = corpcode;
        return this;
    }

    public RcvCardOrderFormBuilder withMerorderno(String merorderno) {
        this.merorderno = merorderno;
        return this;
    }

    public RcvCardOrderFormBuilder withBillid(String billid) {
        this.billid = billid;
        return this;
    }

    public RcvCardOrderFormBuilder withCardno(String cardno) {
        this.cardno = cardno;
        return this;
    }

    public RcvCardOrderFormBuilder withKeywordQuery(String keywordQuery) {
        this.keywordQuery = keywordQuery;
        return this;
    }

    public RcvCardOrderFormBuilder withKeywordQueryValue(String keywordQueryValue) {
        this.keywordQueryValue = keywordQueryValue;
        return this;
    }

    public RcvCardOrderFormBuilder withStates(List<String> states) {
        this.states = states;
        return this;
    }

    public RcvCardOrderFormBuilder withUsercode(String usercode) {
        this.usercode = usercode;
        return this;
    }

    public RcvCardOrderFormBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public RcvCardOrderForm build() {
        RcvCardOrderForm rcvCardOrderForm = new RcvCardOrderForm();
        rcvCardOrderForm.setStartTime(startTime);
        rcvCardOrderForm.setEndTime(endTime);
        rcvCardOrderForm.setState(state);
        rcvCardOrderForm.setCorpcode(corpcode);
        rcvCardOrderForm.setMerorderno(merorderno);
        rcvCardOrderForm.setBillid(billid);
        rcvCardOrderForm.setCardno(cardno);
        rcvCardOrderForm.setKeywordQuery(keywordQuery);
        rcvCardOrderForm.setKeywordQueryValue(keywordQueryValue);
        rcvCardOrderForm.setStates(states);
        rcvCardOrderForm.setUsercode(usercode);
        rcvCardOrderForm.setUsername(username);
        return rcvCardOrderForm;
    }
}
