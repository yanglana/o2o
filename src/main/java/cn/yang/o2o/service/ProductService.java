package cn.yang.o2o.service;

import cn.yang.o2o.dto.ImageHolder;
import cn.yang.o2o.dto.ProductExecution;
import cn.yang.o2o.entity.Product;
import cn.yang.o2o.exceptions.ProductOperationException;

import java.util.List;

public interface ProductService {

    /*
     * @Description 查询商品列表并分页，可输入的条件有： 商品名（模糊），商品状态，店铺Id,商品类别
     * @Param [productCondition, pageIndex, pageSize]
     * @Return cn.yang.o2o.dto.ProductExecution
     */
    ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize) throws ProductOperationException;

    /*
     * @Description 通过商品Id查询唯一的商品信息
     * @Param [product]
     * @Return cn.yang.o2o.dto.ProductExecution
     */
    Product getProductById(long productId) throws ProductOperationException;

    /*
     * @Description 添加商品信息以及图片处理
     * @Param [product, imageHolder, productImgList]
     * @Return cn.yang.o2o.dto.ProductExecution
     */
    ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList) throws ProductOperationException;

    /*
     * @Description 修改商品信息以及图片处理
     * @Param [product, thumbnail, productImgHolderList]
     * @Return cn.yang.o2o.dto.ProductExecution
     */
    ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList) throws ProductOperationException;
}
