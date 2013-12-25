package com.hermes.app.domain.order;

/**
 * 收卡订单管理，专门用来export的对象
 *
 * @author tujianying/of821
 */
public class ExportRcvcardOrder {
    private String merorderno; //商户订单号
    private String billid; //殴飞流水号，殴飞订单号
    private String cardno; //充值卡卡号
    private Float faceval; //声明面值
    private Float realval; //结算面值,真实面值
    private Float mercommission;  //手续费
    private Float merleftmoney; //账户余额
    private String cardname; //卡的运营商，如移动，联通等
    private String retresultmsg; //支付状态描述
    private String ordertime; //订单时间

    public ExportRcvcardOrder(){}

    public ExportRcvcardOrder(String merorderno, String billid, String cardno, Float faceval,
                              Float realval, Float mercommission, Float merleftmoney,
                              String cardname, String retresultmsg, String ordertime) {
        this.merorderno = merorderno;
        this.billid = billid;
        this.cardno = cardno;
        this.faceval = faceval;
        this.realval = realval;
        this.mercommission = mercommission;
        this.merleftmoney = merleftmoney;
        this.cardname = cardname;
        this.retresultmsg = retresultmsg;
        this.ordertime = ordertime;
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
}
