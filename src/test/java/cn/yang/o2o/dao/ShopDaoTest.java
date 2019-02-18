package cn.yang.o2o.dao;

import cn.yang.o2o.BaseTest;
import cn.yang.o2o.entity.Area;
import cn.yang.o2o.entity.PersonInfo;
import cn.yang.o2o.entity.Shop;
import cn.yang.o2o.entity.ShopCategory;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

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
    public void testQueryShopListAndCount() {
        Shop shopCondition = new Shop();
        /*PersonInfo owner = new PersonInfo();
        Area area = new Area();*/
        ShopCategory shopCategory = new ShopCategory();
        ShopCategory parentCategory = new ShopCategory();
        parentCategory.setShopCategoryId(8L);
        shopCategory.setParent(parentCategory);
        //shopCategory.setShopCategoryId(2L);
        //area.setAreaId(2);
        //wner.setUserId(2L);
        //shopCondition.setShopName("3");
        //shopCondition.setEnableStatus(0);
        //shopCondition.setOwner(owner);
        //shopCondition.setArea(area);
        shopCondition.setShopCategory(shopCategory);
        List<Shop> shopList = shopDao.queryShopList(shopCondition, 0, 10);
        System.out.println("店铺列表大小:" + shopList.size());
        System.out.println("店铺名:" + shopList.get(0).getShopName() + shopList.get(1).getShopName());
        shopCategory = new ShopCategory();
        shopCondition.setShopCategory(shopCategory);
        int count = shopDao.queryShopCount(shopCondition);
        System.out.println("店铺总数：" + count);
    }

    @Test
    @Ignore
    public void testQueryByShopId() {
        long shopId = 1;
        Shop shop = shopDao.queryByShopId(shopId);
        System.out.println("areaId:" + shop.getArea().getAreaId());
        System.out.println("areaName" + shop.getArea().getAreaName());
    }

    @Test
    @Ignore
    public void testInsertShop() {
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(2L);
        shopCategory.setShopCategoryId(1L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        ;
        shop.setShopName("测试的店铺");
        shop.setShopDesc("test");
        shop.setShopAddr("test");
        shop.setPhone("test");
        shop.setShopImg("test");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(0);
        shop.setAdvice("审核中");
        int effectedNum = shopDao.insertShop(shop);
        assertEquals(1, effectedNum);
    }

    @Test
    @Ignore
    public void testUpdateShop() {
        Shop shop = new Shop();
        shop.setShopId(1L);
        shop.setShopDesc("测试的描述");
        shop.setShopAddr("测试的地址");
        shop.setLastEditTime(new Date());
        int effectedNum = shopDao.updateShop(shop);
        assertEquals(1, effectedNum);
    }
}
