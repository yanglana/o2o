package cn.yang.o2o.dao;

import cn.yang.o2o.BaseTest;
import cn.yang.o2o.entity.PersonInfo;
import cn.yang.o2o.entity.WechatAuth;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * @Description TODO
 * @Author yanglan
 * @Date 2019/1/15 19:44
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WechatAuthDaoTest extends BaseTest {
    @Autowired
    private WechatAuthDao wechatAuthDao;

    @Test
    public void testAInsertWechatAuth(){
        // 新增一条微信帐号
        WechatAuth wechatAuth = new WechatAuth();
        PersonInfo personInfo = new PersonInfo();
        personInfo.setUserId(1L);
        // 给微信帐号绑定上用户信息
        wechatAuth.setPersonInfo(personInfo);
        // 随意设置上openId
        wechatAuth.setOpenId("dfkalfajfda");
        wechatAuth.setCreateTime(new Date());
        int effectedNum = wechatAuthDao.insertWechatAuth(wechatAuth);
        assertEquals(1,effectedNum);
    }

    @Test
    public void testBQueryWechatAuthByOpenId(){
        WechatAuth wechatAuth = wechatAuthDao.queryWechatInfoByOpenId("dfkalfajfda");
        assertEquals("测试",wechatAuth.getPersonInfo().getName());
    }
}
