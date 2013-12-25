package com.hermes.app.domain.order;

/**
 * 收卡订单数据分析
 *
 * @author tujianying/of821
 */
public class DataAnalyseStatic {
    private String cardname;  //运营商
    private Integer totalcnt;  //总交易笔数
    private Float totalfaceval;  //总交易面额（元）
    private Integer succcnt; //成功交易笔数
    private Float succfaceval;  //成功交易面额（元）
    private Float succaccaddval;  //成功交易结算额（元）

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }

    public Integer getTotalcnt() {
        return totalcnt;
    }

    public void setTotalcnt(Integer totalcnt) {
        this.totalcnt = totalcnt;
    }

    public Float getTotalfaceval() {
        return totalfaceval;
    }

    public void setTotalfaceval(Float totalfaceval) {
        this.totalfaceval = totalfaceval;
    }

    public Integer getSucccnt() {
        return succcnt;
    }

    public void setSucccnt(Integer succcnt) {
        this.succcnt = succcnt;
    }

    public Float getSuccfaceval() {
        return succfaceval;
    }

    public void setSuccfaceval(Float succfaceval) {
        this.succfaceval = succfaceval;
    }

    public Float getSuccaccaddval() {
        return succaccaddval;
    }

    public void setSuccaccaddval(Float succaccaddval) {
        this.succaccaddval = succaccaddval;
    }
}
