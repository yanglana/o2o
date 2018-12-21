package cn.yang.o2o.service;

import cn.yang.o2o.dto.ProductCategoryExecution;
import cn.yang.o2o.entity.ProductCategory;
import cn.yang.o2o.exceptions.ProductCategoryOperationException;

import java.util.List;

public interface ProductCategoryService {
    
    /*
     * @Description 查询指定某个店铺下的所有商品类别信息
     * @Param [shopId]
     * @Return java.util.List<cn.yang.o2o.entity.ProductCategory>
     */
    List<ProductCategory> getProductCategoryList(long shopId);

    ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList)
            throws ProductCategoryOperationException;

    /*
     * @Description 将此类别下的商品里的类别id置为空，再删除掉该商品类别
     * @Param [productCategoryId, shopId]
     * @Return cn.yang.o2o.dto.ProductCategoryExecution
     */
    ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId)
            throws ProductCategoryOperationException;
}
