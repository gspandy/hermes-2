package com.hermes.app.form.cash;

import com.hermes.app.form.BaseForm;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

/**
 * 申请提现form
 *
 * @author of644
 */
public class ApplyForm extends BaseForm{

    public static final String SPLIT = "50000";

    private String cashorderno;
    @NotBlank(message = "请选择需要提现的银行卡")
    private String accountflowid;     // 银行流水号
    @NotBlank(message = "请选择需要提现的银行卡")
    private String bankcode;          // 银行编码
    private String bankname;          // 银行名称
    @NotBlank(message = "请填写提现金额")
    @Min(value = 500, message = "请输入不小于500的提现金额")
    private String applyMoney;        // 申请提现金额
    @NotBlank(message = "请输入提现密码")
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

    public String getCashpwd() {
        return cashpwd;
    }

    public void setCashpwd(String cashpwd) {
        this.cashpwd = cashpwd;
    }

    public String getCashorderno() {
        return cashorderno;
    }

    public void setCashorderno(String cashorderno) {
        this.cashorderno = cashorderno;
    }

    public String getApproveinfo() {
        return approveinfo;
    }

    public void setApproveinfo(String approveinfo) {
        this.approveinfo = approveinfo;
    }

    public String getApprovemanname() {
        return approvemanname;
    }

    public void setApprovemanname(String approvemanname) {
        this.approvemanname = approvemanname;
    }

    public String getOrdersource() {
        return ordersource;
    }

    public void setOrdersource(String ordersource) {
        this.ordersource = ordersource;
    }

    public String getDealstat() {
        return dealstat;
    }

    public void setDealstat(String dealstat) {
        this.dealstat = dealstat;
    }

    public String getDatastat() {
        return datastat;
    }

    public void setDatastat(String datastat) {
        this.datastat = datastat;
    }

    public Boolean getAutoAudit() {
        return autoAudit;
    }

    public void setAutoAudit(Boolean autoAudit) {
        this.autoAudit = autoAudit;
    }

    public String getFeerate() {
        return feerate;
    }

    public void setFeerate(String feerate) {
        this.feerate = feerate;
    }

    public String getCommision() {
        return commision;
    }

    public void setCommision(String commision) {
        this.commision = commision;
    }

    public String getAccountno() {
        return accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }

    public String getAccshortname() {
        return accshortname;
    }

    public void setAccshortname(String accshortname) {
        this.accshortname = accshortname;
    }

    public String getAccountusername() {
        return accountusername;
    }

    public void setAccountusername(String accountusername) {
        this.accountusername = accountusername;
    }

    public String getAccountflowid() {
        return accountflowid;
    }

    public void setAccountflowid(String accountflowid) {
        this.accountflowid = accountflowid;
    }

    public String getApplytype() {
        return applytype;
    }

    public void setApplytype(String applytype) {
        this.applytype = applytype;
    }

    public String getDeduMoney() {
        return deduMoney;
    }

    public void setDeduMoney(String deduMoney) {
        this.deduMoney = deduMoney;
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

    public String getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(String accounttype) {
        this.accounttype = accounttype;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getApplyMoney() {
        return applyMoney;
    }

    public void setApplyMoney(String applyMoney) {
        this.applyMoney = applyMoney;
    }

    public String getBankcode() {
        return bankcode;
    }

    public void setBankcode(String bankcode) {
        this.bankcode = bankcode;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }
}
