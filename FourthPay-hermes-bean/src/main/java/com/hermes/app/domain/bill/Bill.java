package com.hermes.app.domain.bill;

import java.util.Date;

/**
 * 订单（收卡）
 *
 * @author of644
 */
public class Bill {

    private String billid;

    private String merorderno;

    private String usercode;

    private String corpcode;

    private String corpname;

    private String cardno;

    private String cardpass;

    private Double faceval;

    private Double realval;

    private String chargeno;

    private String state;

    private String stateremark;

    private Date ordertime;

    private Date chargestarttime;

    private Date chargeendtime;

    public String getBillid() {
        return billid;
    }

    public void setBillid(String billid) {
        this.billid = billid;
    }

    public String getMerorderno() {
        return merorderno;
    }

    public void setMerorderno(String merorderno) {
        this.merorderno = merorderno;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getCorpcode() {
        return corpcode;
    }

    public void setCorpcode(String corpcode) {
        this.corpcode = corpcode;
    }

    public String getCorpname() {
        return corpname;
    }

    public void setCorpname(String corpname) {
        this.corpname = corpname;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getCardpass() {
        return cardpass;
    }

    public void setCardpass(String cardpass) {
        this.cardpass = cardpass;
    }

    public Double getFaceval() {
        return faceval;
    }

    public void setFaceval(Double faceval) {
        this.faceval = faceval;
    }

    public Double getRealval() {
        return realval;
    }

    public void setRealval(Double realval) {
        this.realval = realval;
    }

    public String getChargeno() {
        return chargeno;
    }

    public void setChargeno(String chargeno) {
        this.chargeno = chargeno;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateremark() {
        return stateremark;
    }

    public void setStateremark(String stateremark) {
        this.stateremark = stateremark;
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public Date getChargestarttime() {
        return chargestarttime;
    }

    public void setChargestarttime(Date chargestarttime) {
        this.chargestarttime = chargestarttime;
    }

    public Date getChargeendtime() {
        return chargeendtime;
    }

    public void setChargeendtime(Date chargeendtime) {
        this.chargeendtime = chargeendtime;
    }
}
