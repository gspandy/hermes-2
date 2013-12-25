package com.hermes.app.domain.order;

/**
 * 回调结果类
 *
 * @author  tujianying/of821
 */
public class CallbackResult {
        private String result;
        private String msg;

    public CallbackResult(){}

    public CallbackResult(String result, String msg) {
        this.result = result;
        this.msg = msg;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
