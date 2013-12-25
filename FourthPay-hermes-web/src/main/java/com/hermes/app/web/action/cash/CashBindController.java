package com.hermes.app.web.action.cash;


import com.hermes.app.domain.cash.Bind;
import com.hermes.app.domain.user.User;
import com.hermes.app.exception.BussinessExceptionBuilder;
import com.hermes.app.form.cash.BindForm;
import com.hermes.app.service.cash.CashService;
import com.hermes.app.service.user.UserService;
import com.hermes.app.web.action.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 提现账户绑定controller
 *
 * @author of644
 */
@Controller
@RequestMapping("/cash")
public class CashBindController extends BaseController {

    @Autowired
    private CashService cashService;

    @Autowired
    private UserService userService;

    /**
     * 访问提现账户绑定
     *
     * @return 页面
     */
    @RequestMapping(value = "/bind", method = RequestMethod.GET)
    public String index(BindForm bindForm, HttpServletRequest request, Model model) {
        bindForm.setUsercode(getSessionUserCode(request));
        // 所有状态的银行卡（审核中，审核通过...）
        List<Bind> binds = cashService.queryBinds(bindForm);
        model.addAttribute("binds", binds);
        return "cash/bind/bind-index";
    }

    /**
     * 添加银行卡成功
     *
     * @return 页面
     */
    @RequestMapping(value = "/bind/success", method = RequestMethod.GET)
    public String success() {
        return "cash/bind/bind-success";
    }

    /**
     * 添加银行卡
     *
     * @return
     */
    @RequestMapping(value = "/bind/new", method = RequestMethod.GET)
    public String add() {
        return "cash/bind/bind-new";
    }

    /**
     * 绑定银行
     *
     * @param bindForm
     * @param request
     * @return
     */
    @RequestMapping(value = "/bind/add", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    public
    @ResponseBody
    String create(@Valid BindForm bindForm, BindingResult result, HttpServletRequest request) {
        try {
            // 验证输入参数
            if (result.hasErrors()) {
                FieldError fes = result.getFieldError();
                return fes.getDefaultMessage();
            }
            // 验证卡号
            if (!bindForm.getAccountno().equals(bindForm.getAccountnoagain())){
                return "两次卡号输入不一致";
            }

            // 获取用户信息
            String usercode = getSessionUserCode(request);
            User user = userService.queryByCode(usercode);
            // 主键
            bindForm.setAccountflowid(UUID.randomUUID().toString().replaceAll("-", ""));
            bindForm.setUsercode(usercode);
            bindForm.setUsername(user.getUsername());
            bindForm.setAccounttype("1");//银行
            bindForm.setDatastat("1");//处理中
            bindForm.setAccshortname("");
            int count = cashService.saveBind(bindForm);
            if (count > 0) {
                return SUCCESS;
            }
            return FAIL;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return FAIL;
        }
    }

    /**
     * 获取银行列表
     *
     * @return
     */
    @RequestMapping(value = "/bind/banks", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Map> getBanks() {
        List<Map> banks = cashService.queryBankCodes(null);
        return banks;
    }

    /**
     * 删除银行
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/bind/delete/{id}", method = RequestMethod.GET)
    public String remove(@PathVariable String id, RedirectAttributes redirectAttrs) {
        int count = cashService.removeBank(id);
        if (count <= 0) {
            throw BussinessExceptionBuilder.aBussinessException()
                    .withErrMsg("删除银行卡异常")
                    .withForm(null)
                    .withRedirecturl("/cash/bind")
                    .build();
        }
        return "redirect:/cash/bind";
    }
}
