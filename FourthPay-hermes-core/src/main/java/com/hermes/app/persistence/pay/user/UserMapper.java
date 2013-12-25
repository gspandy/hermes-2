package com.hermes.app.persistence.pay.user;

import com.hermes.app.domain.user.User;
import com.hermes.app.domain.user.UserAdd;

import java.util.HashMap;

/**
 * 用户Mapper
 *
 * @author of644
 */
public interface UserMapper {

    /**
     * 根据用户名查询用户信息（登录用）
     *
     * @param username
     * @return
     */
    User selectByName(String username);

    /**
     * 更新用户登录失败的次数（登录用）
     *
     * @param user
     * @return
     */
    int updateErrorNum(User user);

    /**
     * 更新用户登录时间
     *
     * @param user
     * @return
     */
    int updateLoginTime(User user);

    /**
     * 根据用户编号查询用户信息
     *
     * @param usercode
     * @return
     */
    User selectByCode(String usercode);

    /**
     * 根据用户编号查询用户信息(企业信息)
     *
     * @param usercode
     * @return
     */
    UserAdd selectAddByCode(String usercode);


    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 查询saleuser表中该用户存在的个数
     * @param username
     * @return
     */
    int selectSalesUserByName(String username);

    /**
     * 查询reguser表中该用户存在的个数
     * @return
     */
    int selectB2CUserByName(String username);

    /**
     * 插入注册的数据到数据库中
     * @param user
     * @return
     */
    int insertSalesUser(User user);

    /**
     * 查询数据库执行的序列号，用来唯一标识usercode
     * @return
     */
    String selectSeqCode();

    /**
     * 注册的时候，插入支付方式信息到数据库中tb_pay_useraddedinfo
     * @param map
     * @return
     */
    int insertPayUserAddedInfo(HashMap map);

    /**
     * 注册的时候，插入用户积分信息
     * @param map
     * @return
     */
    int insertUserScoreInfo(HashMap map);
    /**
     * 获取积分规则
     * @param rulesid
     * @return
     */
    String selectIntegralRule(String rulesid);

    /**
     * 注册的时候，插入salecount
     * @param usercode
     * @return
     */
    int insertSaleCount(String usercode);

    /**
     * 注册的时候，插入user_buy_info
     * @param map
     * @return
     */
    int insertUserBuyInfo(HashMap map);
}
