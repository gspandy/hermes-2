package com.hermes.app.web.action.rate;


import com.hermes.app.domain.rate.Rate;
import com.hermes.app.form.cash.OrderForm;
import com.hermes.app.service.cash.CashService;
import com.hermes.app.service.rate.RateService;
import com.hermes.app.web.action.BaseController;
import com.hermes.app.web.util.DateUtil;
import com.hermes.app.web.util.PaginationSupport;
import com.hermes.app.web.util.RequestUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 支付费率controller
 *
 * @author tujianying/of821
 */
@Controller
public class RateController extends BaseController {

    @Autowired
    private RateService rateService;

    @RequestMapping(value = "/rate")
    public String list(HttpServletRequest request,  Model model) {
        String usercode = getSessionUserCode(request);
        logger.debug("Action.URL={},param={}", RequestUtil.getRestURL(request), usercode);

        // 查询列表
        List<Rate> list = rateService.queryRatesByUsercode(usercode);
        model.addAttribute("rates", list);
        return "rate/rate-index";
    }
}
