package cn.yang.o2o.dao;

import cn.yang.o2o.entity.WechatAuth;

public interface WechatAuthDao {

    /*
     * @Description 通过openId查询对应本平台的微信帐号
     * @Param [openId]
     * @Return cn.yang.o2o.entity.WechatAuth
     */
    WechatAuth queryWechatInfoByOpenId(String openId);

    /*
     * @Description 添加对应本平台的微信帐号
     * @Param [wechatAuth]
     * @Return int
     */
    int insertWechatAuth(WechatAuth wechatAuth);
}
