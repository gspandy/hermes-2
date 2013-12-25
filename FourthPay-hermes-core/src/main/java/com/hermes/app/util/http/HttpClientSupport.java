package com.hermes.app.util.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.Map;


/**
 * http tool
 *
 * @author of644
 */
@Component
public class HttpClientSupport extends AbstractHttpSupport {

    /**
     * logger.
     */
    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * default.
     */
    private static final String DEFAULT_CHARSET = "UTF-8";


    /**
     * @param uri
     * @param requestMap
     * @param charset
     * @return
     */
    public String sendPostMultiValue(String uri, MultiValueMap<String, String> requestMap, String charset) {

        // 2. 设置请求头，设置编码
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType(MediaType.APPLICATION_FORM_URLENCODED.getType(),
                MediaType.APPLICATION_FORM_URLENCODED.getSubtype(), charset == null ? Charset.defaultCharset()
                : Charset.forName(charset)));

        // 3.封装请求实体
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(requestMap,
                headers);

        log.info("send http post start. Uri:{}, Entity:{}", uri, entity);

        String response = "";
        try {
            response = getRestTemplate().postForObject(uri, entity, String.class);
        } catch (Exception e) {
            log.error("send http post start. Uri:{}, Entity:{} error", new Object[]{uri, entity, e});
        }

        log.info("send http post request end. response:{}", response);

        return response;
    }

    /**
     * post请求.
     *
     * @param uri
     * @param requestMap
     * @param charset
     * @return
     */
    public String sendPost(String uri, Map<String, String> requestMap, String charset) {

        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<String, String>(requestMap == null ? 0
                : requestMap.size());

        // 1.将外部参数转换为请求参数。
        super.mapCopyToMultiValueMap(requestMap, requestParams);

        return sendPostMultiValue(uri, requestParams, charset);
    }

    /**
     * send post.
     *
     * @param uri        请求url
     * @param requestMap 请求参数
     * @return 结果字符串
     */
    public String sendPost(String uri, Map<String, String> requestMap) {
        return sendPost(uri, requestMap, DEFAULT_CHARSET);
    }

    /**
     * @param uri
     * @param queryString
     * @return
     */
    public String sendGet(String uri, String queryString, String charset) {

        try {
            URI newUri = UriComponentsBuilder.fromHttpUrl(uri).query(queryString).build().encode(charset).toUri();

            log.info("send http get request start. Uri:{}", newUri);

            String response = getRestTemplate().getForObject(newUri, String.class);

            log.info("send http get request end. response:{}", response);
            return response;
        } catch (UnsupportedEncodingException e) {
            log.warn("encoding error! Uri:{}, QueryString:{}, CharSet:{}", new String[]{uri, queryString, charset});
            return getRestTemplate().getForObject(
                    UriComponentsBuilder.fromHttpUrl(uri).query(queryString).build().toUri(), String.class);
        }

    }

    /**
     * @param uri
     * @param queryString
     * @return
     */
    public String sendGet(String uri, String queryString, String invokeClassName, String invokeMethod) {
        return sendGet(uri, queryString, DEFAULT_CHARSET);
    }

    /**
     * @param uri
     * @return
     */
    public String sendGet(String uri) {

        log.info("send http get request start. Uri:{}", uri);

        String response = getRestTemplate().getForObject(uri, String.class);

        log.info("send http get request end. response:{}", response);

        return response;
    }
}
