package com.hermes.app.persistence.pay;

import com.hermes.app.form.order.RcvCardOrderForm;
import com.hermes.app.persistence.pay.order.CardOrderMapper;
import com.hermes.app.persistence.util.OraclePayMapperTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单管理mapper测试
 *
 * @author tujianying/of821
 */
public class CardOrderMapperTest extends OraclePayMapperTest {
    @Autowired
    private CardOrderMapper cardOrderMapper;

    @Test
    public void testSelectRcvCardOrders() throws Exception {
        RcvCardOrderForm form = new RcvCardOrderForm();
        form.setUsercode("A672353");
        form.setStartTime("2013-11-06 12:00:00");
        form.setEndTime("2013-11-06 12:10:00");
        form.setSortInfo("ordertime desc");
        form.setiDisplayStart(0);
        form.setiDisplayLength(10);
        Assert.assertEquals(1,cardOrderMapper.selectRcvCardOrders(form).size());
    }

    @Test
    public void testSelectRcvCardOrders_state() throws Exception {
        RcvCardOrderForm form = new RcvCardOrderForm();
        form.setUsercode("A672353");
        form.setStartTime("2013-11-06 12:00:00");
        form.setEndTime("2013-11-06 12:10:00");
        form.setSortInfo("ordertime desc");
        List<String> states = new ArrayList<String>();
        states.add("2");
        form.setStates(states);
        form.setiDisplayStart(0);
        form.setiDisplayLength(10);
        Assert.assertEquals(1,cardOrderMapper.selectRcvCardOrders(form).size());
    }

    @Test
    public void testSelectRcvCardOrders_state_fail() throws Exception {
        RcvCardOrderForm form = new RcvCardOrderForm();
        form.setUsercode("A672353");
        form.setStartTime("2013-11-06 12:00:00");
        form.setEndTime("2013-11-06 12:10:00");
        form.setSortInfo("ordertime desc");
        List<String> states = new ArrayList<String>();
        states.add("1");
        form.setStates(states);
        form.setiDisplayStart(0);
        form.setiDisplayLength(10);
        Assert.assertEquals(0,cardOrderMapper.selectRcvCardOrders(form).size());
    }

    @Test
    public void testCountRcvCardOrders() throws Exception {
        RcvCardOrderForm form = new RcvCardOrderForm();
        form.setUsercode("A672353");
        form.setStartTime("2013-11-06 12:00:00");
        form.setEndTime("2013-11-06 12:10:00");
        Assert.assertEquals(1,cardOrderMapper.countRcvCardOrders(form));
    }

    @Test
    public void testSelectTenpayOrders() throws Exception {
        RcvCardOrderForm form = new RcvCardOrderForm();
        form.setUsercode("A772139");
        form.setStartTime("2013-12-18");
        form.setEndTime("2013-12-19");
        Assert.assertEquals(1,cardOrderMapper.selectOuterOrders(form).size());
    }

    @Override
    public String getDataSetFileUrl() {
        return "dataset/persistence/cardorderset.xml";
    }
}
