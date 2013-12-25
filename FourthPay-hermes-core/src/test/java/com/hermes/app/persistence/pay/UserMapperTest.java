package com.hermes.app.persistence.pay;

import com.hermes.app.domain.user.User;
import com.hermes.app.persistence.pay.user.UserMapper;
import com.hermes.app.persistence.util.OraclePayMapperTest;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

/**
 * 用户mapper测试
 *
 * @author of644
 */
public class UserMapperTest extends OraclePayMapperTest {

    @Autowired
    private UserMapper UserMapper;

    @Test
    public void testSelectByName() throws Exception {
        String userName = "ofboss123";
        User user = UserMapper.selectByName(userName);
        Assert.assertNotNull(user);
    }

    @Test
    public void testUpdateUser() throws Exception {
        User user = new User();
        user.setUsercode("A745518");
        user.setUsercell("13100000000");
        user.setEmail("zzzz@163.com");
        user.setUsertel("02543210231");
        user.setQq("123456789");
        int count = UserMapper.updateUser(user);
        Assert.assertEquals(1, count);
    }

    @Test
    public void testSelectSalesUserByName() throws Exception {
        String userName = "ofboss123";
        int count = UserMapper.selectSalesUserByName(userName);
        Assert.assertEquals(1, count);
    }

    @Test
    public void testSelectB2CUserByName() throws Exception {
        String userName = "ofboss123111";
        int count = UserMapper.selectB2CUserByName(userName);
        Assert.assertEquals(0, count);
    }

//    @Test
//    public void testInsertSalesUser() throws Exception {
//        User user = new User();
//        user.setUsername("testusername");  //设置用户名
//        user.setPassword((DigestUtils.md5Hex("test"))); //设置密码
//        user.setUsercell("12345678989");  //设置手机号码
//        user.setEmail("123@qq.com"); //设置邮箱
//        user.setUsertel("02512345678");  //设置固话
//        user.setQq("888888");  //设置qq
//        user.setMerchantType("personal");   //设置商户类型 个人：personal 公司：company
//        user.setUserRealName("测试"); //设置用户的真实姓名为公司联系人
//        user.setIdentityCard("889989898989898899"); //设置个人身份证
//        user.setCompanyName("公司名"); //设置公司名称
//        user.setCompanyLicenseNumber("n123");  //设置公司执照号码
//        user.setPrvcin("江苏");  //设置省份
//        user.setCityin("南京");  //设置城市
//        user.setCountry("雨花区");  //设置所在区域
//        user.setLastip("10.10.10.10");  //设置上一次登入Ip
//        user.setCurip("10.10.10.10");   //设置当前Ip
//        user.setBelongTo("A00000");  //设置上级编号
//        user.setShopLevel("5");   //设置用户级别
//        user.setUserSourceFlag(1);  //设置用户类型能 1 欧飞用户 2 拍拍用户 3其他
//        user.setMerpayflag(0);  //设置是否支付商户
//        user.setUserTag("P");  //设置用户标识
//        user.setOpendeflev("1");
//        int count = UserMapper.insertSalesUser(user);
//        Assert.assertEquals(1, count);
//    }

    @Test
    public void testInsertPayUserAddedInfo() throws Exception {
        HashMap map = new HashMap();
        map.put("usercode", "A745518");  //商户编号
        map.put("paytype", "3");  //支付类型
        map.put("company", "测试公司");  //公司名字
        int count = UserMapper.insertPayUserAddedInfo(map);
        Assert.assertEquals(1, count);

    }

    @Test
    public void testSaveUserScoreInfo() throws Exception {
        HashMap paramMap = new HashMap();
        paramMap.put("Membercode", "A745518");
        paramMap.put("Membername", "测试");
        paramMap.put("Memberloginid", "ceshi");
        paramMap.put("Getscore", "0");
        paramMap.put("Wayid", "1");
        paramMap.put("Rulesid", "3");
        paramMap.put("Remarkinfo", "sale会员注册获取积分");
        paramMap.put("Datastat", "0");
        paramMap.put("Sourceflowid", "");
        paramMap.put("ipaddress", "10.10.10.10");

        int count = UserMapper.insertUserScoreInfo(paramMap);
        Assert.assertEquals(1, count);
    }

    @Test
    public void testSelectIntegralRule() throws Exception {
        String integral = UserMapper.selectIntegralRule("3");
        Assert.assertNull(integral);
    }

    @Override
    public String getDataSetFileUrl() {
        return "dataset/persistence/userset.xml";
    }

}
