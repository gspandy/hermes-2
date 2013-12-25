package com.hermes.app.persistence.main;

import com.hermes.app.domain.cash.UserCount;
import com.hermes.app.form.cash.ApplyForm;
import com.hermes.app.persistence.main.cash.ApplyMapper;
import com.hermes.app.persistence.util.OracleMainMapperTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

/**
 * 用户mapper测试
 *
 * @author of644
 */
public class ApplyMapperTest extends OracleMainMapperTest {

    @Autowired
    private ApplyMapper applyMapper;

    @Test
    public void testSelectLeftMoneyByCode() throws Exception {
        String usercode = "A672353";
        UserCount userCount = applyMapper.selectUserCountByCode(usercode);
        Assert.assertNotNull(userCount);
    }

    @Test
    public void testSelectRate() throws Exception {
        ApplyForm applyForm = new ApplyForm();
        applyForm.setUsercode("A771813");
        applyForm.setBankcode("0105");
        BigDecimal rate = applyMapper.selectRate(applyForm);
        Assert.assertNotNull(rate);
    }

    @Test
    public void testUpdateLeftMoney() throws Exception {
        ApplyForm applyForm = new ApplyForm();
        applyForm.setUsercode("A672353");
        applyForm.setDeduMoney("50.22");
        int count = applyMapper.updateLeftMoney(applyForm);
        Assert.assertEquals(count, 1);
    }

    @Override
    public String getDataSetFileUrl() {
        return "dataset/persistence/cash_apply_set.xml";
    }
}
