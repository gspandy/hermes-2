package com.hermes.app.exception;

import com.hermes.app.form.BaseForm;

/**
 * Created with IntelliJ IDEA.
 * User: ofcard
 * Date: 13-12-3
 * Time: 下午2:29
 * To change this template use File | Settings | File Templates.
 */
public class BussinessExceptionBuilder {
    private BaseForm form;
    private String errMsg;
    private String redirecturl;

    private BussinessExceptionBuilder() {
    }

    public static BussinessExceptionBuilder aBussinessException() {
        return new BussinessExceptionBuilder();
    }

    public BussinessExceptionBuilder withForm(BaseForm form) {
        this.form = form;
        return this;
    }

    public BussinessExceptionBuilder withErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }

    public BussinessExceptionBuilder withRedirecturl(String redirecturl) {
        this.redirecturl = redirecturl;
        return this;
    }

    public BussinessException build() {
        BussinessException bussinessException = new BussinessException();
        bussinessException.setForm(form);
        bussinessException.setErrMsg(errMsg);
        bussinessException.setRedirecturl(redirecturl);
        return bussinessException;
    }
}
