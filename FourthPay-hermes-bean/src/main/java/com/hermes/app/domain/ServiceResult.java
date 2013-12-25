package com.hermes.app.domain;

/**
 * service返回的结果对象
 *
 * @author tujianying/of821
 */
public class ServiceResult {
    private boolean result;  //结果是成功还是失败
    private String errMsg;  //错误原因
    private Object resultObject; //由于某些原因，可能需要返回对象让web层进一步处理

    public ServiceResult(){}

    public ServiceResult(boolean result,  String errMsg, Object resultObject) {
        this.result = result;
        this.resultObject = resultObject;
        this.errMsg = errMsg;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public Object getResultObject() {
        return resultObject;
    }

    public void setResultObject(Object resultObject) {
        this.resultObject = resultObject;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
