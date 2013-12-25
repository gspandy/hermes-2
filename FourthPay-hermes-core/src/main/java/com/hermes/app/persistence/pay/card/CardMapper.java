package com.hermes.app.persistence.pay.card;

import com.hermes.app.domain.card.Category;
import com.hermes.app.domain.order.RcvcardOrder;

import java.util.List;

/**
 * 收卡Mapper
 *
 * @author of644
 */
public interface CardMapper {

    /**
     * 支持提卡的类目
     *
     * @return
     */
    List<Category> selectCategory();

    /**
     * 支持的面值
     *
     * @param corpcode
     * @return
     */
    List<Category> selectFaceValue(String corpcode);

    /**
     * 商户MD5key（收卡）
     *
     * @return
     */
    String selectMd5key(String usercode);

    /**
     * 根据billid获取该卡的的相关信息
     * @param billid
     * @return
     */
    RcvcardOrder selectRcvcardBill(String billid);
}
