package com.hermes.app.domain.cash;


/**
 * 用户余额，积分
 *
 * @author of644
 */
public class UserCount {
    private String merleftmoney; // 余额
    private String leftcredit;   // 积分

    public String getLeftcredit() {
        return leftcredit;
    }

    public void setLeftcredit(String leftcredit) {
        this.leftcredit = leftcredit;
    }

    public String getMerleftmoney() {
        return merleftmoney;
    }

    public void setMerleftmoney(String merleftmoney) {
        this.merleftmoney = merleftmoney;
    }
}
