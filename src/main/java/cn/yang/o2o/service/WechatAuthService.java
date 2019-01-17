package cn.yang.o2o.service;

import cn.yang.o2o.dto.WechatAuthExecution;
import cn.yang.o2o.entity.WechatAuth;
import cn.yang.o2o.exceptions.WechatAuthOperationException;

public interface WechatAuthService {
   
    /*
     * @Description 通过openId查找平台对应的微信帐号
     * @Param [openId]
     * @Return cn.yang.o2o.entity.WechatAuth
     */
    WechatAuth getWechatAuthByOpenId(String openId);
    
    /*
     * @Description 注册本平台的微信帐号
     * @Param [wechatAuth]
     * @Return WechatAuthExecution
     */
    WechatAuthExecution register(WechatAuth wechatAuth) throws WechatAuthOperationException;
}
