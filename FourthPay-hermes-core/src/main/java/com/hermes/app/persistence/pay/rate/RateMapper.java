package com.hermes.app.persistence.pay.rate;

import com.hermes.app.domain.rate.Rate;
import java.util.List;

/**
 * 支付费率Mapper
 *
 * @author tujianying/of821
 */
public interface RateMapper {

    /**
     * 查询用户的支付费率，只查询出该用户支持的卡类型
     *
     * @param userCode
     * @return
     */
    List<Rate> selectRatesByUsercode(String userCode);
}
