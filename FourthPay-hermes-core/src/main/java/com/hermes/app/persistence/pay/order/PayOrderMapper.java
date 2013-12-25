package com.hermes.app.persistence.pay.order;

import com.hermes.app.domain.order.CashBankInfo;
import com.hermes.app.domain.order.PayOrder;
import com.hermes.app.form.order.PayOrderForm;

import java.util.List;

/**
 * 支付订单Mapper
 *
 * @author tujianying/of821
 */
public interface PayOrderMapper {
    /**
     * 查询出所有的银行信息
     *
     * @return
     */
    List<CashBankInfo> selectBackCodeList();

    /**
     * 根据条件查询支付订单，只取一部分信息
     *
     * @param payOrderForm
     * @return
     */
    List<PayOrder> selectPayOrders(PayOrderForm payOrderForm);

    /**
     * 根据条件查询支付订单的总个数
     *
     * @param payOrderForm
     * @return
     */
    int countPayOrders(PayOrderForm payOrderForm);

    /**
     * 根据条件查询支付订单，查询所有，用来导入到excel表格中
     *
     * @param payOrderForm
     * @return
     */
    List<PayOrder> queryExportPayOrders(PayOrderForm payOrderForm);
}
