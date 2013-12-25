package com.hermes.app.service.order;

import com.hermes.app.domain.order.*;
import com.hermes.app.form.card.CardResult;
import com.hermes.app.form.order.DataAnalyseForm;
import com.hermes.app.form.order.PayOrderForm;
import com.hermes.app.form.order.RcvCardOrderForm;
import com.hermes.app.web.util.PaginationSupport;

import java.util.List;
import java.util.Map;

/**
 * 订单相关的service
 *
 * @author tujianying/of821
 */
public interface OrderService {
    /**
     * 查询收卡订单，包含界面分页信息，取出一部分
     * @param rcvCardOrderForm
     * @return
     */
    PaginationSupport<RcvcardOrder> queryRcvCardOrders(RcvCardOrderForm rcvCardOrderForm);

    /**
     * 查询收卡订单列表，
     * @param rcvCardOrderForm
     * @return
     */
    public List<RcvcardOrder> queryExportRcvCardOrders(RcvCardOrderForm rcvCardOrderForm);

    /**
     * 查询收卡订单数据分析
     * @param dataAnalyseForm
     * @return
     */
    PaginationSupport<DataAnalyseStatic> queryRcvCardStatic(DataAnalyseForm dataAnalyseForm);

    /**
     * 查询用户的密钥
     * @param usercode  用户编号
     * @return
     */
    String queryUserMd5key(String usercode);

    /**
     * 查询出所有的银行信息
     *
     * @return
     */
    List<CashBankInfo> selectBackCodeList();

    /**
     * 查询支付订单，包含界面分页信息，取出一部分
     * @param payOrderForm
     * @return
     */
    PaginationSupport<PayOrder> queryPayOrders(PayOrderForm payOrderForm);

    /**
     * 根据条件查询支付订单，查询所有，用来导入到excel表格中
     *
     * @param payOrderForm
     * @return
     */
    List<PayOrder> queryExportPayOrders(PayOrderForm payOrderForm);

    /**
     * 支付订单，回调商户
     * @param oforderno
     * @param usercode
     * @return
     */
    CallbackResult callbackMerchant(String oforderno, String usercode);

    /**
     * 收卡订单，回调商户
     * @param usercode
     * @param billid
     * @return
     */
    CardResult cardCallbackMerchant(String usercode, String billid);


    /**
     * 财付通下载订单
     *
     * @param rcvCardOrderForm
     * @return
     */
    public List<Map> queryOuterOrders(RcvCardOrderForm rcvCardOrderForm);
}
