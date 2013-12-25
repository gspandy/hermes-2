package com.hermes.app.web.interceptor;

import com.hermes.app.AppConstants;
import com.hermes.app.web.util.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 安全拦截器
 *
 * @author of546
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {

    private final transient Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String urlPath = RequestUtil.getRestURL(request);
        if (!urlPath.startsWith(AppConstants.URL_SEPARATOR)) {
            urlPath = AppConstants.URL_SEPARATOR + urlPath;
        }
        logger.debug("requestUrl:{}", urlPath);
        // 判断会话
        String usercode = (String) WebUtils.getSessionAttribute(request, AppConstants.SESSION_USERCODE);
        if (usercode == null) {
            // 如果是ajax请求响应头会有，x-requested-with；
            if (request.getHeader("x-requested-with") != null
                    && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
                // 在响应头设置session状态
                response.setHeader("sessionstatus", "timeout");
                return false;
            }

            response.sendRedirect("/invalidSession.jsp");
            return false;
        }

        return true;
    }

}
