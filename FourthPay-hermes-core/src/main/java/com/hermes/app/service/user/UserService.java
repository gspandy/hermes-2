package com.hermes.app.service.user;

import com.hermes.app.domain.ServiceResult;
import com.hermes.app.domain.user.User;
import com.hermes.app.domain.user.UserAdd;
import com.hermes.app.form.base.LoginForm;

/**
 * 涉及用户相关的service
 *
 * @author of644
 */
public interface UserService {

    /**
     * 登入
     * @param loginForm
     * @return
     */
    public ServiceResult login(LoginForm loginForm);

    /**
     * 注册
     * @param user
     * @return
     */
    public ServiceResult register(User user);

    /**
     * 检查用户名是否已经存在
     *
     * @param userName 用户名
     * @return boolean 是否存在 true：存在  false：不存在
     */
    public boolean isExistUserName(String userName);


    /**
     * 根据usercode获取用户信息
     * @param usercode
     * @return
     */
    public User queryByCode(String usercode);

    /**
     * 根据usercode获取用户信息
     * @param usercode
     * @return
     */
    public UserAdd queryAddByCode(String usercode);
}
