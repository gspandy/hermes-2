package com.hermes.app.form.builder;

import com.hermes.app.form.cash.BindForm;

/**
 * form builder
 *
 * @author of644
 */
public class BindFormBuilder {
    public String accountflowid;    // 流水号
    public String bankcode;         // 银行编号
    public String bankname;         // 银行名称
    public String accountno;        // 银行卡号
    public String accshortname;     // 账户简称
    public String accountusername;  // 开户名
    public String idcardno;         //
    public String datastat;         // 审核状态：1处理中;2审核通过;3审核未通过;4冻结;5解冻;
    public String accounttype;      // 1， 银行 2，第三方账户
    public String usercode;          // 用户编码
    public String username;          // 用户名

    private BindFormBuilder() {
    }

    public static BindFormBuilder aBindForm() {
        return new BindFormBuilder();
    }

    public BindFormBuilder withAccountflowid(String accountflowid) {
        this.accountflowid = accountflowid;
        return this;
    }

    public BindFormBuilder withBankcode(String bankcode) {
        this.bankcode = bankcode;
        return this;
    }

    public BindFormBuilder withBankname(String bankname) {
        this.bankname = bankname;
        return this;
    }

    public BindFormBuilder withAccountno(String accountno) {
        this.accountno = accountno;
        return this;
    }

    public BindFormBuilder withAccshortname(String accshortname) {
        this.accshortname = accshortname;
        return this;
    }

    public BindFormBuilder withAccountusername(String accountusername) {
        this.accountusername = accountusername;
        return this;
    }

    public BindFormBuilder withIdcardno(String idcardno) {
        this.idcardno = idcardno;
        return this;
    }

    public BindFormBuilder withDatastat(String datastat) {
        this.datastat = datastat;
        return this;
    }

    public BindFormBuilder withAccounttype(String accounttype) {
        this.accounttype = accounttype;
        return this;
    }

    public BindFormBuilder withUsercode(String usercode) {
        this.usercode = usercode;
        return this;
    }

    public BindFormBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public BindForm build() {
        BindForm bindForm = new BindForm();
        bindForm.setAccountflowid(accountflowid);
        bindForm.setBankcode(bankcode);
        bindForm.setBankname(bankname);
        bindForm.setAccountno(accountno);
        bindForm.setAccshortname(accshortname);
        bindForm.setAccountusername(accountusername);
        bindForm.setIdcardno(idcardno);
        bindForm.setDatastat(datastat);
        bindForm.setAccounttype(accounttype);
        bindForm.setUsercode(usercode);
        bindForm.setUsername(username);
        return bindForm;
    }
}
