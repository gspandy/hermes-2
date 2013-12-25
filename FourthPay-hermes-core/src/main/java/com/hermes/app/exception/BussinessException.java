package com.hermes.app.exception;


import com.hermes.app.form.BaseForm;

/**
 * 业务异常处理类
 *
 * @author of644
 */
public class BussinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private BaseForm form;
    private String errMsg;
    private String redirecturl;

    public String getRedirecturl() {
        return redirecturl;
    }

    public void setRedirecturl(String redirecturl) {
        this.redirecturl = redirecturl;
    }

    public BaseForm getForm() {
        return form;
    }

    public void setForm(BaseForm form) {
        this.form = form;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
