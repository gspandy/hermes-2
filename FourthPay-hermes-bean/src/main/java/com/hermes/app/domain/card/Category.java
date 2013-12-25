package com.hermes.app.domain.card;


/**
 * 支持卡种信息
 *
 * @author of644
 */
public class Category {

    private String cardcode;  // 卡类编号
    private String corpcode;  // 运营商编号
    private String cardname;  // 卡名
    private String corpname;  // 运营商名
    private String faceval;   // 面值
    private String state;     // 1:正常收卡 2:暂停收卡

    public String getCardcode() {
        return cardcode;
    }

    public void setCardcode(String cardcode) {
        this.cardcode = cardcode;
    }

    public String getCorpcode() {
        return corpcode;
    }

    public void setCorpcode(String corpcode) {
        this.corpcode = corpcode;
    }

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }

    public String getCorpname() {
        return corpname;
    }

    public void setCorpname(String corpname) {
        this.corpname = corpname;
    }

    public String getFaceval() {
        return faceval;
    }

    public void setFaceval(String faceval) {
        this.faceval = faceval;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
