package com.hermes.app.persistence.pay;

import com.hermes.app.domain.rate.Rate;
import com.hermes.app.persistence.pay.rate.RateMapper;
import com.hermes.app.persistence.util.OraclePayMapperTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 支付费率mapper测试
 *
 * @author tujianying/of821
 */
public class RateMapperTest extends OraclePayMapperTest {

    @Autowired
    private RateMapper rateMapper;

    @Test
    public void testSelectBillByCodeInOneMonth() throws Exception {
        List<Rate> rates = rateMapper.selectRatesByUsercode("A889951");
        Assert.assertEquals(1,rates.size());
    }

    @Override
    public String getDataSetFileUrl() {
        return "dataset/persistence/rateset.xml";
    }
}
