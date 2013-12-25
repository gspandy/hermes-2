package com.hermes.app.persistence.pay.user;

/**
 * 商户配置表（保存商户提卡的MD5，加点类型，结算类型等等）Mapper
 *
 * @author of644
 */
public interface PayaccountsecInfoMapper {

    /**
     * 获取用户的密钥
     *
     * @param usercode
     * @return
     */
    String selectUserMd5key(String usercode);
}
