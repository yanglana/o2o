package cn.yang.o2o.dao;

import cn.yang.o2o.BaseTest;
import cn.yang.o2o.entity.ProductImg;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @Description TODO
 * @Author yanglan
 * @Date 2018/12/21 18:5 4
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductImgDaoTest extends BaseTest {

    @Autowired
    private ProductImgDao productImgDao;

    @Test
    public void testAInsertProductImg() {
        List<ProductImg> productImgList = new ArrayList<ProductImg>();
        ProductImg productImg1 = new ProductImg();
        productImg1.setImgAddr("test1");
        productImg1.setImgDesc("图1");
        productImg1.setCreateTime(new Date());
        productImg1.setPriority(1);
        productImg1.setProductId(13L);
        ProductImg productImg2 = new ProductImg();
        productImg2.setImgAddr("test2");
        productImg2.setImgDesc("图2");
        productImg2.setCreateTime(new Date());
        productImg2.setPriority(2);
        productImg2.setProductId(13L);
        ProductImg productImg3 = new ProductImg();
        productImg3.setImgAddr("test3");
        productImg3.setImgDesc("图3");
        productImg3.setCreateTime(new Date());
        productImg3.setPriority(3);
        productImg3.setProductId(13L);
        productImgList.add(productImg1);
        productImgList.add(productImg2);
        productImgList.add(productImg3);
        int effectedNum = productImgDao.batchInsertProductImg(productImgList);
        assertEquals(3, effectedNum);
    }
}
