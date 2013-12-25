package com.hermes.app.domain.account;

import java.io.Serializable;

/**
 * 账户
 * @author of644
 */
public class Account implements Serializable {
    private Double ordercount;
    private Double totalmoney;
    private Double succordercount;
    private Double succtotalmoney;

    public Double getOrdercount() {
        return ordercount;
    }

    public void setOrdercount(Double ordercount) {
        this.ordercount = ordercount;
    }

    public Double getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(Double totalmoney) {
        this.totalmoney = totalmoney;
    }

    public Double getSuccordercount() {
        return succordercount;
    }

    public void setSuccordercount(Double succordercount) {
        this.succordercount = succordercount;
    }

    public Double getSucctotalmoney() {
        return succtotalmoney;
    }

    public void setSucctotalmoney(Double succtotalmoney) {
        this.succtotalmoney = succtotalmoney;
    }
}
