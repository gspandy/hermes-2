package com.hermes.app.form.order;

import com.hermes.app.form.BaseTableForm;

import java.util.List;

/**
 * 收卡订单form
 *
 * @author tujianying/of821
 */
public class DataAnalyseForm extends BaseTableForm {
    private String startTime;    // 订单查询开始时间
    private String endTime;      //订单查询结束时间
    private String chargeStartTime;   // 交易查询开始时间
    private String chargeEndTime;  //交易查询结束时间

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getChargeStartTime() {
        return chargeStartTime;
    }

    public void setChargeStartTime(String chargeStartTime) {
        this.chargeStartTime = chargeStartTime;
    }

    public String getChargeEndTime() {
        return chargeEndTime;
    }

    public void setChargeEndTime(String chargeEndTime) {
        this.chargeEndTime = chargeEndTime;
    }
}
