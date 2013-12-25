package com.hermes.app.domain.user;

import com.hermes.app.web.util.DateUtil;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

    private String username;            // 用户名
    private String password;            // 用户密码
    private String nickname;            // 登录名
    private String curip;               // 当前IP
    private String lastip;              // 上一次登入Ip
    private String pusercode;           // 父权限用户
    private String ctype;               // 权限类型
    private String usercode;            // 用户编码
    private String tempflag;            // 用户标识
    private int errlognum;              // 用户登录错误次数
    private String userStat;            // 用户状态
    private Date errlogtime;            // 最后一个用户登入失败时间
    private String belongTo;            // 上级编号
    private String shopLevel;           // 用户级别
    private int userSourceFlag;         // 用户类型能 1 欧飞用户 2 拍拍用户 3其他
    private int merpayflag;             // 是否支付商户
    private String userTag;             // 用户标识？？？tjy
    private String psw;

    private String merchantType;            // 商户类型 个人：personal 公司：company

    //个人用户
    private String identityCard;            // 个人身份证

    //企业用户
    private String companyName;             // 公司名称
    private String companyLicenseNumber;    // 公司执照号码

    private String userRealName;        // 个人或者公司联系人真实名字
    private String prvcin;              // 用户省份
    private String cityin;              // 用户城市
    private String country;             // 用户地区
    private String opendeflev;

    private String logpsw;              // 登录密码
    private String paypws;              // 提现密码
    private String locked;              // 提现锁定
    private String userfax;             // 交易密码
    private String logpwdtime;          // 修改登录密码时间

    // 账户基本资料维护 联系方式（part1）
    private String usercell;            // 手机
    private String email;               // 邮箱
    private String usertel;             // 固话
    private String qq;                  // qq
    private String idno;                // 身份证

    private Date lasttime;              // 上次登录时间
    private String logdays;             // 上次修改登录密码的时间(天数)
    private String busdays;             // 上次修改交易密码的时间(天数)

    public String getBusdays() {
        return busdays;
    }

    public void setBusdays(String busdays) {
        this.busdays = busdays;
    }

    public String getLogdays() {
        return logdays;
    }

    public void setLogdays(String logdays) {
        this.logdays = logdays;
    }

    public String getLogpsw() {
        return logpsw;
    }

    public void setLogpsw(String logpsw) {
        this.logpsw = logpsw;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getLasttime() {
        return DateUtil.getDateTime(lasttime);
    }

    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }

    public String getLogpwdtime() {
        return logpwdtime;
    }

    public void setLogpwdtime(String logpwdtime) {
        this.logpwdtime = logpwdtime;
    }

    public String getUserfax() {
        return userfax;
    }

    public void setUserfax(String userfax) {
        this.userfax = userfax;
    }

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked;
    }

    public String getPaypws() {
        return paypws;
    }

    public void setPaypws(String paypws) {
        this.paypws = paypws;
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

    public int getErrlognum() {
        return errlognum;
    }

    public void setErrlognum(int errlognum) {
        this.errlognum = errlognum;
    }

    public String getTempflag() {
        return tempflag;
    }

    public void setTempflag(String tempflag) {
        this.tempflag = tempflag;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getPusercode() {
        return pusercode;
    }

    public void setPusercode(String pusercode) {
        this.pusercode = pusercode;
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCurip() {
        return curip;
    }

    public void setCurip(String curip) {
        this.curip = curip;
    }

    public String getLastip() {
        return lastip;
    }

    public void setLastip(String lastip) {
        this.lastip = lastip;
    }

    public String getUserStat() {
        return userStat;
    }

    public void setUserStat(String userStat) {
        this.userStat = userStat;
    }

    public Date getErrlogtime() {
        return errlogtime;
    }

    public void setErrlogtime(Date errlogtime) {
        this.errlogtime = errlogtime;
    }

    public String getBelongTo() {
        return belongTo;
    }

    public void setBelongTo(String belongTo) {
        this.belongTo = belongTo;
    }

    public String getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(String merchantType) {
        this.merchantType = merchantType;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyLicenseNumber() {
        return companyLicenseNumber;
    }

    public void setCompanyLicenseNumber(String companyLicenseNumber) {
        this.companyLicenseNumber = companyLicenseNumber;
    }

    public String getPrvcin() {
        return prvcin;
    }

    public void setPrvcin(String prvcin) {
        this.prvcin = prvcin;
    }

    public String getCityin() {
        return cityin;
    }

    public void setCityin(String cityin) {
        this.cityin = cityin;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getShopLevel() {
        return shopLevel;
    }

    public void setShopLevel(String shopLevel) {
        this.shopLevel = shopLevel;
    }

    public int getUserSourceFlag() {
        return userSourceFlag;
    }

    public void setUserSourceFlag(int userSourceFlag) {
        this.userSourceFlag = userSourceFlag;
    }

    public int getMerpayflag() {
        return merpayflag;
    }

    public void setMerpayflag(int merpayflag) {
        this.merpayflag = merpayflag;
    }

    public String getUserTag() {
        return userTag;
    }

    public void setUserTag(String userTag) {
        this.userTag = userTag;
    }

    public String getOpendeflev() {
        return opendeflev;
    }

    public void setOpendeflev(String opendeflev) {
        this.opendeflev = opendeflev;
    }
}
