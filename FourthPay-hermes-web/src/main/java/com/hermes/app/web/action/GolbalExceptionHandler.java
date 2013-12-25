package com.hermes.app.web.action;


import com.hermes.app.exception.BussinessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 统一异常处理
 *
 * @author of644
 */
@ControllerAdvice
public class GolbalExceptionHandler {

    @ExceptionHandler(BussinessException.class)
    public String handleCashException(BussinessException ex, HttpServletRequest request, HttpServletResponse response) {
        // 效果类似于RedirectAttributes.addFlashAttribute
        FlashMap flashMap = RequestContextUtils.getOutputFlashMap(request);
        // 错误消息
        flashMap.put("errMsg", ex.getErrMsg());
        // 原form表单信息
        flashMap.put("form", ex.getForm());
        return new StringBuilder().append("redirect:").append(ex.getRedirecturl()).toString();
    }
}
