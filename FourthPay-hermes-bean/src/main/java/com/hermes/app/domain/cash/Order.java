package com.hermes.app.domain.cash;

import com.hermes.app.web.util.DateUtil;

import java.util.Date;

/**
 * 提现订单管理
 *
 * @author of644
 */
public class Order {
    private String cashorderno;          // 提现单号
    private Date applytime;              // 申请时间
    private String applymoney;           // 提现金额
    private String commision;            // 手续费
    private String merleftmoney;         // 提现前金额
    private String merleftmoneyafter;    // 提现后金额
    private String applytype;            // 提现类型 1：提现，2：转信用点
    private String bankname;             // 银行名称
    private String accountusername;      // 真实姓名
    private String accountno;            // 银行账号
    private String datastat;             // 运营审核 1待审核2通过3拒绝
    private String dealstat;             // 财务处理 0待处理1已提交2交易成功3交易失败
    private String remarkinfo;           // 备注
    private String stat;                 // 状态

    public String getFormatApplytime() {
        return DateUtil.getDateTime(applytime);
    }

    public String getCashorderno() {
        return cashorderno;
    }

    public void setCashorderno(String cashorderno) {
        this.cashorderno = cashorderno;
    }

    public Date getApplytime() {
        return applytime;
    }

    public void setApplytime(Date applytime) {
        this.applytime = applytime;
    }

    public String getApplymoney() {
        return applymoney;
    }

    public void setApplymoney(String applymoney) {
        this.applymoney = applymoney;
    }

    public String getCommision() {
        return commision;
    }

    public void setCommision(String commision) {
        this.commision = commision;
    }

    public String getMerleftmoney() {
        return merleftmoney;
    }

    public void setMerleftmoney(String merleftmoney) {
        this.merleftmoney = merleftmoney;
    }

    public String getMerleftmoneyafter() {
        return merleftmoneyafter;
    }

    public void setMerleftmoneyafter(String merleftmoneyafter) {
        this.merleftmoneyafter = merleftmoneyafter;
    }

    public String getApplytype() {
        return applytype;
    }

    public void setApplytype(String applytype) {
        this.applytype = applytype;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getAccountusername() {
        return accountusername;
    }

    public void setAccountusername(String accountusername) {
        this.accountusername = accountusername;
    }

    public String getAccountno() {
        return accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }

    public String getDatastat() {
        return datastat;
    }

    public void setDatastat(String datastat) {
        this.datastat = datastat;
    }

    public String getDealstat() {
        return dealstat;
    }

    public void setDealstat(String dealstat) {
        this.dealstat = dealstat;
    }

    public String getRemarkinfo() {
        return remarkinfo;
    }

    public void setRemarkinfo(String remarkinfo) {
        this.remarkinfo = remarkinfo;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }
}
