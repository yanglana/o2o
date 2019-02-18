package cn.yang.o2o.dao;

import cn.yang.o2o.BaseTest;
import cn.yang.o2o.dto.ImageHolder;
import cn.yang.o2o.entity.Shop;
import cn.yang.o2o.entity.ShopCategory;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @Description TODO
 * @Author yanglan
 * @Date 2018/12/5 17:01
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ShopCategoryDaoTest extends BaseTest {
    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @Test
    public void testBQueryShopCategory() {
        List<ShopCategory> shopCategoryList = shopCategoryDao.queryShopCategory(null);
        assertEquals(2, shopCategoryList.size());
        ShopCategory testCategory = new ShopCategory();
        ShopCategory parentCategory = new ShopCategory();
        parentCategory.setShopCategoryId(1L);
        testCategory.setParent(parentCategory);
        shopCategoryList = shopCategoryDao.queryShopCategory(testCategory);
        assertEquals(3, shopCategoryList.size());
        System.out.println(shopCategoryList.get(0).getShopCategoryName());
    }

    @Test
    public void testAInsertShopCategory() {
        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setShopCategoryName("二手市场");
        shopCategory.setShopCategoryDesc("二手商品交易");
        shopCategory.setPriority(100);
        int effectedNum = shopCategoryDao.insertShopCategory(shopCategory);
        assertEquals(1, effectedNum);
    }

    @Test
    public void testCUpdateShopCategory() {
        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setShopCategoryId(3L);
        shopCategory.setShopCategoryName("二手");
        int effectedNum = shopCategoryDao.updateShopCategory(shopCategory);
        assertEquals(1, effectedNum);
    }

    //因为店铺类别与店铺存在一对多关系，所以绑定了店铺的店铺类别不能删，需要先删店铺
    @Test
    public void testDDeleteShopCategory() {
        long shopCategoryId = 5L;
        int effectedNum = shopCategoryDao.deleteShopCategory(shopCategoryId);
        assertEquals(1, effectedNum);
    }

    @Test
    public void testEBatchDeleteShopCategory() {
        List<Long> shopCategoryIdList = new ArrayList<Long>();
        long shopCategoryId1 = 6L;
        long shopCategoryId2 = 7L;
        shopCategoryIdList.add(shopCategoryId1);
        shopCategoryIdList.add(shopCategoryId2);
        int effectedNum = shopCategoryDao.batchDeleteShopCategory(shopCategoryIdList);
        assertEquals(2, effectedNum);
    }
}
