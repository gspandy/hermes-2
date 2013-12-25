package com.hermes.app.service.impl.job;

import com.hermes.app.domain.job.CashCfg;
import com.hermes.app.persistence.pay.job.JobMapper;
import com.hermes.app.service.job.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 任务
 * @author of644
 */
@Service
public class JobServiceImpl implements JobService{

    @Autowired
    private JobMapper jobMapper;

    @Override
    public List<CashCfg> queryCashCfg(String settleMode) {
        return jobMapper.selectCashCfg(settleMode);
    }

    @Override
    public String queryYesterdayTotal(String usercode) {
        return jobMapper.selectYesterdayTotal(usercode);
    }
}
