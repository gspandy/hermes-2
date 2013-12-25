package com.hermes.app.service.impl.cash;


import com.hermes.app.domain.cash.Bind;
import com.hermes.app.domain.cash.Order;
import com.hermes.app.domain.cash.UserCount;
import com.hermes.app.form.cash.ApplyForm;
import com.hermes.app.form.cash.BindForm;
import com.hermes.app.form.cash.OrderForm;
import com.hermes.app.persistence.main.cash.ApplyMapper;
import com.hermes.app.persistence.main.cash.BindMapper;
import com.hermes.app.persistence.main.cash.OrderMapper;
import com.hermes.app.service.cash.CashService;
import com.hermes.app.web.util.PaginationSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 提现相关的service
 *
 * @author of644
 */
@Service
public class CashServiceImpl implements CashService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private BindMapper bindMapper;

    @Autowired
    private ApplyMapper applyMapper;

    @Override
    public PaginationSupport<Order> queryOrders(OrderForm orderForm) {
        List<Order> list = orderMapper.selectOrders(orderForm);
        int totalCount = orderMapper.countOrders(orderForm);
        return new PaginationSupport(list, totalCount, orderForm.getiDisplayLength(), orderForm.getiDisplayStart());
    }

    @Override
    public List<Bind> queryBinds(BindForm bindForm) {
        return bindMapper.selectBinds(bindForm);
    }

    @Override
    public int saveBind(BindForm bindForm) {
        return bindMapper.insertBind(bindForm);
    }

    @Override
    public List<Map> queryBankCodes(String bankcode) {
        return bindMapper.selectBankCodes(bankcode);
    }

    @Override
    public int removeBank(String id) {
        return bindMapper.deleteBind(id);
    }

    @Override
    public UserCount queryUserCountByCode(String usercode) {
        return applyMapper.selectUserCountByCode(usercode);
    }

    @Override
    public BigDecimal queryRate(ApplyForm applyForm) {
        return applyMapper.selectRate(applyForm);
    }

    @Override
    public int updateLeftMoney(ApplyForm applyForm) {
        return applyMapper.updateLeftMoney(applyForm);
    }

    @Override
    public Boolean applyCash(ApplyForm applyForm) {
        int count = applyMapper.updateLeftMoney(applyForm);
        if (count > 0) {
            applyForm.setCashorderno(createSeq());
            applyMapper.insertCashOrder(applyForm);
        }
        return true;
    }

    private String createSeq() {
        String seq = applyMapper.selectSeq();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 6 - seq.length(); i++) {
            stringBuffer.append("0");
        }
        seq = stringBuffer.append(seq).toString();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyMMdd");
        return "T" + sdf1.format(new Date()) + seq;
    }

    /**
     * 分割申请提现金额
     *
     *
     * @param applyMoney
     * @param split      按照多少钱来分割(50000)
     * @return
     */
    @Override
    public List<BigDecimal> getSplitMoneyList(String applyMoney, String split) {
        List<BigDecimal> smList = new ArrayList<BigDecimal>();
        BigDecimal tempMoney = new BigDecimal(applyMoney);
        BigDecimal splitMoney = new BigDecimal(split);
        // 分割
        while (tempMoney.compareTo(splitMoney) > 0) {
            tempMoney = tempMoney.subtract(splitMoney);
            smList.add(splitMoney);
        }
        smList.add(tempMoney);
        return smList;
    }

    /**
     * 计算手续费，保留小数点两位，四舍五入
     *
     *
     * @param applyMoney
     * @param rate
     * @return
     */
    @Override
    public BigDecimal getComision(BigDecimal applyMoney, BigDecimal rate) {
        return applyMoney.multiply(rate).divide(new BigDecimal(1000), 2, BigDecimal.ROUND_UP);
    }
}
