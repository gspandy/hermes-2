package com.hermes.app.persistence.pay;


import com.hermes.app.domain.card.Category;
import com.hermes.app.persistence.pay.card.CardMapper;
import com.hermes.app.persistence.util.OraclePayMapperTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 收卡测试
 * @author of644
 */
public class CardMapperTest extends OraclePayMapperTest {
    @Autowired
    private CardMapper cardMapper;

    @Test
    public void testSelectCategory() throws Exception {
        List<Category> categories = cardMapper.selectCategory();
        Assert.assertNotNull(categories);
    }

    @Test
    public void testSelectFaceValue() throws Exception {
        List<Category> faceValues = cardMapper.selectFaceValue("1");
        Assert.assertNotNull(faceValues);
    }

    @Override
    public String getDataSetFileUrl() {
        return "dataset/persistence/cardset.xml";
    }
}
