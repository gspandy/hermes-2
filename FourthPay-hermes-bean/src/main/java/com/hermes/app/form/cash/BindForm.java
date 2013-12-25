package com.hermes.app.form.cash;

import com.hermes.app.form.BaseForm;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 银行账户绑定form
 *
 * @author of644
 */
public class BindForm extends BaseForm{
    public String accountflowid;    // 流水号
    @NotBlank(message="请选择银行类型")
    public String bankcode;         // 银行编号
    public String bankname;         // 银行名称
    @NotBlank(message="请输入银行卡号")
    public String accountno;        // 银行卡号
    @NotBlank(message="请再次输入银行卡号")
    public String accountnoagain;        // 银行卡号
    public String accshortname;     // 账户简称
    @NotBlank(message="请输入开户名")
    public String accountusername;  // 开户名
    public String idcardno;         //
    public String datastat;         // 审核状态：1处理中;2审核通过;3审核未通过;4冻结;5解冻;
    public String accounttype;      // 1， 银行 2，第三方账户
    public String remarkinfo;       // 备注

    public String getAccountnoagain() {
        return accountnoagain;
    }

    public void setAccountnoagain(String accountnoagain) {
        this.accountnoagain = accountnoagain;
    }

    public String getRemarkinfo() {
        return remarkinfo;
    }

    public void setRemarkinfo(String remarkinfo) {
        this.remarkinfo = remarkinfo;
    }

    public String getAccountflowid() {
        return accountflowid;
    }

    public void setAccountflowid(String accountflowid) {
        this.accountflowid = accountflowid;
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

    public String getIdcardno() {
        return idcardno;
    }

    public void setIdcardno(String idcardno) {
        this.idcardno = idcardno;
    }

    public String getDatastat() {
        return datastat;
    }

    public void setDatastat(String datastat) {
        this.datastat = datastat;
    }

    public String getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(String accounttype) {
        this.accounttype = accounttype;
    }

}
