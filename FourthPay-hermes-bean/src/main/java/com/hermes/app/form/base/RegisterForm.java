package com.hermes.app.form.base;

import com.hermes.app.validator.NotStartsWith;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 用户注册信息
 *
 * @author tujianying/of821
 */
public class RegisterForm {
    /**
     * 账户信息
     */
    private String userName; //注册用户名
    private String userPassword; //注册用户密码
    private String confirmPassword; //再一次确认密码
    private String merchantType; //商户类型 个人：personal 公司：company

    /**
     * 基本信息
     */
    //个人用户
    private String contactName; //个人联系人
    private String identityCard; //个人身份证

    //企业用户
    private String companyName; //公司名称
    private String companyLicenseNumber; //公司执照号码

    private String province;  //用户省份
    private String city;  //用户城市
    private String country;  //用户地区

    /**
     * 联系方式
     */
    private String cellPhoneNumber; //手机号码
    private String email; //邮箱地址
    private String captcha; //验证码

    @NotBlank(message = "用户名不能为空或者空字符串")
    @Size(min = 4, max = 20, message = "用户名长度必须在{min}和{max}之间")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @NotNull(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "密码长度必须在{min}和{max}之间")
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @NotNull(message = "再次确认密码不能为空")
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(String merchantType) {
        this.merchantType = merchantType;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @NotNull(message = "手机号码不能为空")
    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }

    public void setCellPhoneNumber(String cellPhoneNumber) {
        this.cellPhoneNumber = cellPhoneNumber;
    }

    @NotNull(message = "邮箱不能为空")
    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull(message = "验证码不能为空")
    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    @Override
    public String toString() {
        return "RegisterForm{" +
                "userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", merchantType='" + merchantType + '\'' +
                ", contactName='" + contactName + '\'' +
                ", identityCard='" + identityCard + '\'' +
                ", companyName='" + companyName + '\'' +
                ", companyLicenseNumber='" + companyLicenseNumber + '\'' +
                ", cellPhoneNumber='" + cellPhoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", captcha='" + captcha + '\'' +
                '}';
    }
}
