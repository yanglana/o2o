package cn.yang.o2o.service;

import cn.yang.o2o.BaseTest;
import cn.yang.o2o.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @Description TODO
 * @Author yanglan
 * @Date 2018/12/5 18:10
 */
public class ShopCategoryServiceTest extends BaseTest {
    @Autowired
    private ShopCategoryService shopCategoryService;

    @Test
    public void testShopCategoryService(){
        List<ShopCategory> categoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
        assertEquals(2,categoryList.size());
        ShopCategory testCategory = new ShopCategory();
        ShopCategory parentCategory = new ShopCategory();
        parentCategory.setShopCategoryId(3L);
        testCategory.setParent(parentCategory);
        categoryList = shopCategoryService.getShopCategoryList(testCategory);
        assertEquals(0,categoryList.size());
        //System.out.println(categoryList.get(0).getShopCategoryName());
    }

}
