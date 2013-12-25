package com.hermes.app.domain.order;

/**
 * 导出支付订单管理（大接口的非收卡的支付管理）
 *
 * @author tujianying/of821
 */
public class ExportPayOrder {
    private String orderno;   //商户订单号
    private String bankorderno;   //网银订单号
    private String transactionid;   //流水号
    private Float ordermoney;   //订单金额
    private Float commission;   //手续费
    private Float merchantretmoney;   //分账余额
    private Float merleftmoney;   //支付余额

    private String paymethodname;   //支付方式
    private String dealstat;   //支付状态
    private String dealtime;   //支付时间
    private String refundstate;   //退款状态
    private String refundamount;   //退款金额
    private String refundopttime;   //退款时间
    private String ordertime;   //商户交互时间

    public ExportPayOrder(String orderno, String bankorderno, String transactionid, Float ordermoney,
                          Float commission, Float merchantretmoney, Float merleftmoney,
                          String paymethodname, String dealstat, String dealtime, String refundstate,
                          String refundamount, String refundopttime, String ordertime) {
        this.orderno = orderno;
        this.bankorderno = bankorderno;
        this.transactionid = transactionid;
        this.ordermoney = ordermoney;
        this.commission = commission;
        this.merchantretmoney = merchantretmoney;
        this.merleftmoney = merleftmoney;
        this.paymethodname = paymethodname;
        this.dealstat = dealstat;
        this.dealtime = dealtime;
        this.refundstate = refundstate;
        this.refundamount = refundamount;
        this.refundopttime = refundopttime;
        this.ordertime = ordertime;
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

    public Float getOrdermoney() {
        return ordermoney;
    }

    public void setOrdermoney(Float ordermoney) {
        this.ordermoney = ordermoney;
    }

    public Float getCommission() {
        return commission;
    }

    public void setCommission(Float commission) {
        this.commission = commission;
    }

    public Float getMerchantretmoney() {
        return merchantretmoney;
    }

    public void setMerchantretmoney(Float merchantretmoney) {
        this.merchantretmoney = merchantretmoney;
    }

    public Float getMerleftmoney() {
        return merleftmoney;
    }

    public void setMerleftmoney(Float merleftmoney) {
        this.merleftmoney = merleftmoney;
    }

    public String getPaymethodname() {
        return paymethodname;
    }

    public void setPaymethodname(String paymethodname) {
        this.paymethodname = paymethodname;
    }

    public String getDealstat() {
        return dealstat;
    }

    public void setDealstat(String dealstat) {
        this.dealstat = dealstat;
    }

    public String getDealtime() {
        return dealtime;
    }

    public void setDealtime(String dealtime) {
        this.dealtime = dealtime;
    }

    public String getRefundstate() {
        return refundstate;
    }

    public void setRefundstate(String refundstate) {
        this.refundstate = refundstate;
    }

    public String getRefundamount() {
        return refundamount;
    }

    public void setRefundamount(String refundamount) {
        this.refundamount = refundamount;
    }

    public String getRefundopttime() {
        return refundopttime;
    }

    public void setRefundopttime(String refundopttime) {
        this.refundopttime = refundopttime;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }
}
