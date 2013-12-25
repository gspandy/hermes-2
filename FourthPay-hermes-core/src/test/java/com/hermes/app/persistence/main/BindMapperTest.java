package com.hermes.app.persistence.main;

import com.hermes.app.domain.cash.Bind;
import com.hermes.app.form.cash.BindForm;
import com.hermes.app.persistence.main.cash.BindMapper;
import com.hermes.app.persistence.util.OracleMainMapperTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 用户mapper测试
 *
 * @author of644
 */
public class BindMapperTest extends OracleMainMapperTest {

    @Autowired
    private BindMapper bindMapper;

    @Test
    public void testSelectBindByCode() throws Exception {
        BindForm bindForm = new BindForm();
        bindForm.setUsercode("A771813");
        List<Bind> orders = bindMapper.selectBinds(bindForm);
        Assert.assertEquals(2, orders.size());
    }

    @Test
    public void testInsertBind() throws Exception {
        BindForm bindForm = new BindForm();
        bindForm.setAccountflowid(UUID.randomUUID().toString().replaceAll("-", ""));
        bindForm.setUsercode("A891692");
        bindForm.setUsername("zjh459");
        bindForm.setAccounttype("1");//银行
        bindForm.setBankcode("0105");
        bindForm.setBankname("招商银行");
        bindForm.setAccountusername("福州网福信息技术有限公司");
        bindForm.setAccountno("125903807510206");
        bindForm.setDatastat("1");//处理中
        bindForm.setAccshortname("测试简称");
        bindForm.setRemarkinfo("测试");

        int count = bindMapper.insertBind(bindForm);

        Assert.assertEquals(1, count);
    }

    @Test
    public void testSelectBankCodes() throws Exception {
        List<Map> bankcodes = bindMapper.selectBankCodes(null);
        Assert.assertNotNull(bankcodes);
        Assert.assertTrue(bankcodes.size() > 0);

        List<Map> bankcodes2 = bindMapper.selectBankCodes("0201");
        Assert.assertEquals(bankcodes2.size(), 1);
    }

    @Test
    public void testDeleteBind() throws Exception {
        String id = "6bfe336fbbb33169503f0f38da9e9a1d";
        int count = bindMapper.deleteBind(id);
        Assert.assertTrue(count > 0);
    }

    @Override
    public String getDataSetFileUrl() {
        return "dataset/persistence/cash_bind_set.xml";
    }
}
