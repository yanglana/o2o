package cn.yang.o2o.dao;

import cn.yang.o2o.BaseTest;
import cn.yang.o2o.entity.Area;
import cn.yang.o2o.entity.PersonInfo;
import cn.yang.o2o.entity.Shop;
import cn.yang.o2o.entity.ShopCategory;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * @Description TODO
 * @Author yanglan
 * @Date 2018/11/28 11:29
 */
public class ShopDaoTest extends BaseTest {
    @Autowired
    private ShopDao shopDao;

    @Test
    public void testInsertShop(){
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(1L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);;
        shop.setShopName("测试的店铺");
        shop.setShopDesc("test");
        shop.setShopAddr("test");
        shop.setPhone("test");
        shop.setShopImg("test");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(0);
        shop.setAdvice("审核中");
        int effectedNum = shopDao.insertShop(shop);
        assertEquals(1,effectedNum);
    }

    @Test
    public void testUpdateShop(){
        Shop shop = new Shop();
        shop.setShopId(1L);
        shop.setShopDesc("测试的描述");
        shop.setShopAddr("测试的地址");
        shop.setLastEditTime(new Date());
        int effectedNum = shopDao.updateShop(shop);
        assertEquals(1,effectedNum);
    }
}
