package com.hermes.app.web.action.base;

import com.hermes.app.web.action.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * 退出登录
 */
@Controller
@RequestMapping("/logout")
public class LogoutController extends BaseController {

    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

}
