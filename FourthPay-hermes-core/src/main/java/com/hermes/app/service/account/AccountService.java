package com.hermes.app.service.account;


import com.hermes.app.domain.account.Account;
import com.hermes.app.domain.account.Announce;
import com.hermes.app.form.account.AccountForm;

import java.util.List;

/**
 * 账户
 * @author of644
 */
public interface AccountService {
    /**
     * 根据用户编号查询用户当日订单统计
     *
     * @param usercode
     * @return
     */
    Account queryStatsByCode(String usercode);

    /**
     * 修改密码(提现密码，交易密码)
     *
     * @param accountForm
     * @return
     */
    boolean updatePsw(AccountForm accountForm);

    /**
     * 修改登录密码
     *
     * @return
     */
    String updateLogPsw(AccountForm accountForm);

    /**
     * 更新账户信息
     *
     * @param accountForm
     * @return
     */
    boolean updateAccountInfo(AccountForm accountForm);

    /**
     * 最新公告
     *
     * @return
     */
    Announce queryLatestAnnounce();

    /**
     * 公告列表
     *
     * @return
     */
    List<Announce> queryAnnounces();
}
