package com.hermes.app.persistence.pay.order;

import com.hermes.app.domain.order.RcvcardOrder;
import com.hermes.app.form.order.RcvCardOrderForm;

import java.util.List;
import java.util.Map;

/**
 * 收卡订单Mapper
 *
 * @author tujianying/of821
 */
public interface CardOrderMapper {
    /**
     * 根据条件查询收卡订单，只取一部分信息
     *
     * @param rcvCardOrderForm
     * @return
     */
    List<RcvcardOrder> selectRcvCardOrders(RcvCardOrderForm rcvCardOrderForm);

    /**
     * 根据条件查询收卡订单，给导出excel用，取出时间段内的所有信息，没有进行分页
     *
     * @param rcvCardOrderForm
     * @return
     */
    List<RcvcardOrder> queryExportRcvCardOrders(RcvCardOrderForm rcvCardOrderForm);

    /**
     * 根据条件查询收卡订单的总个数
     *
     * @param rcvCardOrderForm
     * @return
     */
    int countRcvCardOrders(RcvCardOrderForm rcvCardOrderForm);

    /**
     * 财付通下载订单
     *
     * @param rcvCardOrderForm
     * @return
     */
    List<Map> selectOuterOrders(RcvCardOrderForm rcvCardOrderForm);
}
