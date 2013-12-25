/**
 * 
 */
package com.hermes.app.util.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.support.RestGatewaySupport;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


/**
 *
 * @author of644
 */
public class AbstractHttpSupport extends RestGatewaySupport {

    @Autowired
    public void setRestTemplate(@Qualifier("restTemplate") RestTemplate restTemplate) {
        super.setRestTemplate(restTemplate);
    }

    /**
     * 转换为请求参数.
     * 
     * @param params
     * @param destMap
     */
    public void mapCopyToMultiValueMap(Map<String, String> params, MultiValueMap<String, String> destMap) {
        if (params == null || destMap == null) {
            return;
        }

        Iterator<Entry<String, String>> iterator = params.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, String> next = iterator.next();
            destMap.add(next.getKey(), next.getValue());
        }

    }
}
