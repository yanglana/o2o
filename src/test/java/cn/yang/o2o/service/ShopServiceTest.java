package cn.yang.o2o.service;

import cn.yang.o2o.BaseTest;
import cn.yang.o2o.dto.ImageHolder;
import cn.yang.o2o.dto.ShopExecution;
import cn.yang.o2o.entity.Area;
import cn.yang.o2o.entity.PersonInfo;
import cn.yang.o2o.entity.Shop;
import cn.yang.o2o.entity.ShopCategory;
import cn.yang.o2o.enums.ShopStateEnum;
import cn.yang.o2o.exceptions.ShopOperationException;
import org.junit.Ignore;
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
    public void testGetShopList() {
        Shop shopCondition = new Shop();
        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setShopCategoryId(4L);
        shopCondition.setShopCategory(shopCategory);
        ShopExecution se = shopService.getShopList(shopCondition, 2, 2);
        System.out.println("店铺列表数为：" + se.getShopList().size());
        System.out.println("店铺总数为：" + se.getCount());
    }

    @Test
    @Ignore
    public void testModifyShop() throws ShopOperationException, FileNotFoundException {
        Shop shop = new Shop();
        shop.setShopId(1L);
        shop.setShopName("修改后的店铺名称");
        File shopImg = new File("E:/image/dabai.jpg");
        InputStream is = new FileInputStream(shopImg);
        ImageHolder thumbnail = new ImageHolder(shopImg.getName(), is);
        ShopExecution shopExecution = shopService.modifyShop(shop, thumbnail);
        System.out.println("新的图片地址为：" + shopExecution.getShop().getShopImg());
    }

    @Test
    @Ignore
    public void testAddShop() throws FileNotFoundException {
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
        shop.setShopName("测试的店铺3");
        shop.setShopDesc("test3");
        shop.setShopAddr("test3");
        shop.setPhone("test3");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");
        File shopImage = new File("E:/image/xiaohuangren.jpg");
        InputStream is = new FileInputStream(shopImage);
        ImageHolder thumbnail = new ImageHolder(shopImage.getName(), is);
        ShopExecution se = shopService.addShop(shop, thumbnail);
        assertEquals(ShopStateEnum.CHECK.getState(), se.getState());
    }

}
