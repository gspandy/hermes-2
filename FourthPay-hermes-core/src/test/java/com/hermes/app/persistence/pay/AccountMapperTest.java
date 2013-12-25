package com.hermes.app.persistence.pay;

import com.hermes.app.domain.account.Account;
import com.hermes.app.form.account.AccountForm;
import com.hermes.app.persistence.pay.account.AccountMapper;
import com.hermes.app.persistence.util.OraclePayMapperTest;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户mapper测试
 *
 * @author of644
 */
public class AccountMapperTest extends OraclePayMapperTest {

    @Autowired
    private AccountMapper accountMapper;

    @Test
    public void testSelectStatsByCode() throws Exception {
        String usercode = "A672353";
        Account account = accountMapper.selectStatsByCode(usercode);
        Assert.assertNotNull(account);
        Assert.assertEquals(account.getOrdercount(), new Double(2.0));
    }

    @Test
    public void testUpdatePsw() throws Exception {
        AccountForm accountForm = new AccountForm();
        accountForm.setUsercode("A672353");
        accountForm.setLogpswNew(DigestUtils.md5Hex("123456"));
        accountForm.setPsw("xxxxx");
        int count1 = accountMapper.updatePsw(accountForm);
        accountForm.setLogpswNew(null);
        accountForm.setPaypwsNew(DigestUtils.md5Hex("123456"));
        int count2 = accountMapper.updatePsw(accountForm);
        accountForm.setPaypwsNew(null);
        accountForm.setUserfaxNew("123456");
        int count3 = accountMapper.updatePsw(accountForm);

        Assert.assertEquals(1, count1);
        Assert.assertEquals(1, count2);
        Assert.assertEquals(1, count3);
    }

    @Test
    public void testUpdateAccountInfo() throws Exception {
        AccountForm accountForm = new AccountForm();
        accountForm.setUsercode("A672353");
        accountForm.setUsercell("13800000000");
        accountForm.setCompany("ofpay");
        int count1 = accountMapper.updateAccountInfo(accountForm);
        int count2 = accountMapper.updateAccountAddInfo(accountForm);
        Assert.assertEquals(1, count1);
        Assert.assertEquals(1, count2);
    }

    @Override
    public String getDataSetFileUrl() {
        return "dataset/persistence/accountset.xml";
    }
}
