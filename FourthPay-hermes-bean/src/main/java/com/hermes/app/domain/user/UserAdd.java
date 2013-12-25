package com.hermes.app.domain.user;


/**
 * 用户附加信息
 * @author of644
 */
public class UserAdd {

    // 账户基本资料维护 支付资料（part2）
    private String company;             // 公司名
    private String siteurl;             // 网站url
    private String siteicp;             // 备案号
    private String companydes;          // 应用场合介绍


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSiteurl() {
        return siteurl;
    }

    public void setSiteurl(String siteurl) {
        this.siteurl = siteurl;
    }

    public String getSiteicp() {
        return siteicp;
    }

    public void setSiteicp(String siteicp) {
        this.siteicp = siteicp;
    }

    public String getCompanydes() {
        return companydes;
    }

    public void setCompanydes(String companydes) {
        this.companydes = companydes;
    }
}
