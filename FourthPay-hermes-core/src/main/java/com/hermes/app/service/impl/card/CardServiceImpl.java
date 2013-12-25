package com.hermes.app.service.impl.card;

import com.hermes.app.domain.card.Category;
import com.hermes.app.domain.order.RcvcardOrder;
import com.hermes.app.form.card.CardForm;
import com.hermes.app.form.card.CardResult;
import com.hermes.app.persistence.pay.card.CardMapper;
import com.hermes.app.service.card.CardService;
import com.hermes.app.util.Flowids;
import com.hermes.app.util.http.HttpClientSupport;
import com.hermes.app.web.util.DateUtil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.mapper.MapperWrapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 收卡service
 *
 * @author of644
 */
@Service
public class CardServiceImpl implements CardService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CardMapper cardMapper;

    @Autowired
    private HttpClientSupport httpClientSupport;

    @Value("${card.mode}")
    private String mode;

    @Value("${card.version}")
    private String version;

    @Value("${card.format}")
    private String format;

    @Value("${card.retaction}")
    private String retaction;

    @Value("${card.url}")
    private String url;

    @Override
    public List<Category> queryCategory() {
        return cardMapper.selectCategory();
    }

    @Override
    public List<Category> queryFaceValue(String corpcode) {
        return cardMapper.selectFaceValue(corpcode);
    }

    @Override
    public CardResult postCard(CardForm cardForm) {

        String usercode = cardForm.getUsercode();
        String datetime = DateUtil.getDateString(new Date(), "yyyyMMddHHmmss");
        String orderno = Flowids.createFlowid();
        String cardcode = cardForm.getCardcode();
        String cardno = cardForm.getCardno();
        String cardpass = cardForm.getCardpass();
        String md5src = new StringBuilder().append(usercode).append(mode).append(version).
                append(orderno).append(cardcode).append(cardno).append(cardpass).
                append(retaction).append(datetime).append(format).toString();
        logger.info("提交收卡支付申请:" + md5src);
        String sign = DigestUtils.md5Hex(md5src + cardForm.getMd5key());

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("mode", mode);
        paramMap.put("version", version);
        paramMap.put("usercode", usercode);
        paramMap.put("orderno", orderno);
        paramMap.put("cardcode", cardcode);
        paramMap.put("cardno", cardno);
        paramMap.put("cardpass", cardpass);
        paramMap.put("retaction", retaction);
        paramMap.put("format", format);
        paramMap.put("datetime", datetime);
        paramMap.put("sign", sign);
        logger.info("发送给收卡的参数：{}", paramMap);
        String returnStr = httpClientSupport.sendPost(url, paramMap);
        logger.info("收卡接口返回：{}", returnStr);
        return getCardResult(returnStr);
    }

    @Override
    public String queryMd5key(String usercode) {
        return cardMapper.selectMd5key(usercode);
    }

    @Override
    public RcvcardOrder queryRcvcardBill(String billid) {
        return cardMapper.selectRcvcardBill(billid);
    }

    private CardResult getCardResult(String returnStr) {
        if(StringUtils.isEmpty(returnStr)){
            CardResult errorResult = new CardResult();
            errorResult.setResult("fail");
            errorResult.setInfo("获取到的结果为空");

            return errorResult;
        }
        XStream xstream = new XStream(new DomDriver()) {
            // 如果字段不存在，则忽略该字段。
            protected MapperWrapper wrapMapper(MapperWrapper next) {
                return new MapperWrapper(next) {

                    public boolean shouldSerializeMember(Class definedIn, String fieldName) {
                        return definedIn != Object.class ? super.shouldSerializeMember(definedIn, fieldName) : false;
                    }
                };
            }
        };
        xstream.alias("root", CardResult.class);
        return (CardResult) xstream.fromXML(returnStr);
    }
}
