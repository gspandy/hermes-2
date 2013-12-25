package com.hermes.app.service.card;


import com.hermes.app.domain.card.Category;
import com.hermes.app.domain.order.RcvcardOrder;
import com.hermes.app.form.card.CardForm;
import com.hermes.app.form.card.CardResult;

import java.util.List;

/**
 * 收卡service
 * @author of644
 */
public interface CardService {

    /**
     * 支持提卡的类目
     *
     * @return
     */
    List<Category> queryCategory();

    /**
     * 支持面值
     *
     * @param corpcode
     * @return
     */
    List<Category> queryFaceValue(String corpcode);

    /**
     * 提交卡密
     *
     * @param cardForm
     * @return
     */
    CardResult postCard(CardForm cardForm);

    /**
     * 查询MD5key
     *
     * @param usercode
     * @return
     */
    String queryMd5key(String usercode);

    /**
     * 根据billid获取该卡的的相关信息
     * @param billid
     * @return
     */
    RcvcardOrder queryRcvcardBill(String billid);
}
