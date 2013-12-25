package com.hermes.app.persistence.pay;

import com.hermes.app.domain.bill.Bill;
import com.hermes.app.domain.user.User;
import com.hermes.app.persistence.pay.bill.BillMapper;
import com.hermes.app.persistence.util.OraclePayMapperTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 用户mapper测试
 *
 * @author of644
 */
public class BillMapperTest extends OraclePayMapperTest {

    @Autowired
    private BillMapper billMapper;

    @Test
    public void testSelectBillByCodeInOneMonth() throws Exception {
        User user = new User();
        user.setUsercode("A752242");
        List<Bill> bills = billMapper.selectBillByCodeInOneMonth(user);
        Assert.assertEquals(1,bills.size());
    }

    @Override
    public String getDataSetFileUrl() {
        return "dataset/persistence/billset.xml";
    }
}
