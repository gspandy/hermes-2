package com.hermes.app.persistence.pay;

import com.hermes.app.domain.job.CashCfg;
import com.hermes.app.persistence.pay.job.JobMapper;
import com.hermes.app.persistence.util.OraclePayMapperTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 任务
 *
 * @author of644
 */
public class JobMapperTest extends OraclePayMapperTest{
    @Autowired
    private JobMapper jobMapper;

    @Test
    public void testSelectCashCfg() throws Exception {
        List<CashCfg> cashCfgs = jobMapper.selectCashCfg("1");
        Assert.assertNotNull(cashCfgs);
    }

    @Override
    public String getDataSetFileUrl() {
        return "dataset/persistence/jobset.xml";
    }
}
