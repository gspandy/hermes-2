package com.hermes.app.persistence.pay.order;

import com.hermes.app.domain.order.DataAnalyseStatic;
import com.hermes.app.form.order.DataAnalyseForm;

import java.util.List;

/**
 * 收卡订单Mapper
 *
 * @author tujianying/of821
 */
public interface DataAnalyseMapper {
    /**
     * 根据条件查询收卡订单数据分析
     *
     * @param dataAnalyseForm
     * @return
     */
    List<DataAnalyseStatic> queryRcvCardStatic(DataAnalyseForm dataAnalyseForm);

    /**
     * 根据条件查询收卡订单数据分析的总个数
     *
     * @param dataAnalyseForm
     * @return
     */
    int countRcvCardStatic(DataAnalyseForm dataAnalyseForm);
}
