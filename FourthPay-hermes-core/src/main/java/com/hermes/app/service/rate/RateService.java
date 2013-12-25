package com.hermes.app.service.rate;

import com.hermes.app.domain.rate.Rate;
import java.util.List;

/**
 * 支付费率相关的service
 *
 * @author tujianying/of821
 */
public interface RateService {
    /**
     * 查询用户的支付费率，只查询出该用户支持的卡类型
     * @param userCode
     * @return
     */
    List<Rate> queryRatesByUsercode(String userCode);
}
