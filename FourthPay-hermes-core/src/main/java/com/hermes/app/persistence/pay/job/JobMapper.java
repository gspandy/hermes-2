package com.hermes.app.persistence.pay.job;

import com.hermes.app.domain.job.CashCfg;

import java.util.List;

/**
 * 定时任务提现
 *
 * @author of644
 */
public interface JobMapper {

    /**
     * 定时提现配置
     *
     * @param settleMode
     * @return
     */
    List<CashCfg> selectCashCfg(String settleMode);

    /**
     * 查询该商户前一天的交易额
     *
     * @return
     */
    String selectYesterdayTotal(String usercode);
}
