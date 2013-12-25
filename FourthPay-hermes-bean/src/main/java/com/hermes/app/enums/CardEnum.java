package com.hermes.app.enums;

/**
 * 收卡类型
 * @author of644
 */
public enum CardEnum {

    CARD_YD("1", "神州行卡", "移动"), CARD_LT("2", "联通一卡充", "联通"), CARD_DX("3", "电信国卡", "电信"),
    CARD_OF("4", "欧飞一卡通", "欧飞"), CARD_JW("5", "骏网一卡通", "骏网"), CARD_SD("6", "盛大一卡通", "盛大"),
    CARD_TX("7", "Q币", "腾讯"), CARD_WM("8", "完美一卡通", "完美"), CARD_ZT("9", "征途一卡通", "征途"),
    CARD_WY("10", "网易一卡通", "网易"), CARD_SH("11", "搜狐一卡通", "搜狐"), CARD_JY("12", "久游一卡通", "久游"),
    CARD_TH("13", "天宏一卡通", "天宏"), CARD_TXTY("14", "天下一卡通通用", "天下一卡通通用"), CARD_GY("15", "光宇一卡通", "光宇"),
    CARD_ZY("16", "纵游一卡通", "纵游"), CARD_JSYD("17", "江苏神州行", "江苏移动"), CARD_ZJYD("18", "浙江神州行", "浙江移动"),
    CARD_FJYD("20", "福建神州行", "福建移动"), CARD_GDYD("21", "广东神州行", "广东移动"), CARD_CFT("23", "盛付通一卡通", "盛付通");

    private String corpcode;
    private String corpname;
    private String cardname;

    private CardEnum(String corpcode, String cardname, String corpname) {
        this.corpcode = corpcode;
        this.corpname = corpname;
        this.cardname = cardname;
    }

    public String getCorpcode() {
        return corpcode;
    }

    public void setCorpcode(String corpcode) {
        this.corpcode = corpcode;
    }

    public String getCorpname() {
        return corpname;
    }

    public void setCorpname(String corpname) {
        this.corpname = corpname;
    }

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }
}
