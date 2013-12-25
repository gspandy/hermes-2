package com.hermes.app.web.action.cash;


import com.hermes.app.form.cash.OrderForm;
import com.hermes.app.service.cash.CashService;
import com.hermes.app.web.action.BaseController;
import com.hermes.app.web.util.DateUtil;
import com.hermes.app.web.util.PaginationSupport;
import com.hermes.app.web.util.RequestUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * 提现订单controller
 *
 * @author of644
 */
@Controller
@RequestMapping("/cash")
public class CashOrderController extends BaseController {

    @Autowired
    private CashService cashService;

    /**
     * 访问提现订单明细
     *
     * @return 登录页面
     */
    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String index() {
        return "cash/order/order-index";
    }

    /**
     * ajax url
     */
    @RequestMapping(value = "/order/list", method = RequestMethod.POST)
    public
    @ResponseBody
    java.util.Map<String, Object> list(OrderForm form, HttpServletRequest request) {
        form.setSortInfo(request);
        form.setUsercode(getSessionUserCode(request));
        logger.debug("Action.URL={},param={}", RequestUtil.getRestURL(request), form.toString());
        // 日期类型
        String dateType = form.getDateType();
        String sysDate =  DateUtil.getDate(new Date());
        String applyTimeStart;
        String applyTimeEnd;
        // 选择是日期类型
        if(StringUtils.isNotEmpty(dateType)) {
            // 最近一个月
            if (dateType.equals("month")) {
                applyTimeStart = DateUtil.dateAdd(new Date(), -30);
                applyTimeEnd = sysDate;
            }
            // 最近一星期
            else if (dateType.equals("week")) {
                applyTimeStart = DateUtil.dateAdd(new Date(), -7);
                applyTimeEnd = sysDate;
            }
            // 今天
            else {
                applyTimeStart = sysDate;
                applyTimeEnd = sysDate;
            }
        }
        // 手动选择了时间或者刚初始化的时候
        else {
            applyTimeStart = StringUtils.defaultIfEmpty(form.getApplyTimeStart(), sysDate);
            applyTimeEnd = StringUtils.defaultIfEmpty(form.getApplyTimeEnd(), sysDate);
        }
        form.setApplyTimeStart(applyTimeStart);
        form.setApplyTimeEnd(applyTimeEnd);

        // 查询列表
        PaginationSupport ps = this.cashService.queryOrders(form);

        Map<String, Object> dataMap = dataTableJson(ps.getTotalCount(), ps.getItems());

        dataMap.put("start", applyTimeStart);
        dataMap.put("end", applyTimeEnd);
        return dataMap;
    }
}
