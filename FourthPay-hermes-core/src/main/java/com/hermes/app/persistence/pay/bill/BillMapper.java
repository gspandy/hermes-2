package com.hermes.app.persistence.pay.bill;

import com.hermes.app.domain.bill.Bill;
import com.hermes.app.domain.user.User;

import java.util.List;

/**
 * 订单Mapper
 *
 * @author of644
 */
public interface BillMapper {

    /**
     * 用户最近一个月的订单信息(首页用)
     *
     * @param user
     * @return
     */
    List<Bill> selectBillByCodeInOneMonth(User user);


}
