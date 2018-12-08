package cn.yang.o2o.service;

import cn.yang.o2o.BaseTest;
import cn.yang.o2o.dto.ShopExecution;
import cn.yang.o2o.entity.Area;
import cn.yang.o2o.entity.PersonInfo;
import cn.yang.o2o.entity.Shop;
import cn.yang.o2o.entity.ShopCategory;
import cn.yang.o2o.enums.ShopStateEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * @Description TODO
 * @Author yanglan
 * @Date 2018/11/29 14:03
 */
public class ShopServiceTest extends BaseTest {
    @Autowired
    private ShopService shopService;

    @Test
    public void testAddShop() throws FileNotFoundException {
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(1L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试的店铺3");
        shop.setShopDesc("test3");
        shop.setShopAddr("test3");
        shop.setPhone("test3");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");
        File shopImage = new File("E:/image/xiaohuangren.jpg");
        InputStream is=new FileInputStream(shopImage);
        ShopExecution se = shopService.addShop(shop, is,shopImage.getName());
        assertEquals(ShopStateEnum.CHECK.getState(), se.getState());
    }

}
