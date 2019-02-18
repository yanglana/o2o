package cn.yang.o2o.dao;

import cn.yang.o2o.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductDao {

    /*
     * @Description 查询商品列表并分页，可输入的条件有： 商品名（模糊），商品状态，店铺Id,商品类别
     * @Param [productCondition, rowIndex, pageSize]
     * @Return java.util.List<cn.yang.o2o.entity.Product>
     */
    List<Product> queryProductList(@Param("productCondition") Product productCondition, @Param("rowIndex") int rowIndex,
                                   @Param("pageSize") int pageSize);

    /*
     * @Description 查询对应的商品总数
     * @Param [productCondition]
     * @Return int
     */
    int queryProductCount(@Param("productCondition") Product productCondition);

    /*
     * @Description 通过productId查询唯一的商品信息
     * @Param [productId]
     * @Return cn.yang.o2o.entity.Product
     */
    Product queryProductById(long productId);

    /*
     * @Description 插入商品
     * @Param [product]
     * @Return int
     */
    int insertProduct(Product product);

    /*
     * @Description 更新商品信息
     * @Param [product]
     * @Return int
     */
    int updateProduct(Product product);

    /*
     * @Description 删除商品类别之前，将商品类别ID置为空
     * @Param [productCategoryId]
     * @Return int
     */
    int updateProductCategoryToNull(long productCategoryId);

    /*
     * @Description 删除商品
     * @Param [productId, shopId]
     * @Return int
     */
    int deleteProduct(@Param("productId") long productId, @Param("shopId") long shopId);
}
