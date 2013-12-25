package com.hermes.app.web.action.base;

import com.hermes.app.domain.ServiceResult;
import com.hermes.app.domain.user.User;
import com.hermes.app.form.base.RegisterForm;
import com.hermes.app.service.user.UserService;
import com.hermes.app.web.action.BaseController;
import com.ofpay.rex.captcha.CaptchaControlHelp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 第四方用户注册
 *
 * @author of821
 */
@Controller
@RequestMapping("/register")
public class RegisterController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 注册
     *
     * @return 注册
     */
    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "base/login/register";
    }

    /**
     * 注册
     * @param form
     * @param result
     * @param request
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> register(@Valid RegisterForm form, BindingResult result, HttpServletRequest request,
                         HttpSession session, Model model) {
        Map<String, Object> map = new HashMap<String, Object>();

        //判断输入参数验证是否报错
        if (result.hasErrors())
        {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> fes = result.getFieldErrors();
            for(FieldError error : fes){
                errorMsg.append(error.getDefaultMessage());
                errorMsg.append(";");
            }
            map.put("result", FAIL);
            map.put("errMsg", errorMsg.toString());
            return map;
        }

        try {
            //先验证验证码
            if (!CaptchaControlHelp.checkCaptcha(form.getCaptcha(), request.getSession(true)))
            {
                map.put("result", FAIL);
                map.put("errMsg", "注册验证码不正确！");
                return map;
            }

            //存用户信息到数据库中
            ServiceResult loginResult = userService.register(
                    formTranslate2ServiceBean(request,form));
            if (!loginResult.isResult()) {
                map.put("result", FAIL);
                map.put("errMsg", loginResult.getErrMsg());
                return map;
            }

        } catch (Exception e) {
            logger.debug(e.getMessage());
            map.put("result", FAIL);
            map.put("errMsg", "注册异常错误！");
            return map;
        }
        map.put("result", SUCCESS);
        return map;
    }

    /**
     * 检查用户名是否存在
     * @param username 用户名
     * @return
     */
    @RequestMapping(value="/checkusername")
     public  @ResponseBody
    Map<String, Object> checkUserName(@RequestParam("username")String username){
        boolean isExist = userService.isExistUserName(username);
        return data2json(isExist);
    }

    /**
     * 将从界面上过来的form消息转化为service的bean对象
     * @param request
     * @param form
     * @return
     */
    private User formTranslate2ServiceBean(HttpServletRequest request,RegisterForm form){
        User saleUser = new User();
        saleUser.setUsername(form.getUserName());   //设置用户名
        saleUser.setPassword(form.getUserPassword());  //设置密码
        saleUser.setUsercell(form.getCellPhoneNumber());  //设置手机号码
        saleUser.setEmail(form.getEmail());  //设置邮箱
        saleUser.setUsertel(null);  //设置固话
        saleUser.setQq(null);  //设置qq
        saleUser.setMerchantType(form.getMerchantType());  //设置商户类型 个人：personal 公司：company
        saleUser.setUserRealName(form.getContactName());  //设置用户的真实姓名为个人联系人名字

        saleUser.setIdentityCard(form.getIdentityCard()); //设置个人身份证
        saleUser.setCompanyName(form.getCompanyName()); //设置公司名称
        saleUser.setCompanyLicenseNumber(form.getCompanyLicenseNumber());  //设置公司执照号码
        saleUser.setPrvcin(form.getProvince());  //设置省份
        saleUser.setCityin(form.getCity());  //设置城市
        saleUser.setCountry(form.getCountry());  //设置所在区域

        String ip = getIp(request);
        saleUser.setLastip(ip);  //设置上一次登入Ip
        saleUser.setCurip(ip);   //设置当前Ip
        //saleUser.setPersonal(personal);  //这个地方没有用到，可能到时候界面上要用到？？？tjy

        return saleUser;
    }



}
