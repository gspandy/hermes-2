package com.hermes.app.web.action;

import com.hermes.app.AppConstants;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Controller基类，放置用到的公共资源
 *
 * @author of546
 */
@Scope("request")
public class BaseController {
    protected final transient Logger logger = LoggerFactory.getLogger("inf");

    /**
     * The action execution was successful.
     */
    public static final String SUCCESS = "success";

    /**
     * The action execution was a fail.
     */
    public static final String FAIL = "fail";

    /**
     * The Remote execution was a error
     */
    public static final String ERROR = "error";


    /**
     * Jquery DataTable Data
     *
     * @param totalCount
     * @param dataList
     * @return
     */
    protected Map<String, Object> dataTableJson(int totalCount, List<?> dataList) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("iTotalDisplayRecords", totalCount);
        data.put("iTotalRecords", totalCount);
        data.put("aaData", dataList == null ? Collections.EMPTY_LIST : dataList);
        Map<String, Object> map = new HashMap<String, Object>();
        if (CollectionUtils.isEmpty(dataList)) {
            map.put("result", ERROR);
        } else {
            map.put("result", SUCCESS);
        }
        map.put("data", data);
        return map;
    }

    protected Map<String, Object> data2json(List<?> data) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (CollectionUtils.isEmpty(data)) {
            map.put("result", ERROR);
        } else {
            map.put("result", SUCCESS);
        }
        map.put("data", data);
        return map;
    }

    protected Map<String, Object> data2json(Object data) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (data == null) {
            map.put("result", ERROR);
        } else {
            map.put("result", SUCCESS);
        }
        map.put("data", data);
        return map;
    }

    /**
     * 从session中获取usercode
     * @param request
     * @return
     */
    protected String getSessionUserCode(HttpServletRequest request) {
        return (String) WebUtils.getSessionAttribute(request, AppConstants.SESSION_USERCODE);
    }

    /**
     * 获取用户ip地址
     */
    public String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getRemoteAddr();
        }
        // 多级反向代理
        if (null != ip && !"".equals(ip.trim()))
        {
            StringTokenizer st = new StringTokenizer(ip, ",");
            if (st.countTokens() > 1)
            {
                return st.nextToken();
            }
        }
        return ip;
    }
}
