package com.hermes.app.domain.order;

/**
 * 收卡订单管理
 *
 * @author tujianying/of821
 */
public class RcvcardOrder {
    private String usercode; //商户编号
    private String merorderno; //商户订单号
    private String billid; //殴飞流水号，殴飞订单号
    private String cardno; //充值卡卡号
    private Float faceval; //声明面值
    private Float realval; //结算面值,真实面值
    private Float mercommission;  //手续费
    private Float merleftmoney; //账户余额
    private String cardname; //卡的运营商，如移动，联通等
    private String retresult; //支付状态
    private String retresultmsg; //支付状态描述
    private String ordertime; //订单时间
    private String accaddval;    // 实际支付额
    private String corpname;     // 运营商名称
    private String cardcode;  //卡类编号
    private String cardpass;  //卡密

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
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

    public Float getFaceval() {
        return faceval;
    }

    public void setFaceval(Float faceval) {
        this.faceval = faceval;
    }

    public Float getRealval() {
        return realval;
    }

    public void setRealval(Float realval) {
        this.realval = realval;
    }

    public Float getMercommission() {
        return mercommission;
    }

    public void setMercommission(Float mercommission) {
        this.mercommission = mercommission;
    }

    public Float getMerleftmoney() {
        return merleftmoney;
    }

    public void setMerleftmoney(Float merleftmoney) {
        this.merleftmoney = merleftmoney;
    }

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }

    public String getRetresult() {
        return retresult;
    }

    public void setRetresult(String retresult) {
        this.retresult = retresult;
    }

    public String getRetresultmsg() {
        return retresultmsg;
    }

    public void setRetresultmsg(String retresultmsg) {
        this.retresultmsg = retresultmsg;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public String getAccaddval() {
        return accaddval;
    }

    public void setAccaddval(String accaddval) {
        this.accaddval = accaddval;
    }

    public String getCorpname() {
        return corpname;
    }

    public void setCorpname(String corpname) {
        this.corpname = corpname;
    }

    public String getCardcode() {
        return cardcode;
    }

    public void setCardcode(String cardcode) {
        this.cardcode = cardcode;
    }

    public String getCardpass() {
        return cardpass;
    }

    public void setCardpass(String cardpass) {
        this.cardpass = cardpass;
    }

    @Override
    public String toString() {
        return "RcvcardOrder{" +
                "usercode='" + usercode + '\'' +
                ", merorderno='" + merorderno + '\'' +
                ", billid='" + billid + '\'' +
                ", cardno='" + cardno + '\'' +
                ", faceval=" + faceval +
                ", realval=" + realval +
                ", mercommission=" + mercommission +
                ", merleftmoney=" + merleftmoney +
                ", cardname='" + cardname + '\'' +
                ", retresult='" + retresult + '\'' +
                ", ordertime=" + ordertime +
                ", accaddval='" + accaddval + '\'' +
                ", corpname='" + corpname + '\'' +
                '}';
    }
}
