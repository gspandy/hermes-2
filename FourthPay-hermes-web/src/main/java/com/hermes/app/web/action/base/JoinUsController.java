package com.hermes.app.web.action.base;

import com.hermes.app.web.action.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 公共账号申请加入充值中心
 *
 * @author of546
 */
@Controller
@RequestMapping("/join_us")
public class JoinUsController extends BaseController {

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "base/joinUs/joinUs-index";
    }

}
