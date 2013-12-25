package com.hermes.app.service.cash;

import com.hermes.app.domain.cash.Bind;
import com.hermes.app.domain.cash.Order;
import com.hermes.app.domain.cash.UserCount;
import com.hermes.app.form.cash.ApplyForm;
import com.hermes.app.form.cash.BindForm;
import com.hermes.app.form.cash.OrderForm;
import com.hermes.app.web.util.PaginationSupport;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 提现相关的service
 *
 * @author of644
 */
public interface CashService {

    /**
     * 分页查找订单信息
     *
     * @param orderForm
     * @return
     */
    PaginationSupport<Order> queryOrders(OrderForm orderForm);

    /**
     * 用户绑定的银行卡信息
     * @param bindForm
     * @return
     */
    List<Bind> queryBinds(BindForm bindForm);

    /**
     * 银行账户绑定
     * @param bindForm
     * @return
     */
    int saveBind(BindForm bindForm);

    /**
     * 返回提现银行代码（下拉列表）
     *
     * @return
     */
    List<Map> queryBankCodes(String bankcode);

    /**
     * 删除银行账户
     *
     * @return
     */
    int removeBank(String id);

    /**
     * 根据用户编号获取用户余额
     *
     * @param usercode
     * @return
     */
    UserCount queryUserCountByCode(String usercode);

    /**
     * 查询商户提现费率
     *
     * @param applyForm
     * @return
     */
    BigDecimal queryRate(ApplyForm applyForm);

    /**
     * 更新用户余额
     *
     * @param applyForm
     * @return
     */
    int updateLeftMoney(ApplyForm applyForm);

    /**
     * 申请提现
     *
     * @param applyForm
     * @return
     */
    Boolean applyCash(ApplyForm applyForm);

    /**
     * 分割申请提现金额
     *
     *
     * @param applyMoney
     * @param split      按照多少钱来分割(50000)
     * @return
     */
    List<BigDecimal> getSplitMoneyList(String applyMoney, String split);

    /**
     * 计算手续费，保留小数点两位，四舍五入
     *
     *
     * @param applyMoney
     * @param rate
     * @return
     */
    BigDecimal getComision(BigDecimal applyMoney, BigDecimal rate);
}
