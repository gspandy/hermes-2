package com.hermes.app.persistence.main;

import com.hermes.app.domain.cash.Order;
import com.hermes.app.form.cash.OrderForm;
import com.hermes.app.persistence.main.cash.OrderMapper;
import com.hermes.app.persistence.util.OracleMainMapperTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 用户mapper测试
 *
 * @author of644
 */
public class OrderMapperTest extends OracleMainMapperTest {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void testSelectOrders() throws Exception {
        OrderForm orderForm = new OrderForm();
        orderForm.setUsercode("A802651");
        orderForm.setStat("1");//成功
        orderForm.setApplyTimeStart("2013-06-30");
        orderForm.setApplyTimeEnd("2013-07-02");
        orderForm.setSortInfo("applytime desc");
        orderForm.setiDisplayStart(0);
        orderForm.setiDisplayLength(10);
        List<Order> orders = orderMapper.selectOrders(orderForm);
        Assert.assertEquals(3, orders.size());
    }

    @Test
    public void testCountOrders() throws Exception {
        OrderForm orderForm = new OrderForm();
        orderForm.setUsercode("A802651");
        orderForm.setStat("1");//成功
        orderForm.setApplyTimeStart("2013-06-30");
        orderForm.setApplyTimeEnd("2013-07-02");
        orderForm.setSortInfo("applytime desc");
        orderForm.setiDisplayStart(0);
        orderForm.setiDisplayLength(10);
        int count = orderMapper.countOrders(orderForm);
        Assert.assertEquals(3, count);
    }

    @Override
    public String getDataSetFileUrl() {
        return "dataset/persistence/cash_order_set.xml";
    }
}
