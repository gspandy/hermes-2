package com.hermes.app.domain.job;

/**
 * 提现设置
 * @author of644
 */
public class CashCfg {
    private String autoflowid;     // 配置流水，通过UUID生成32位随机数
    private String usercode;       // 配置的商户号
    private String datastatus;     // 数据状态，1为正常，其他为失效数据
    private String settlemode;     // 自动提现模式，1：定时提现  2：定额提现 3:零点提现
    private String settleamount;   // 定额提现阀值（元）
    private String applytype;      // 提现类型：1、提现现金 2、提现信用点
    private String accountflowid;  // 自动提现现金时商户对应的绑定银行信息，关联tb_pay_useraccountinfo.accountflowid

    public String getAutoflowid() {
        return autoflowid;
    }

    public void setAutoflowid(String autoflowid) {
        this.autoflowid = autoflowid;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getDatastatus() {
        return datastatus;
    }

    public void setDatastatus(String datastatus) {
        this.datastatus = datastatus;
    }

    public String getSettlemode() {
        return settlemode;
    }

    public void setSettlemode(String settlemode) {
        this.settlemode = settlemode;
    }

    public String getSettleamount() {
        return settleamount;
    }

    public void setSettleamount(String settleamount) {
        this.settleamount = settleamount;
    }

    public String getApplytype() {
        return applytype;
    }

    public void setApplytype(String applytype) {
        this.applytype = applytype;
    }

    public String getAccountflowid() {
        return accountflowid;
    }

    public void setAccountflowid(String accountflowid) {
        this.accountflowid = accountflowid;
    }
}
