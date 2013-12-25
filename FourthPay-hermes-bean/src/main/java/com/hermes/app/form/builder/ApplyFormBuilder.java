package com.hermes.app.form.builder;

import com.hermes.app.form.cash.ApplyForm;

/**
 * form builder
 *
 * @author of644
 */
public class ApplyFormBuilder {
    public String usercode;          // 用户编码
    public String username;          // 用户名
    private String cashorderno;
    private String accountflowid;     // 银行流水号
    private String bankcode;          // 银行编码
    private String bankname;          // 银行名称
    private String applyMoney;        // 申请提现金额
    private String cashpwd;           // 输入的提现密码
    private String rate;              // 费率
    private String accounttype;       // 银行类型：1：银行2：第三方账户
    private String merleftmoney;      // 剩余金额（提现前）
    private String merleftmoneyafter; // 剩余金额（扣除提现金额和手续费后）
    private String deduMoney;         // 需要扣除的金额
    private String applytype;         // 申请提现的类型，1：申请冻结资金提现2：申请冻结资金转信用点
    private String accountno;         // 银行卡号
    private String accshortname;      // 账户简称
    private String accountusername;   // 开户名
    private String commision;         // 手续费
    private String feerate;           // 费率
    private Boolean autoAudit;        // 是否自动审核
    private String datastat;          // 1：正常订单 2：审核通过 3：审核不通过
    private String dealstat;          // 0：未处理1:已发送交易2：交易成功3：交易失败
    private String ordersource;       // 订单来源【9：第四方商户】
    private String approvemanname;
    private String approveinfo;

    private ApplyFormBuilder() {
    }

    public static ApplyFormBuilder anApplyForm() {
        return new ApplyFormBuilder();
    }

    public ApplyFormBuilder withCashorderno(String cashorderno) {
        this.cashorderno = cashorderno;
        return this;
    }

    public ApplyFormBuilder withAccountflowid(String accountflowid) {
        this.accountflowid = accountflowid;
        return this;
    }

    public ApplyFormBuilder withBankcode(String bankcode) {
        this.bankcode = bankcode;
        return this;
    }

    public ApplyFormBuilder withBankname(String bankname) {
        this.bankname = bankname;
        return this;
    }

    public ApplyFormBuilder withApplyMoney(String applyMoney) {
        this.applyMoney = applyMoney;
        return this;
    }

    public ApplyFormBuilder withCashpwd(String cashpwd) {
        this.cashpwd = cashpwd;
        return this;
    }

    public ApplyFormBuilder withRate(String rate) {
        this.rate = rate;
        return this;
    }

    public ApplyFormBuilder withAccounttype(String accounttype) {
        this.accounttype = accounttype;
        return this;
    }

    public ApplyFormBuilder withMerleftmoney(String merleftmoney) {
        this.merleftmoney = merleftmoney;
        return this;
    }

    public ApplyFormBuilder withMerleftmoneyafter(String merleftmoneyafter) {
        this.merleftmoneyafter = merleftmoneyafter;
        return this;
    }

    public ApplyFormBuilder withDeduMoney(String deduMoney) {
        this.deduMoney = deduMoney;
        return this;
    }

    public ApplyFormBuilder withApplytype(String applytype) {
        this.applytype = applytype;
        return this;
    }

    public ApplyFormBuilder withAccountno(String accountno) {
        this.accountno = accountno;
        return this;
    }

    public ApplyFormBuilder withAccshortname(String accshortname) {
        this.accshortname = accshortname;
        return this;
    }

    public ApplyFormBuilder withAccountusername(String accountusername) {
        this.accountusername = accountusername;
        return this;
    }

    public ApplyFormBuilder withCommision(String commision) {
        this.commision = commision;
        return this;
    }

    public ApplyFormBuilder withFeerate(String feerate) {
        this.feerate = feerate;
        return this;
    }

    public ApplyFormBuilder withAutoAudit(Boolean autoAudit) {
        this.autoAudit = autoAudit;
        return this;
    }

    public ApplyFormBuilder withDatastat(String datastat) {
        this.datastat = datastat;
        return this;
    }

    public ApplyFormBuilder withDealstat(String dealstat) {
        this.dealstat = dealstat;
        return this;
    }

    public ApplyFormBuilder withOrdersource(String ordersource) {
        this.ordersource = ordersource;
        return this;
    }

    public ApplyFormBuilder withApprovemanname(String approvemanname) {
        this.approvemanname = approvemanname;
        return this;
    }

    public ApplyFormBuilder withApproveinfo(String approveinfo) {
        this.approveinfo = approveinfo;
        return this;
    }

    public ApplyFormBuilder withUsercode(String usercode) {
        this.usercode = usercode;
        return this;
    }

    public ApplyFormBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public ApplyForm build() {
        ApplyForm applyForm = new ApplyForm();
        applyForm.setCashorderno(cashorderno);
        applyForm.setAccountflowid(accountflowid);
        applyForm.setBankcode(bankcode);
        applyForm.setBankname(bankname);
        applyForm.setApplyMoney(applyMoney);
        applyForm.setCashpwd(cashpwd);
        applyForm.setRate(rate);
        applyForm.setAccounttype(accounttype);
        applyForm.setMerleftmoney(merleftmoney);
        applyForm.setMerleftmoneyafter(merleftmoneyafter);
        applyForm.setDeduMoney(deduMoney);
        applyForm.setApplytype(applytype);
        applyForm.setAccountno(accountno);
        applyForm.setAccshortname(accshortname);
        applyForm.setAccountusername(accountusername);
        applyForm.setCommision(commision);
        applyForm.setFeerate(feerate);
        applyForm.setAutoAudit(autoAudit);
        applyForm.setDatastat(datastat);
        applyForm.setDealstat(dealstat);
        applyForm.setOrdersource(ordersource);
        applyForm.setApprovemanname(approvemanname);
        applyForm.setApproveinfo(approveinfo);
        applyForm.setUsercode(usercode);
        applyForm.setUsername(username);
        return applyForm;
    }
}
