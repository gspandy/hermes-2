package com.hermes.app.service.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.hermes.app.domain.ServiceResult;
import com.hermes.app.domain.user.User;
import com.hermes.app.form.base.LoginForm;
import com.hermes.app.persistence.pay.user.UserMapper;
import com.hermes.app.service.impl.user.UserServiceImpl;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;

import org.unitils.mock.Mock;

import java.util.HashMap;
import java.util.List;

/**
 * 测试userservice类
 *
 * @author tujianying/of821
 */
public class UserServiceImplTest extends UnitilsJUnit3 {
    //被测试的service对象
    @TestedObject
    UserService userService = new UserServiceImpl();
    //自动按照类型注入到被测试对象中
    @InjectIntoByType
    Mock<UserMapper> userMapperMock;
    //准备UserMapper返回的模拟对象数据
    @FileContent("/dataset/service/USER.js")
    private String usersJs;
    @FileContent("/dataset/service/USER_login.js")
    private String userloginJs;
    @FileContent("/dataset/service/USER_fail.js")
    private String userfailJs;
    @FileContent("/dataset/service/USER_fail3.js")
    private String userfail3Js;

    //各个测试用例共享的测试数据
    List<User> users;
    User user;
    User userFail;
    User userFail3;

    @Override
    public void setUp(){
        //密码统一为test
        user = JSON.parseObject(userloginJs, User.class);
        userFail = JSON.parseObject(userfailJs, User.class);
        userFail3 = JSON.parseObject(userfail3Js, User.class);
        List<User> users = JSON.parseObject(usersJs, new TypeReference<List<User>>(){});
    }

    //输入对象为空
    public void testLogin_fail1() throws Exception {
        LoginForm loginForm = null;
        ServiceResult serviceResult = userService.login(loginForm);
        assertFalse(serviceResult.isResult());
    }

    //用户名不存在
    public void testLogin_fail2() throws Exception {
        LoginForm loginForm = new LoginForm();
        loginForm.setUsername("test4");
        userMapperMock.returns(null).selectByName("test4");
        ServiceResult serviceResult = userService.login(loginForm);
        assertFalse(serviceResult.isResult());
    }

    //用户名状态不正确
    public void testLogin_fail3() throws Exception {
        LoginForm loginForm = new LoginForm();
        loginForm.setUsername("test11");
        userMapperMock.returns(userFail3).selectByName("test11");
        ServiceResult serviceResult = userService.login(loginForm);
        assertFalse(serviceResult.isResult());
    }

    //登入次数已经超过5次了，为了测试，将上一次用户的登入时间修改为未来时间“2020-10-10”
    public void testLogin_fail4() throws Exception {
        LoginForm loginForm = new LoginForm();
        loginForm.setUsername("test12");
        userMapperMock.returns(userFail).selectByName("test12");
        ServiceResult serviceResult = userService.login(loginForm);
        assertFalse(serviceResult.isResult());
    }

    //密码输入错误
    public void testLogin_fail5() throws Exception {
        LoginForm loginForm = new LoginForm();
        loginForm.setUsername("test12");
        loginForm.setPassword("123");
        userMapperMock.returns(user).selectByName("test12");
        ServiceResult serviceResult = userService.login(loginForm);
        assertFalse(serviceResult.isResult());
    }

    //成功登入  //"ofcardpay"
    public void testLogin_success() throws Exception {
        LoginForm loginForm = new LoginForm();
        loginForm.setUsername("tujianying2");
        loginForm.setPassword("05296b3e0aff3cec1a46efcbd055892fe8dc084437a484162b94806d9adea0fd");
        loginForm.setPing("VZszbB2hGT");
        userMapperMock.returns(user).selectByName("tujianying2");
        userMapperMock.returns(0).updateErrorNum(user);
        ServiceResult serviceResult = userService.login(loginForm);
        assertTrue(serviceResult.isResult());
    }

    //用户名存在saleuser表中
    public void testRegister_fail1() throws Exception {
        User user = new User();
        user.setUsername("registerfail1");
        userMapperMock.returns(1).selectSalesUserByName(user.getUsername());
        userMapperMock.returns(0).selectB2CUserByName(user.getUsername());
        ServiceResult serviceResult = userService.register(user);
        assertFalse(serviceResult.isResult());
    }

    //用户名存在B2CUser表中
    public void testRegister_fail2() throws Exception {
        User user = new User();
        user.setUsername("registerfail2");
        userMapperMock.returns(0).selectSalesUserByName(user.getUsername());
        userMapperMock.returns(1).selectB2CUserByName(user.getUsername());
        ServiceResult serviceResult = userService.register(user);
        assertFalse(serviceResult.isResult());
    }

    //注册成功
    public void testRegister_success() throws Exception {
        userMapperMock.returns(user).selectByName("tujianying2");
        userMapperMock.returns(1).insertSalesUser(user);
        userMapperMock.returns(1).insertPayUserAddedInfo(new HashMap());
        userMapperMock.returns(1).insertUserScoreInfo(new HashMap());
        ServiceResult serviceResult = userService.register(user);
        userMapperMock.returns("0").selectIntegralRule("3");
        assertTrue(serviceResult.isResult());
    }
}
