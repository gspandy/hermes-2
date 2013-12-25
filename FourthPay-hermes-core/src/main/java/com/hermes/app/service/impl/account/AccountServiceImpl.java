package com.hermes.app.service.impl.account;

import com.hermes.app.domain.account.Account;
import com.hermes.app.domain.account.Announce;
import com.hermes.app.form.account.AccountForm;
import com.hermes.app.persistence.pay.account.AccountMapper;
import com.hermes.app.service.account.AccountService;
import com.hermes.app.util.http.HttpClientSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 账户
 * @author of644
 */
@Service
public class AccountServiceImpl implements AccountService{

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private HttpClientSupport httpClientSupport;

    @Value("${saleapi.url}")
    private String url;

    @Override
    public Account queryStatsByCode(String usercode) {
        return accountMapper.selectStatsByCode(usercode);
    }

    @Override
    public boolean updatePsw(AccountForm accountForm) {
        return accountMapper.updatePsw(accountForm) == 1;
    }

    @Override
    public String updateLogPsw(AccountForm accountForm) {
        Map paramMap = new HashMap();
        paramMap.put("mode", "changeNewPwd");
        paramMap.put("usercode", accountForm.getUsercode());
        paramMap.put("oldpwd", accountForm.getLogpswBefore());
        paramMap.put("newpwd", accountForm.getLogpswNew());
//        paramMap.put("fromip", accountForm.getRemoteIp());
        paramMap.put("fromip", "172.17.4.31");

        logger.info("发送给saleapi的参数：{}", paramMap);
        String result = httpClientSupport.sendPost(url, paramMap);
        logger.info("saleapi返回的结果：{}", result);
        return result;
    }

    @Override
    public boolean updateAccountInfo(AccountForm accountForm) {
        return accountMapper.updateAccountInfo(accountForm) == 1
                && accountMapper.updateAccountAddInfo(accountForm) == 1;
    }

    @Override
    public Announce queryLatestAnnounce() {
        return accountMapper.selectLatestAnnounce();
    }

    @Override
    public List<Announce> queryAnnounces() {
        return accountMapper.selectAnnounces();
    }
}
