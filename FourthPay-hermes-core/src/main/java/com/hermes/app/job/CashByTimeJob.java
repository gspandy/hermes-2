package com.hermes.app.job;

import com.hermes.app.domain.cash.Bind;
import com.hermes.app.domain.cash.UserCount;
import com.hermes.app.domain.job.CashCfg;
import com.hermes.app.domain.user.User;
import com.hermes.app.form.builder.ApplyFormBuilder;
import com.hermes.app.form.builder.BindFormBuilder;
import com.hermes.app.form.cash.ApplyForm;
import com.hermes.app.form.cash.BindForm;
import com.hermes.app.service.cash.CashService;
import com.hermes.app.service.job.JobService;
import com.hermes.app.service.user.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 定时定额提现
 *
 * @author of644
 */
public class CashByTimeJob implements Serializable {

    protected final transient Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JobService jobService;

    @Autowired
    private UserService userService;

    @Autowired
    private CashService cashService;

    @Value("${job.on}")
    private boolean isJobOn;

    /**
     * 提现
     *
     * @param type 1:每天1:00，提现昨天的交易额，2:定额提现，提取用户全部余额，3：每天0:00，提取用户全部余额
     */
    public void work(String type) {
        // 开关
        if(!isJobOn){
            logger.info("定时任务开关：{}", isJobOn);
            return;
        }

        //
        List<CashCfg> cashCfgs = jobService.queryCashCfg(type);

        for (CashCfg cashCfg : cashCfgs) {
            try {
                // 用户编码
                String usercode = cashCfg.getUsercode();
                User user = userService.queryByCode(usercode);

                if ("0".equals(user.getLocked())) {
                    logger.info("{} 账户被锁定，不做提现！", usercode);
                    continue;
                }

                // 用户余额
                UserCount userCount = cashService.queryUserCountByCode(usercode);
                String leftMoney = userCount.getMerleftmoney();
                if (StringUtils.isEmpty(leftMoney) || "0".equals(leftMoney)) {
                    logger.info("{} 帐户余额为 {} ，不做提现！", usercode, leftMoney);
                    continue;
                }

                // 银行信息
                BindForm bindForm = BindFormBuilder.aBindForm()
                        .withUsercode(usercode)
                        .withAccountflowid(cashCfg.getAccountflowid())
                        .build();
                List<Bind> bindBanks = cashService.queryBinds(bindForm);

                // 绑定银行信息是否存在
                if (CollectionUtils.isEmpty(bindBanks) || bindBanks.size() < 1) {
                    logger.info("{} 没有绑定的银行信息!", usercode);
                    continue;
                }
                Bind bindBank = bindBanks.get(0);
                //
                ApplyForm applyForm = ApplyFormBuilder.anApplyForm()
                        // 用户信息
                        .withUsercode(user.getUsercode())
                        .withUsername(user.getUsername())
                                // 用户余额
                        .withMerleftmoney(leftMoney)
                                // 银行信息
                        .withBankcode(bindBank.getBankcode())
                        .withBankname(bindBank.getBankname())
                        .withAccountusername(bindBank.getAccountusername())
                        .withAccountno(bindBank.getAccountno())
                        .withAccshortname(bindBank.getAccshortname())
                                // 1：申请冻结资金提现 2：申请冻结资金转信用点
                        .withApplytype("1")
                                // 0：未处理1:已发送交易2：交易成功3：交易失败
                        .withDealstat("0")
                                // 订单来源 9：第四方商户
                        .withOrdersource("9")
                        .withAccounttype("1")//没用到了
                        .build();

                // 费率
                BigDecimal rate = cashService.queryRate(applyForm);
                if (rate == null) {
                    logger.info("{} 没有设置费率，不能提现", usercode);
                    continue;
                }
                applyForm.setRate(rate.toString());

                // 配置的结算阀值
                BigDecimal settleAmount = (StringUtils.isEmpty(cashCfg.getSettleamount())) ?
                        new BigDecimal(0) : new BigDecimal(cashCfg.getSettleamount());
//                // 计算提现金额
//                if (!calcApplyMoney(type, settleAmount, applyForm)) {
//                    continue;
//                }

                // 用户余额
                BigDecimal leftMoneyDecimal = new BigDecimal(applyForm.getMerleftmoney());
                BigDecimal applyMoney = BigDecimal.ZERO;
                // 定时提现
                if ("1".equals(type)) {
                    // 定时提前前一天交易额
                    String yesterdayTotal = jobService.queryYesterdayTotal(applyForm.getUsercode());

                    BigDecimal yesterdayTrade = (StringUtils.isEmpty(yesterdayTotal)) ? new BigDecimal(0)
                            : new BigDecimal(yesterdayTotal);

                    logger.info("{} 当前余额：{}", applyForm.getUsercode(), applyForm.getMerleftmoney());
                    logger.info("{} 昨日收入金额：{}", applyForm.getUsercode(), yesterdayTrade);

                    // 收入不足阀值，不提现
                    if (yesterdayTrade.compareTo(settleAmount) < 0) {
                        logger.info("{} 昨日收入不足额度设置，不做提现！", applyForm.getUsercode());
                        continue;
                    }
                    applyMoney = yesterdayTrade;
                }
                // 零点提现 & 定额提现
                else if ("3".equals(type) || "2".equals(type)) {
                    // 余额不足阀值，不提现
                    if (leftMoneyDecimal.compareTo(settleAmount) < 0) {
                        logger.info("{} 昨日收入不足额度设置，不做提现！", applyForm.getUsercode());
                        continue;
                    }
                    applyMoney = leftMoneyDecimal;
                }

                // 自动审核
                if (applyMoney.compareTo(new BigDecimal("500")) >= 0) {
                    // 1：正常订单 2：审核通过 3：审核不通过
                    applyForm.setDatastat("2");
                    // 操作信息
                    applyForm.setApprovemanname("system");
                    applyForm.setApproveinfo(applyForm.getUsercode() + "提现系统自动审核");
                } else {
                    // 1：正常订单 2：审核通过 3：审核不通过
                    applyForm.setDatastat("1");
                    // 操作信息
                    applyForm.setApprovemanname("");
                    applyForm.setApproveinfo("");
                }

                // 特殊逻辑（快钱不拆单）
                if ("A771813".equals(usercode)) {
                    if (!calcAndBindMoney(applyMoney, leftMoneyDecimal, type, applyForm)) {
                        continue;
                    }
                    cashService.applyCash(applyForm);
                }
                // 拆单
                else {
                    // 拆分订单，大于50000
                    List<BigDecimal> splitMoneyList = cashService.getSplitMoneyList(applyMoney.toString(), ApplyForm.SPLIT);

                    // 如果金额大于5W的则分批生成提现单；每批单独减动手续费；
                    if (!splitMoneyList.isEmpty() && splitMoneyList.size() > 0) {
                        for (BigDecimal splitMoney : splitMoneyList) {
                            // 重新计算提现金额
                            if (!calcAndBindMoney(splitMoney, leftMoneyDecimal, type, applyForm)) {
                                continue;
                            }
                            cashService.applyCash(applyForm);
                        }
                    }
                }
                logger.info("用户：{}，用户名：{} 成功提现 {}元", applyForm.getUsercode(), applyForm.getUsername(), applyForm.getApplyMoney());
            } catch (Exception e) {
                logger.error(new StringBuilder().append("用户：").append(cashCfg.getUsercode()).append("，定时提现异常 ;").toString(), e);
                continue;
            }
        }
    }

