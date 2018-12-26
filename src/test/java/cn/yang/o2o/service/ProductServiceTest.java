package cn.yang.o2o.service;

import cn.yang.o2o.BaseTest;
import cn.yang.o2o.dto.ImageHolder;
import cn.yang.o2o.dto.ProductExecution;
import cn.yang.o2o.entity.*;
import cn.yang.o2o.enums.ProductStateEnum;
import cn.yang.o2o.exceptions.ProductOperationException;
import cn.yang.o2o.util.ImageUtil;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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
 * @Date 2018/12/22 15:43
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductServiceTest extends BaseTest {
    @Autowired
    private ProductService productService;

    @Test
    public void testAddProduct() throws ProductOperationException, FileNotFoundException {
        // 创建shopId为1且productCategoryId为1的商品实例并给其成员变量赋值
        Product product = new Product();
        Shop shop = new Shop();
        shop.setShopId(1L);
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(1L);
        product.setShop(shop);
        product.setProductCategory(productCategory);
        product.setProductName("测试商品1");
        product.setProductDesc("测试商品1");
        product.setPriority(20);
        product.setCreateTime(new Date());
        product.setEnableStatus(ProductStateEnum.SUCCESS.getState());
        // 创建缩略图文件流
        File thumbnailFile = new File("E:/image/xiaohuangren.jpg");
        InputStream is = new FileInputStream(thumbnailFile);
        ImageHolder thumbnail = new ImageHolder(thumbnailFile.getName(),is);
        // 创建两个商品详情图文件流并将他们添加到详情图列表中
        File productImg1 = new File("E:/image/xiaohuangren.jpg");
        InputStream is1 = new FileInputStream(productImg1);
        File productImg2 = new File("E:/image/dabai.jpg");
        InputStream is2 = new FileInputStream(productImg2);
        List<ImageHolder> productImgList = new ArrayList<ImageHolder>();
        productImgList.add(new ImageHolder(productImg1.getName(),is1));
        productImgList.add(new ImageHolder(productImg2.getName(),is2));
        // 添加商品并验证
        ProductExecution pe = productService.addProduct(product,thumbnail,productImgList);
        System.out.println(pe.getState());
        assertEquals(ProductStateEnum.SUCCESS.getState(),pe.getState());
    }

    @Test
    public void testBModifyProduct() throws FileNotFoundException {
        // 创建shopId为1且productCategoryId为1的商品实例并给其成员变量赋值
        Product product = new Product();
        Shop shop = new Shop();
        shop.setShopId(1L);
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(1L);
        product.setProductId(18L);
        product.setShop(shop);
        product.setProductCategory(productCategory);
        product.setProductName("正式的商品");
        product.setProductDesc("正式的商品");
        // 创建缩略图文件流
        File thumbnailFile = new File("E:/image/test.png");
        InputStream is = new FileInputStream(thumbnailFile);
        ImageHolder thumbnail = new ImageHolder(thumbnailFile.getName(),is);
        // 创建两个商品详情图文件流并将他们添加到详情图列表中
        File productImg1 = new File("C:/Users/Administrator/Desktop/新建文件夹/h1.png");
        InputStream is1 = new FileInputStream(productImg1);
        File productImg2 = new File("C:/Users/Administrator/Desktop/新建文件夹/h2.png");
        InputStream is2 = new FileInputStream(productImg2);
        List<ImageHolder> productImgList = new ArrayList<ImageHolder>();
        productImgList.add(new ImageHolder(productImg1.getName(),is1));
        productImgList.add(new ImageHolder(productImg2.getName(),is2));
        // 添加商品并验证
        ProductExecution pe = productService.modifyProduct(product,thumbnail,productImgList);
        assertEquals(ProductStateEnum.SUCCESS.getState(),pe.getState());
    }
}
