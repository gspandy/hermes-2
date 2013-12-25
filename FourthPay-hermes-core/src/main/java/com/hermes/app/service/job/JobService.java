package com.hermes.app.service.job;

import com.hermes.app.domain.job.CashCfg;

import java.util.List;

/**
 * 任务
 * @author of644
 */
public interface JobService {

    /**
     * 提现配置
     * @param settleMode
     * @return
     */
    List<CashCfg> queryCashCfg(String settleMode);

    /**
     * 查询该商户前一天的交易额
     *
     * @param usercode
     * @return
     */
    String queryYesterdayTotal(String usercode);
}
