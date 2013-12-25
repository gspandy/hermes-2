package com.hermes.app.domain.order;

/**
 * 银行信息
 *
 * @author  tujianying/of821
 */
public class CashBankInfo {
    private String bankcode;  //银行编号
    private String bankname;  //银行名称

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
