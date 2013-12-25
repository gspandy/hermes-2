package com.hermes.app.form.card;

import com.hermes.app.form.BaseForm;
import org.hibernate.validator.constraints.NotBlank;


/**
 * 收卡form
 *
 * @author of644
 */
public class CardForm extends BaseForm {
    @NotBlank(message = "请选择提卡面值")
    private String cardcode;  // 卡类编号
    private String cardname;  // 卡名
    private String corpname;  // 运营商名
    private String faceval;   // 面值
    private String corpcode;  // 运营商编号
    private String state;     // 1:正常收卡 2:暂停收卡
    // @NotBlank(message = "请输入充值卡卡号")
    private String cardno;    // 卡号
    // @NotBlank(message = "请输入充值卡密码")
    private String cardpass;  // 卡密
    private String md5key;
    private String captcha;   // 验证码
    private String cardcontent;     // 批量提交

    public String getCardcontent() {
        return cardcontent;
    }

    public void setCardcontent(String cardcontent) {
        this.cardcontent = cardcontent;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getMd5key() {
        return md5key;
    }

    public void setMd5key(String md5key) {
        this.md5key = md5key;
    }

    public String getCardpass() {
        return cardpass;
    }

    public void setCardpass(String cardpass) {
        this.cardpass = cardpass;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getCardcode() {
        return cardcode;
    }

    public void setCardcode(String cardcode) {
        this.cardcode = cardcode;
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

    public String getCorpcode() {
        return corpcode;
    }

    public void setCorpcode(String corpcode) {
        this.corpcode = corpcode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
