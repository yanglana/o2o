package cn.yang.o2o.service;

import cn.yang.o2o.BaseTest;
import cn.yang.o2o.dto.WechatAuthExecution;
import cn.yang.o2o.entity.PersonInfo;
import cn.yang.o2o.entity.WechatAuth;
import cn.yang.o2o.enums.WechatAuthStateEnum;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * @Description TODO
 * @Author yanglan
 * @Date 2019/1/16 15:57
 */
public class WechatAuthServiceTest extends BaseTest {
    @Autowired
    private WechatAuthService wechatAuthService;

    @Test
    public void testRegister() {
        // 新增一条微信帐号
        WechatAuth wechatAuth = new WechatAuth();
        PersonInfo personInfo = new PersonInfo();
        String openId = "dfkalfajfdaa";
        // 给微信帐号设置上用户信息，但不设置上用户Id
        // 希望创建微信帐号的时候自动创建用户信息
        personInfo.setCreateTime(new Date());
        personInfo.setName("测试一下");
        personInfo.setUserType(1);
        wechatAuth.setPersonInfo(personInfo);
        wechatAuth.setOpenId(openId);
        wechatAuth.setCreateTime(new Date());
        WechatAuthExecution wae = wechatAuthService.register(wechatAuth);
        assertEquals(WechatAuthStateEnum.SUCCESS.getState(), wae.getState());
        // 通过openId找到新增的wechatAuth
        wechatAuth = wechatAuthService.getWechatAuthByOpenId(openId);
        // 打印用户名字看看跟预期是否相符
        System.out.println(wechatAuth.getPersonInfo().getName());
    }
}
