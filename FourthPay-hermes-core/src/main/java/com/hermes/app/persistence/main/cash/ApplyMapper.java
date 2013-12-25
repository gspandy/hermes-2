package com.hermes.app.persistence.main.cash;

import com.hermes.app.domain.cash.UserCount;
import com.hermes.app.form.cash.ApplyForm;

import java.math.BigDecimal;

/**
 * 申请提现Mapper
 *
 * @author of644
 */
public interface ApplyMapper {

    /**
     * 根据用户编码查询用户余额
     *
     * @param usercode
     * @return
     */
    UserCount selectUserCountByCode(String usercode);

    /**
     * 查询提现商户费率
     *
     * @param applyForm
     * @return
     */
    BigDecimal selectRate(ApplyForm applyForm);

    /**
     * 更新用户余额
     *
     * @param applyForm
     * @return
     */
    int updateLeftMoney(ApplyForm applyForm);

    /**
     * 插入提现订单
     *
     * @param applyForm
     * @return
     */
    int insertCashOrder(ApplyForm applyForm);

    /**
     * 查询提现序列
     *
     * @return
     */
    String selectSeq();
}
