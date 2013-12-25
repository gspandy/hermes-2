package com.hermes.app.service.impl.rate;


import com.hermes.app.domain.cash.Bind;
import com.hermes.app.domain.cash.Order;
import com.hermes.app.domain.rate.Rate;
import com.hermes.app.form.cash.OrderForm;
import com.hermes.app.persistence.main.cash.BindMapper;
import com.hermes.app.persistence.main.cash.OrderMapper;
import com.hermes.app.persistence.pay.rate.RateMapper;
import com.hermes.app.service.cash.CashService;
import com.hermes.app.service.rate.RateService;
import com.hermes.app.web.util.PaginationSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 提现相关的service
 *
 * @author of644
 *
 */
@Service
public class RateServiceImpl implements RateService {
    @Autowired
    private RateMapper rateMapper;

    /**
     * 查询用户的支付费率，只查询出该用户支持的卡类型
     * @param userCode
     * @return
     */
    public List<Rate> queryRatesByUsercode(String userCode) {
        return rateMapper.selectRatesByUsercode(userCode);
    }
}
