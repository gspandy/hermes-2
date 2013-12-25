package com.hermes.app.web.action.order;


import com.hermes.app.form.order.DataAnalyseForm;
import com.hermes.app.form.order.RcvCardOrderForm;
import com.hermes.app.service.order.OrderService;
import com.hermes.app.util.order.StateCode;
import com.hermes.app.web.action.BaseController;
import com.hermes.app.web.util.PaginationSupport;
import com.hermes.app.web.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 订单数据分析controller
 *
 * @author tujianying/of821
 */
@Controller
@RequestMapping("/order")
public class DataAnalyseController extends BaseController {

    @Autowired
    private OrderService orderService;

    /**
     * 访问提现订单明细
     *
     * @return 登录页面
     */
    @RequestMapping(value = "/dataanalyse", method = RequestMethod.GET)
    public String index() {
        return "order/dataanalyse-index";
    }

    /**
     * ajax url
     */
    @RequestMapping(value = "/dataanalyse/list", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> list(DataAnalyseForm form, HttpServletRequest request) {
        form.setSortInfo(request);
        form.setUsercode(getSessionUserCode(request));

        logger.debug("Action.URL={},param={}", RequestUtil.getRestURL(request), form.toString());

        //修改时间，加上时:分:秒
        String startTag = " 00:00:00";
        String endTag = " 23:59:59";
        form.setStartTime(form.getStartTime()+ startTag);
        form.setEndTime(form.getEndTime() + endTag);

        String chargeStartTime = form.getChargeStartTime();
        String chargeEndTime = form.getChargeEndTime();
        if(null != chargeStartTime && !"".equals(chargeStartTime)){
            form.setChargeStartTime(chargeStartTime + startTag);
        }
        if(null != chargeEndTime && !"".equals(chargeEndTime)){
            form.setChargeEndTime(chargeEndTime + endTag);
        }

        // 查询列表
        PaginationSupport ps = orderService.queryRcvCardStatic(form);

        Map<String, Object> dataMap = dataTableJson(ps.getTotalCount(), ps.getItems());
        return dataMap;
    }
}
