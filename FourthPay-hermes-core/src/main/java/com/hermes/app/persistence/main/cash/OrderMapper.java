package com.hermes.app.persistence.main.cash;

import com.hermes.app.domain.cash.Order;
import com.hermes.app.form.cash.OrderForm;

import java.util.List;

/**
 * 提现订单管理
 *
 * @author of644
 */
public interface OrderMapper {

    /**
     * 查询提现订单详情
     *
     * @param orderForm
     * @return
     */
    List<Order> selectOrders(OrderForm orderForm);

    /**
     * 订单条数
     *
     * @param orderForm
     * @return
     */
    int countOrders(OrderForm orderForm);
}
