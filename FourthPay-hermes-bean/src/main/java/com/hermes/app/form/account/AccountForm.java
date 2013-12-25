package com.hermes.app.form.account;

import com.hermes.app.form.BaseForm;


/**
 * 账户
 *
 * @author of644
 */
public class AccountForm extends BaseForm {
    private String logpswBefore;        // 原登录密码
    private String logpswNew;           // 新登录密码
    private String logpswNewAgain;      // 新登录密码
    private String paypwsBefore;        // 原提现密码
    private String paypwsNew;           // 新提现密码
    private String paypwsNewAgain;      // 新提现密码
    private String userfaxBefore;       // 原交易密码
    private String userfaxNew;          // 新交易密码
    private String userfaxNewAgain;     // 新交易密码

    // 账户基本资料维护 联系方式（part1）
    private String usercell;            // 手机
    private String email;               // 邮箱
    private String usertel;             // 固话
    private String qq;                  // qq

    // 账户基本资料维护 支付资料（part2）
    private String company;             // 公司名
    private String siteurl;             // 网站url
    private String siteicp;             // 备案号
    private String companydes;          // 应用场合介绍
    private String remoteIp;            // ip

    // 公告
    private String createtime;

    private String ping;
    private String psw;

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getPing() {
        return ping;
    }

    public void setPing(String ping) {
        this.ping = ping;
    }

    public String getLogpswBefore() {
        return logpswBefore;
    }

    public void setLogpswBefore(String logpswBefore) {
        this.logpswBefore = logpswBefore;
    }

    public String getLogpswNew() {
        return logpswNew;
    }

    public void setLogpswNew(String logpswNew) {
        this.logpswNew = logpswNew;
    }

    public String getLogpswNewAgain() {
        return logpswNewAgain;
    }

    public void setLogpswNewAgain(String logpswNewAgain) {
        this.logpswNewAgain = logpswNewAgain;
    }

    public String getPaypwsBefore() {
        return paypwsBefore;
    }

    public void setPaypwsBefore(String paypwsBefore) {
        this.paypwsBefore = paypwsBefore;
    }

    public String getPaypwsNew() {
        return paypwsNew;
    }

    public void setPaypwsNew(String paypwsNew) {
        this.paypwsNew = paypwsNew;
    }

    public String getPaypwsNewAgain() {
        return paypwsNewAgain;
    }

    public void setPaypwsNewAgain(String paypwsNewAgain) {
        this.paypwsNewAgain = paypwsNewAgain;
    }

    public String getUserfaxBefore() {
        return userfaxBefore;
    }

    public void setUserfaxBefore(String userfaxBefore) {
        this.userfaxBefore = userfaxBefore;
    }

    public String getUserfaxNew() {
        return userfaxNew;
    }

    public void setUserfaxNew(String userfaxNew) {
        this.userfaxNew = userfaxNew;
    }

    public String getUserfaxNewAgain() {
        return userfaxNewAgain;
    }

    public void setUserfaxNewAgain(String userfaxNewAgain) {
        this.userfaxNewAgain = userfaxNewAgain;
    }

    public String getUsercell() {
        return usercell;
    }

    public void setUsercell(String usercell) {
        this.usercell = usercell;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsertel() {
        return usertel;
    }

    public void setUsertel(String usertel) {
        this.usertel = usertel;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

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

    public String getRemoteIp() {
        return remoteIp;
    }

    public void setRemoteIp(String remoteIp) {
        this.remoteIp = remoteIp;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}
