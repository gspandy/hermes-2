package com.hermes.app.web.action.account;

import com.hermes.app.AppConstants;
import com.hermes.app.domain.account.Account;
import com.hermes.app.domain.account.Announce;
import com.hermes.app.domain.cash.UserCount;
import com.hermes.app.domain.user.User;
import com.hermes.app.domain.user.UserAdd;
import com.hermes.app.exception.BussinessExceptionBuilder;
import com.hermes.app.form.account.AccountForm;
import com.hermes.app.form.order.RcvCardOrderForm;
import com.hermes.app.service.account.AccountService;
import com.hermes.app.service.cash.CashService;
import com.hermes.app.service.order.OrderService;
import com.hermes.app.service.user.UserService;
import com.hermes.app.web.action.BaseController;
import com.hermes.app.web.util.DateUtil;
import com.hermes.app.web.util.PaginationSupport;
import com.hermes.app.web.util.RequestUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 账户管理入口
 *
 * @author of644
 */
@Controller
@RequestMapping("/account")
public class AccountController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private CashService cashService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${common.api.url}")
    private String commonApiUrl;
    @Value("${common.ip.search}")
    private String commonIpSearch;
    /**
     * 账户中心
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model) {
        String usercode = getSessionUserCode(request);
        // 账户信息
        User user = userService.queryByCode(usercode);
        WebUtils.setSessionAttribute(request, AppConstants.SESSION_USERNAME, user.getUsername());
        WebUtils.setSessionAttribute(request, AppConstants.SESSION_USERFLAG, user.getMerpayflag());
        // 用户余额
        UserCount userCount = cashService.queryUserCountByCode(usercode);
        model.addAttribute("user", user);
        model.addAttribute("usercount", userCount);

        return "account/account-index";
    }

    /**
     * 今日统计
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/stat", method = RequestMethod.GET)
    public
    @ResponseBody
    Map stat(HttpServletRequest request) {
        // usercode
        String usercode = getSessionUserCode(request);
        Account account = accountService.queryStatsByCode(usercode);
        return data2json(account);
    }

    /**
     * 最近交易记录
     *
     * @param form
     * @param request
     * @return
     */
    @RequestMapping(value = "/order/list", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> list(RcvCardOrderForm form, HttpServletRequest request) {
        form.setSortInfo(request);
        form.setUsercode(getSessionUserCode(request));
        // 最近7天提交的卡密
        form.setStartTime(DateUtil.dateAdd(new Date(), -7));
        form.setEndTime(DateUtil.dateAdd(new Date(), 1));
        // 查询列表
        PaginationSupport ps = orderService.queryRcvCardOrders(form);
        return dataTableJson(ps.getTotalCount(), ps.getItems());
    }

    /**
     * 用户资料
     *
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String info(HttpServletRequest request, Model model) {
        User user = userService.queryByCode(getSessionUserCode(request));
        UserAdd userAdd = userService.queryAddByCode(getSessionUserCode(request));
        model.addAttribute("user", user);
        model.addAttribute("userAdd", userAdd);
        return "account/info/account-info-index";
    }

    /**
     * 用户资料修改
     *
     * @return
     */
    @RequestMapping(value = "/info/edit", method = RequestMethod.GET)
    public String infoEdit(HttpServletRequest request, Model model) {
        User user = userService.queryByCode(getSessionUserCode(request));
        UserAdd userAdd = userService.queryAddByCode(getSessionUserCode(request));
        model.addAttribute("user", user);
        model.addAttribute("userAdd", userAdd);
        return "account/info/account-info-edit";
    }

    /**
     * 用户资料提交
     *
     * @return
     */
    @RequestMapping(value = "/info/edit", method = RequestMethod.POST)
    public String infoPost(AccountForm accountForm, HttpServletRequest request) {
        accountForm.setUsercode(getSessionUserCode(request));
        // 更新失败
        if (!accountService.updateAccountInfo(accountForm)) {
            throw BussinessExceptionBuilder.aBussinessException()
                    .withErrMsg("用户信息更新失败")
                    .withForm(accountForm)
                    .withRedirecturl("/account/info/edit")
                    .build();
        }
        return "account/info/account-info-success";
    }

    /**
     * 用户安全设置
     *
     * @return
     */
    @RequestMapping(value = "/security", method = RequestMethod.GET)
    public String security(HttpServletRequest request, Model model) {
        User user = userService.queryByCode(getSessionUserCode(request));
        model.addAttribute("user", user);
        return "account/security/account-security-index";
    }

    /**
     * 用户登录密码修改
     *
     * @return
     */
    @RequestMapping(value = "/security/logpsw", method = RequestMethod.GET)
    public String logpsw() {
        return "account/security/account-security-logpsw";
    }

    /**
     * 用户提现密码修改
     *
     * @return
     */
    @RequestMapping(value = "/security/paypsw", method = RequestMethod.GET)
    public String paypsw() {
        return "account/security/account-security-paypsw";
    }

    /**
     * 用户交易密码修改
     *
     * @return
     */
    @RequestMapping(value = "/security/buspsw", method = RequestMethod.GET)
    public String buspsw(HttpServletRequest request, Model model) {
        User user = userService.queryByCode(getSessionUserCode(request));
        model.addAttribute("user", user);
        return "account/security/account-security-buspsw";
    }

    /**
     * 用户登录密码修改
     *
     * @return
     */
    @RequestMapping(value = "/security/{mode}", method = RequestMethod.POST)
    public String pswPost(AccountForm accountForm, @PathVariable String mode, HttpServletRequest request) {
        String usercode = getSessionUserCode(request);
        User user = userService.queryByCode(usercode);
        accountForm.setUsercode(usercode);
        accountForm.setRemoteIp(RequestUtil.getRemoteIp(request));

        // 验证表单
        validForm(accountForm, user, mode);
        // 更新登录密码，原来是调用老saleapi接口，现在没有人维护了
        // 于是我们还是改成了直接操作saleuser表的方式
        if("logpsw".equals(mode)){
            accountForm.setPsw(createPsw());
            accountForm.setLogpswNew(DigestUtils.md5Hex(accountForm.getLogpswNew()));
            accountForm.setLogpswNew(createLogPwd(accountForm, user));
        }
        else if ("paypsw".equals(mode)){
            accountForm.setPaypwsNew(DigestUtils.md5Hex(accountForm.getPaypwsNew()));
        }
        // 更新密码
        if (!accountService.updatePsw(accountForm)) {
            throw BussinessExceptionBuilder.aBussinessException()
                    .withErrMsg("修改密码异常")
                    .withForm(accountForm)
                    .withRedirecturl(new StringBuilder().append("/account/security/").append(mode).toString())
                    .build();
        }

        return "account/security/account-security-success";
    }

    /**
     * api接口展示页
     *
     * @return
     */
    @RequestMapping(value = "/api", method = RequestMethod.GET)
    public String api() {
        return "account/account-api-index";
    }

    /**
     * 最新公告（首页用）
     *
     * @return
     */
    @RequestMapping(value = "/announce/latest", method = RequestMethod.GET)
    public
    @ResponseBody
    Map announce() {
        Announce announce = accountService.queryLatestAnnounce();
        return data2json(announce);
    }

    /**
     * 公告列表
     *
     * @return
     */
    @RequestMapping(value = "/announce/list", method = RequestMethod.GET)
    public
    @ResponseBody
    Map announceList() {
        List<Announce> announce = accountService.queryAnnounces();
        return data2json(announce);
    }


    /**
     * 调用公共接口查询ip地址归属地
     *
     * @param ip
     * @return
     */
    @RequestMapping(value = "/location/{ip}", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    public
    @ResponseBody
    String queryLocByIp(@PathVariable String ip, HttpServletRequest request) {
        URI targetUrl= UriComponentsBuilder
                .fromUriString(commonApiUrl)
                .path(commonIpSearch)
                .queryParam("ip", WebUtils.getSessionAttribute(request, AppConstants.SESSION_LASTIP))
                .build()
                .toUri();
        return restTemplate.getForObject(targetUrl, String.class);
    }

    /**
     * 随机串
     *
     * @return
     */
    public String createPsw() {
        String s1 = DigestUtils.md5Hex(String.valueOf(System.currentTimeMillis()));
        String s2 = UUID.randomUUID().toString().replaceAll("\\-", "");
        return new StringBuilder().append(s1).append(s2).toString();
    }

    /**
     * 生成登录密码
     *
     * @param accountForm
     * @param user
     * @return
     */
    private String createLogPwd(AccountForm accountForm, User user) {
        // 新注册的用户
        if (!"1".equals(user.getTempflag())) {
            return accountForm.getLogpswNew();
        }
        // 老sale用户
        return DigestUtils.md5Hex(user.getUsercode() + accountForm.getLogpswNew() + accountForm.getPsw());
    }

    /**
     * 检查密码是否正确
     *
     * @param logpswBefore
     * @param dbpwd
     * @param user
     * @param ping
     * @return
     */
    private boolean checkLogPwd(String logpswBefore, String dbpwd, User user, String ping) {
        // 新注册的用户
        if (!"1".equals(user.getTempflag())) {
            logpswBefore = DigestUtils.md5Hex(logpswBefore + ping);
            return logpswBefore.equals(DigestUtils.md5Hex(dbpwd + ping));
        }
        // 老sale用户
        logpswBefore = DigestUtils.md5Hex(user.getUsercode() + logpswBefore + user.getPsw());
        return logpswBefore.equals(dbpwd);
    }



    /**
     * 验证并更新密码
     *
     * 注意：登录密码和提现密码在注册的时候都初始化好了，交易密码注册时候为空
     *
     * @param accountForm
     * @param user
     * @param mode
     */
    private void validForm(AccountForm accountForm, User user, String mode) {
        String checkpsw;
        String checkpswBefore;
        String checkpswNew;
        String checkpswNewAgain;
        // 登录密码
        if ("logpsw".equals(mode)) {
            checkpsw = user.getLogpsw();
            checkpswBefore = DigestUtils.md5Hex(accountForm.getLogpswBefore());
            checkpswNew = DigestUtils.md5Hex(accountForm.getLogpswNew());
            checkpswNewAgain = DigestUtils.md5Hex(accountForm.getLogpswNewAgain());
        }
        // 提现密码
        else if ("paypsw".equals(mode)) {
            checkpsw = user.getPaypws();
            checkpswBefore = DigestUtils.md5Hex(accountForm.getPaypwsBefore());
            checkpswNew = DigestUtils.md5Hex(accountForm.getPaypwsNew());
            checkpswNewAgain = DigestUtils.md5Hex(accountForm.getPaypwsNewAgain());
        }
        // 交易密码
        else {
            checkpsw = user.getUserfax();
            checkpswBefore = accountForm.getUserfaxBefore();
            checkpswNew = accountForm.getUserfaxNew();
            checkpswNewAgain = accountForm.getUserfaxNewAgain();
        }
        // 验证密码不为空
        if (mode.equals("buspsw")){
            if (StringUtils.isEmpty(checkpswNew) || StringUtils.isEmpty(checkpswNewAgain)) {
                throw BussinessExceptionBuilder.aBussinessException()
                        .withErrMsg("密码不能为空")
                        .withForm(accountForm)
                        .withRedirecturl(new StringBuilder().append("/account/security/").append(mode).toString())
                        .build();
            }
        }
        else {
            if (StringUtils.isEmpty(checkpswBefore)
                    || StringUtils.isEmpty(checkpswNew)
                    || StringUtils.isEmpty(checkpswNewAgain)) {
                throw BussinessExceptionBuilder.aBussinessException()
                        .withErrMsg("密码不能为空")
                        .withForm(accountForm)
                        .withRedirecturl(new StringBuilder().append("/account/security/").append(mode).toString())
                        .build();
            }
        }
        // 一致性
        if (!checkpswNew.equals(checkpswNewAgain)) {
            throw BussinessExceptionBuilder.aBussinessException()
                    .withErrMsg("新密码输入不一致，请重新输入")
                    .withForm(accountForm)
                    .withRedirecturl(new StringBuilder().append("/account/security/").append(mode).toString())
                    .build();
        }
        // 与原密码比较
        if(mode.equals("logpsw")){
            if (!checkLogPwd(checkpswBefore, checkpsw, user, accountForm.getPing())) {
                throw BussinessExceptionBuilder.aBussinessException()
                        .withErrMsg("原密码不正确，请重新输入")
                        .withForm(accountForm)
                        .withRedirecturl(new StringBuilder().append("/account/security/").append(mode).toString())
                        .build();
            }
        }
        else {
            if (StringUtils.isNotEmpty(checkpsw) && !checkpsw.equals(checkpswBefore)) {
                throw BussinessExceptionBuilder.aBussinessException()
                        .withErrMsg("原密码不正确，请重新输入")
                        .withForm(accountForm)
                        .withRedirecturl(new StringBuilder().append("/account/security/").append(mode).toString())
                        .build();
            }
        }
    }
}
