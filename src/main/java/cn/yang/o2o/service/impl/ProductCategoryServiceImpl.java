package cn.yang.o2o.service.impl;

import cn.yang.o2o.dao.ProductCategoryDao;
import cn.yang.o2o.dto.ProductCategoryExecution;
import cn.yang.o2o.entity.ProductCategory;
import cn.yang.o2o.enums.ProductCategoryStateEnum;
import cn.yang.o2o.exceptions.ProductCategoryOperationException;
import cn.yang.o2o.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description TODO
 * @Author yanglan
 * @Date 2018/12/20 10:03
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    ProductCategoryDao productCategoryDao;

    @Override
    public List<ProductCategory> getProductCategoryList(long shopId) {
        return productCategoryDao.queryProductCategoryList(shopId);
    }

    @Override
    @Transactional
    public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException {
        if (productCategoryList != null && productCategoryList.size()>0){
            try {
                int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
                if (effectedNum <= 0) {
                    throw new ProductCategoryOperationException("店铺类别创建失败");
                } else {
                    return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS, productCategoryList);
                }
            } catch (Exception e) {
                throw new ProductCategoryOperationException("batchAddProductCategory error: " + e.getMessage());
            }
        } else {
            return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
        }
    }

    @Override
    public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId) throws ProductCategoryOperationException {
        // 解除tb_product里的商品与该producategoryId的关联
        // TODO

        // 删除该productCategory
        try {
            int effectedNum = productCategoryDao.deleteProductCategory(productCategoryId,shopId);
            if (effectedNum <= 0) {
                throw new ProductCategoryOperationException("商品类别删除失败");
            } else {
                return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
            }
        } catch (Exception e) {
            throw new ProductCategoryOperationException("deleteProductCategory error:" + e.getMessage());
        }
    }
}
