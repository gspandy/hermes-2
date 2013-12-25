package com.hermes.app.service.impl.user;

import com.hermes.app.domain.ServiceResult;
import com.hermes.app.domain.user.User;
import com.hermes.app.domain.user.UserAdd;
import com.hermes.app.form.base.LoginForm;
import com.hermes.app.persistence.pay.user.UserMapper;
import com.hermes.app.service.user.UserService;
import com.hermes.app.util.RSAUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

/**
 * 涉及用户相关的service
 *
 * @author tujianying/of821
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 登入
     * @param loginForm
     * @return
     */
    public ServiceResult login(LoginForm loginForm) {
        //基本参数校验
        ServiceResult validatResult = loginValidator(loginForm);
        if(!validatResult.isResult()){
            return validatResult;
        }

        // 1.查询用户信息
        User user = (User)validatResult.getResultObject();
        // 2.解密用户密码
        String dbpwd = user.getPassword();
        String usercode = user.getUsercode();
        // RSA解密
        String rsastr = RSAUtil.getDecryptString(loginForm.getPassword());
        // ping随机串
        String ping = loginForm.getPing();
        String tempflag = user.getTempflag();
        String psw = user.getPsw();
        int errlognum = user.getErrlognum();

        ServiceResult result = null;
        if (!checkPwd(rsastr, dbpwd, usercode, psw, tempflag, ping)) {
            // 先更新错误次数
            int newerrlognum = errlognum + 1;
            user.setErrlognum(newerrlognum);
            userMapper.updateErrorNum(user);
            result = new ServiceResult(false, "用户名或密码错误", user);
        } else {
            result = new ServiceResult(true, "", user);
            // 初始化错误次数 0
            user.setErrlognum(0);
            user.setLastip(loginForm.getLastip());
            userMapper.updateLoginTime(user);
        }
        return result;
    }

    /**
     * 注册
     * @param user
     * @return
     */
    public ServiceResult register(User user){
        //基本参数校验，有错误直接返回错误
        ServiceResult validatResult = registerValidator(user);
        if(!validatResult.isResult()){
            return validatResult;
        }

        ServiceResult result = null;
        String passMd5=DigestUtils.md5Hex(user.getPassword());
        user.setPassword(passMd5);  //设置加密后的密码到数据库中
        user.setPaypws(passMd5);
        user.setBelongTo("A00000");  //设置上级编号

        user.setShopLevel("5");   //设置用户级别
        user.setUserSourceFlag(1);  //设置用户类型能 1 欧飞用户 2 拍拍用户 3其他
        user.setMerpayflag(0);  //设置是否支付商户
        user.setUserTag("P");  //设置用户标识？？？tjy
        user.setOpendeflev("1");  //这个干吗用的？？？tjy

        //保存信息到saleuser数据库中
        userMapper.insertSalesUser(user);

        //保存salecount
        saveSaleCount(user.getUsercode());

        //保存user_buy_info
        saveUserBuyInfo(user.getUsercode());

        //保存用户注册的其他信息tb_pay_useraddedinfo
        savePayUserInfo(user);

        //创建用户积分表
        saveUserScoreInfo(user);
        return new ServiceResult(true, null, user);
    }

    /**
     * servie层登入校验
     * @param loginForm
     * @return
     */
    private ServiceResult loginValidator(LoginForm loginForm)
    {
        ServiceResult result = null;

        if(null == loginForm){
            return new ServiceResult(false, "用户输入参数错误", null);
        }

        //查询用户信息
        User user = userMapper.selectByName(loginForm.getUsername());

        //判断用户名是否存在
        if(null == user){
            return new ServiceResult(false, "用户名不存在", null);
        }

        //判断帐户是否通过验证
        if ("-1".equals(user.getUserStat())) {
            return new ServiceResult(false, "帐户是否通过验证", user);
        }

        //刷新登入次数或者直接返回错误
        //5次登入失败，便会锁2个小时
        int errlognum = user.getErrlognum();
        if ( errlognum > 0) {
            Date dt = new Date();
            long nowtime = dt.getTime();
            long errtime = user.getErrlogtime().getTime();
            long leftjoin = (nowtime - errtime) / 3600000L;

            if (leftjoin >= 2L) {
                user.setErrlognum(0);
                userMapper.updateErrorNum(user);
            } else if (errlognum > 5) {
                return new ServiceResult(false, "登录错误次数已达到最大值", user);
            }
        }

        return new ServiceResult(true, null, user);
    }

    /**
     * 注册校验
     * @param user
     * @return
     */
    private ServiceResult registerValidator(User user)
    {
        ServiceResult result = null;
        //用户名已经被使用
        if(isExistUserName(user.getUsername())){
            return new ServiceResult(false, "用户名已经被使用", user);
        }

        return new ServiceResult(true, null, user);
    }

    /**
     * 检查密码是否正确
     * @param rsastr
     * @param dbpwd
     * @param usercode
     * @param psw
     * @param tempflag
     * @param ping
     * @return
     */
    private boolean checkPwd(String rsastr, String dbpwd, String usercode, String psw, String tempflag, String ping) {

        if (StringUtils.isBlank(rsastr) || StringUtils.isBlank(dbpwd)) {
            return false;
        }

        if (!("1".equals(tempflag))) {
            rsastr = DigestUtils.md5Hex(DigestUtils.md5Hex(rsastr) + ping);
           return rsastr.equals(DigestUtils.md5Hex(dbpwd + ping));
        }

        rsastr = DigestUtils.md5Hex(usercode + DigestUtils.md5Hex(rsastr) + psw);
        return rsastr.equals(dbpwd);
    }

    /**
     *保存信息到saleuser数据库中
     * @param user
     */
    private void savePayUserInfo(User user){
        HashMap map = new HashMap();
        User newUser = userMapper.selectByName(user.getUsername());
        map.put("usercode", newUser.getUsercode());  //商户编号
        map.put("paytype", "3");  //支付类型
        if(user.getMerchantType().equals("personal")){
            map.put("company", user.getUsername());  //公司名字
        }
        else
        {
            map.put("company", user.getCompanyName());
        }
        userMapper.insertPayUserAddedInfo(map);
    }

    /**
     * 创建用户积分表数据TB_Mall_MemGetScore
     */
    private void saveUserScoreInfo(User user){
        //获取积分规则
        String integral = userMapper.selectIntegralRule("3");
        if (integral == null || integral.equals("")) {
            integral = "0";
        }

        HashMap paramMap = new HashMap();
        paramMap.put("Membercode", user.getUsercode());
        paramMap.put("Membername", user.getUserRealName());
        paramMap.put("Memberloginid", user.getUsername());
        paramMap.put("Getscore", integral);
        paramMap.put("Wayid", "1");
        paramMap.put("Rulesid", "3");
        paramMap.put("Remarkinfo", "sale会员注册获取积分");
        paramMap.put("Datastat", "0");
        paramMap.put("Sourceflowid", "");
        paramMap.put("ipaddress", user.getLastip());

        userMapper.insertUserScoreInfo(paramMap);

    }

    /**
     * 注册的时候，插入salecount
     * @param usercode
     * @return
     */
    private void saveSaleCount(String usercode){
        userMapper.insertSaleCount(usercode);
    }

    /**
     * 注册的时候，插入user_buy_info
     * @param usercode
     * @return
     */
    private void saveUserBuyInfo(String usercode){
        HashMap paramMap = new HashMap();
        paramMap.put("buynumber", "0");
        paramMap.put("userid", usercode);
        paramMap.put("sumcash", "0");

        userMapper.insertUserBuyInfo(paramMap);
    }

    /**
     * 检查用户名是否已经存在
     *
     * @param userName 用户名
     * @return boolean 是否存在 true：存在  false：不存在
     */
    public boolean isExistUserName(String userName)
    {
        //根据用户名查询用户信息
        int salesUserCount = userMapper.selectSalesUserByName(userName);
        int b2cUserCount = userMapper.selectB2CUserByName(userName);

        //没有查询到返回false
        if(salesUserCount > 0 || b2cUserCount > 0){
            return true;
        }
        return false;
    }

    /**
     * 根据usercode获取用户信息
     * @param usercode
     * @return
     */
    public User queryByCode(String usercode){
        return userMapper.selectByCode(usercode);
    }

    /**
     * 根据usercode获取用户信息
     * @param usercode
     * @return
     */
    public UserAdd queryAddByCode(String usercode){
        return userMapper.selectAddByCode(usercode);
    }
}
