package cn.yang.o2o.dao;

import cn.yang.o2o.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductCategoryDao {

    /*
     * @Description 通过shop id 查询店铺商品类别
     * @Param
     * @Return
     */
    List<ProductCategory> queryProductCategoryList(long shopId);

    /*
     * @Description 批量新增商品类别
     * @Param [productCategoryList]
     * @Return int
     */
    int batchInsertProductCategory(List<ProductCategory> productCategoryList);

    /*
     * @Description 删除指定商品类别
     * @Param [productCategoryId, shopId]
     * @Return int
     */
    int deleteProductCategory(@Param("productCategoryId") long productCategoryId, @Param("shopId") long shopId);
}
