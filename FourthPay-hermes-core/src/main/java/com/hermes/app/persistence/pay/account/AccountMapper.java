package com.hermes.app.persistence.pay.account;


import com.hermes.app.domain.account.Account;
import com.hermes.app.domain.account.Announce;
import com.hermes.app.form.account.AccountForm;

import java.util.List;

/**
 * 账户
 * @author of644
 */
public interface AccountMapper {

    /**
     * 根据用户编号查询用户当日订单统计
     *
     * @param usercode
     * @return
     */
    Account selectStatsByCode(String usercode);

    /**
     * 修改密码(登录密码，提现密码，交易密码)
     *
     * @param accountForm
     * @return
     */
    int updatePsw(AccountForm accountForm);

    /**
     * 更新账户信息
     *
     * @param accountForm
     * @return
     */
    int updateAccountInfo(AccountForm accountForm);

    /**
     * 更新账户信息(企业信息)
     *
     * @param accountForm
     * @return
     */
    int updateAccountAddInfo(AccountForm accountForm);

    /**
     * 获取最新的公告
     *
     * @return
     */
    Announce selectLatestAnnounce();

    /**
     * 获取公告列表
     *
     * @return
     */
    List<Announce> selectAnnounces();
}
