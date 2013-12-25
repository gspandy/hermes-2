package com.hermes.app.web.action.base;

import com.hermes.app.AppConstants;
import com.hermes.app.domain.ServiceResult;
import com.hermes.app.domain.user.User;
import com.hermes.app.form.base.LoginForm;
import com.hermes.app.service.user.UserService;
import com.hermes.app.web.action.BaseController;
import com.ofpay.rex.captcha.CaptchaControlHelp;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * 后台登录
 *
 * @author of644
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 访问
     *
     * @return 登录页面
     */
    @RequestMapping(value = "/manager",method = RequestMethod.GET)
    public String manager(HttpSession session) {
        return "base/login/login-manager";
    }

    /**
     * 访问
     *
     * @return 登录页面
     */
    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpSession session) {
        String usercode = (String)session.getAttribute(AppConstants.SESSION_USERCODE);

        if(StringUtils.isEmpty(usercode)){
            return "base/login/login-index";
        }
        return "base/login/login-manager";
    }

    /**
     * 登录
     *
     * @return 管理首页
     */
    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid LoginForm form, BindingResult result, HttpServletRequest request,
                         HttpSession session, Model model) {

        //判断输入参数验证是否报错
        if (result.hasErrors())
        {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> fes = result.getFieldErrors();
            for(FieldError error : fes){
                errorMsg.append(error.getDefaultMessage());
                errorMsg.append(";");
            }
            model.addAttribute("resultState", FAIL);
            model.addAttribute("errMsg", errorMsg.toString());
            return "base/login/login-index";
        }

        try {
//            //先验证验证码
//            String rand = (String) session.getAttribute("rand");
//
//            //没有获取到图片上的验证码来与用户输入的进行比较
//            if(null == rand || "".equals(rand.trim())){
//                model.addAttribute("resultState", FAIL);
//                model.addAttribute("errMsg", "没有获取到图片上的验证码，并非用户填的验证码！");
//                return "base/login/login-index";
//            }
//
//            //用户输入验证码与图片上的验证码不一致
//            if(!rand.equalsIgnoreCase(form.getCaptcha()))
//            {
//                model.addAttribute("resultState", FAIL);
//                model.addAttribute("errMsg", "验证码不正确！");
//                return "base/login/login-index";
//            }

            if (!CaptchaControlHelp.checkCaptcha(form.getCaptcha(), request.getSession(true)))
            {
                model.addAttribute("resultState", FAIL);
                model.addAttribute("errMsg", "验证码不正确！");
                return "base/login/login-index";
            }
            form.setLastip(getIp(request));
            // 登录验证逻辑
            ServiceResult loginResult = userService.login(form);
            if (!loginResult.isResult()) {
                model.addAttribute("resultState", FAIL);
                model.addAttribute("errMsg", loginResult.getErrMsg());
                return "base/login/login-index";
            }
            session.setAttribute(AppConstants.SESSION_USERCODE, ((User)(loginResult.getResultObject())).getUsercode());
            session.setAttribute(AppConstants.SESSION_LASTTIME, ((User)(loginResult.getResultObject())).getLasttime());
            session.setAttribute(AppConstants.SESSION_LASTIP, ((User)(loginResult.getResultObject())).getLastip());

        } catch (Exception e) {
            logger.debug(e.getMessage());
            model.addAttribute("resultState", FAIL);
            model.addAttribute("errMsg", "用户名或者密码错误！");
            return "base/login/login-index";
        }
        return "redirect:/account";
    }

}
