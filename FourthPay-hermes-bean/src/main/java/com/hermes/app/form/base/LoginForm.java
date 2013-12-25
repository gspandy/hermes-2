package com.hermes.app.form.base;

import com.hermes.app.validator.NotStartsWith;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * 商户通过第四方登入
 *
 * @author of821
 */
public class LoginForm {
    private String username;  //用户名
    private String password;   //密码
    private String captcha;  //验证码
    private String ping;  //地址
    private String lastip;

    public String getLastip() {
        return lastip;
    }

    public void setLastip(String lastip) {
        this.lastip = lastip;
    }

    @NotBlank(message = "用户名不能为空或者空字符串")
    @NotStartsWith(value="paipai_", message = "拍拍用户不能登陆")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull(message = "密码不能为空")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotEmpty(message = "验证码不能为空")
    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getPing() {
        return ping;
    }

    public void setPing(String ping) {
        this.ping = ping;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LoginForm{");
        sb.append("username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", captcha='").append(captcha).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
