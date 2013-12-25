package com.hermes.app.form.cash;

import com.hermes.app.form.BaseTableForm;

/**
 * 提现订单form
 *
 * @author of644
 */
public class OrderForm extends BaseTableForm {
    /**
     * 提现列表展示
     */
    private String applyTimeStart;    // 查询开始时间
    private String applyTimeEnd;      // 查询结束时间
    private String cashOrderNo;       // 提现单号
    private String stat;              // 结果
    private String dateType;          // 日期类型：今天，最近一个月...


    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public String getApplyTimeStart() {
        return applyTimeStart;
    }

    public void setApplyTimeStart(String applyTimeStart) {
        this.applyTimeStart = applyTimeStart;
    }

    public String getApplyTimeEnd() {
        return applyTimeEnd;
    }

    public void setApplyTimeEnd(String applyTimeEnd) {
        this.applyTimeEnd = applyTimeEnd;
    }

    public String getCashOrderNo() {
        return cashOrderNo;
    }

    public void setCashOrderNo(String cashOrderNo) {
        this.cashOrderNo = cashOrderNo;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }
}
