package com.hermes.app.persistence.main.cash;

import com.hermes.app.domain.cash.Bind;
import com.hermes.app.form.cash.BindForm;

import java.util.List;
import java.util.Map;

/**
 * 银行绑定
 * @author of644
 */
public interface BindMapper {

    /**
     * 根据用户编号查询用户已绑定的银行卡信息
     *
     * @param bindForm
     * @return
     */
    List<Bind> selectBinds(BindForm bindForm);

    /**
     * 添加银行绑定信息
     *
     * @param bindForm
     * @return
     */
    int insertBind(BindForm bindForm);

    /**
     * 获取支持提现的列表（下拉列表）
     *
     * @return
     */
    List<Map> selectBankCodes(String bankcode);

    /**
     * 删除银行卡信息
     *
     * @param id
     * @return
     */
    int deleteBind(String id);
}
