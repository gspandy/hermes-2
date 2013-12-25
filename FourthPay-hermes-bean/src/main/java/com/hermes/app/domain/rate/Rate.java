package com.hermes.app.domain.rate;

/**
 * 支付费率（收卡）
 *
 * @author tujianying/of821
 */
public class Rate {
    private String cardName; //卡类型，即卡名称
    private String rate;     //该卡的费率

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
