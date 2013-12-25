package com.hermes.app.form;

/**
 * form 基类
 *
 * @author of644
 */
public class BaseForm {
    public String usercode;          // 用户编码
    public String username;          // 用户名

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
