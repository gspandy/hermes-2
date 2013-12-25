package com.hermes.app.domain.cash;

import com.hermes.app.enums.BindStatEnum;

/**
 * 银行卡绑定
 * @author of644
 */
public class Bind {
    private String accountflowid;    // 流水号
    private String bankcode;         // 银行编号
    private String bankname;         // 银行名称
    private String accountno;        // 银行卡号
    private String accshortname;     // 账户简称
    private String accountusername;  // 开户名
    private String idcardno;         //
    private String datastat;         // 审核状态：1处理中;2审核通过;3审核未通过;4冻结;5解冻;
    private String accounttype;      // 1， 银行类型 2，第三方账户
    private String remarkinfo;       // 备注

    public String getStatinfo() {
        return BindStatEnum.fromString(datastat).toString();
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