    /**
     *
     *
     * @param applyMoney
     * @param leftMoneyDecimal
     * @param type
     * @param applyForm
     * @return
     */
    private boolean calcAndBindMoney(BigDecimal applyMoney, BigDecimal leftMoneyDecimal, String type, ApplyForm applyForm) {
        BigDecimal deduMoney;
        BigDecimal merleftmoneyafter;
        // 手续费
        BigDecimal commision = cashService.getComision(applyMoney, new BigDecimal(applyForm.getRate()));
        // 如果是提现前一天交易额，则要加上手续费，并保证余额足够
        if ("1".equals(type)) {
            // 需扣除金额
            deduMoney = applyMoney.add(commision);
            // 帐户当前交易剩余金额
            merleftmoneyafter = leftMoneyDecimal.subtract(deduMoney);
        }
        // 如果是零点提现或者定额提现，由于提取的是用户余额，则不加手续费，由账务系统直接计算
        else {
            // 需扣除金额
            deduMoney = applyMoney;
            // 帐户当前交易剩余金额
            merleftmoneyafter = leftMoneyDecimal.subtract(deduMoney);
        }

        if (merleftmoneyafter.compareTo(BigDecimal.ZERO) < 0) {
            logger.info("{} 用户余额不足，余额：{}，手续费：{}，不做提现！", applyForm.getUsercode(), leftMoneyDecimal, commision);
            return false;
        }

        // 先从支付账户中减去支付金额和手续费
        applyForm.setDeduMoney(deduMoney.toString());
        applyForm.setApplyMoney(applyMoney.toString());
        applyForm.setCommision(commision.toString());
        applyForm.setFeerate(applyForm.getRate());
        applyForm.setMerleftmoney(leftMoneyDecimal.toString());
        applyForm.setMerleftmoneyafter(merleftmoneyafter.toString());

        return true;
    }
}
