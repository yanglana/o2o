package cn.yang.o2o.service;

import cn.yang.o2o.BaseTest;
import cn.yang.o2o.dto.ImageHolder;
import cn.yang.o2o.dto.ShopCategoryExecution;
import cn.yang.o2o.entity.ShopCategory;
import cn.yang.o2o.enums.ShopCategoryStateEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
    public void testGetShopCategoryService(){
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

    @Test
    public void testAddShopCategoryService() throws FileNotFoundException {
        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setShopCategoryName("声乐舞蹈");
        shopCategory.setShopCategoryDesc("声乐舞蹈");
        shopCategory.setPriority(49);
        ShopCategory shopCategoryParent = new ShopCategory();
        shopCategoryParent.setShopCategoryId(12L);
        shopCategory.setParent(shopCategoryParent);
        File thumbnailFile = new File("C:/Users/Administrator/Desktop/image/upload/images/item/shopcategory/2017060421595843693.png");
        InputStream is = new FileInputStream(thumbnailFile);
        ImageHolder imageHolder = new ImageHolder(thumbnailFile.getName(),is);
        ShopCategoryExecution shopCategoryExecution = shopCategoryService.addShopCategory(shopCategory,imageHolder);
        assertEquals(ShopCategoryStateEnum.SUCCESS.getState(),shopCategoryExecution.getState());

        ShopCategory shopCategory2 = new ShopCategory();
        shopCategory2.setShopCategoryName("演出道具");
        shopCategory2.setShopCategoryDesc("演出道具");
        shopCategory2.setPriority(45);
        ShopCategory shopCategoryParent2 = new ShopCategory();
        shopCategoryParent2.setShopCategoryId(13L);
        shopCategory2.setParent(shopCategoryParent2);
        File thumbnailFile2 = new File("C:/Users/Administrator/Desktop/image/upload/images/item/shopcategory/2017060422114076152.png");
        InputStream is2 = new FileInputStream(thumbnailFile2);
        ImageHolder imageHolder2 = new ImageHolder(thumbnailFile2.getName(),is2);
        ShopCategoryExecution shopCategoryExecution2 = shopCategoryService.addShopCategory(shopCategory2,imageHolder2);
        assertEquals(ShopCategoryStateEnum.SUCCESS.getState(),shopCategoryExecution2.getState());

        ShopCategory shopCategory3 = new ShopCategory();
        shopCategory3.setShopCategoryName("交通工具");
        shopCategory3.setShopCategoryDesc("交通工具");
        shopCategory3.setPriority(44);
        ShopCategory shopCategoryParent3 = new ShopCategory();
        shopCategoryParent3.setShopCategoryId(13L);
        shopCategory3.setParent(shopCategoryParent3);
        File thumbnailFile3 = new File("C:/Users/Administrator/Desktop/image/upload/images/item/shopcategory/2017060422121144586.png");
        InputStream is3 = new FileInputStream(thumbnailFile3);
        ImageHolder imageHolder3 = new ImageHolder(thumbnailFile3.getName(),is3);
        ShopCategoryExecution shopCategoryExecution3 = shopCategoryService.addShopCategory(shopCategory3,imageHolder3);
        assertEquals(ShopCategoryStateEnum.SUCCESS.getState(),shopCategoryExecution3.getState());
    }
}
