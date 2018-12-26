package cn.yang.o2o.dao;

import cn.yang.o2o.entity.ProductImg;

import java.util.List;

public interface ProductImgDao {

    /*
     * @Description 批量添加商品详情图片
     * @Param [productImgList]
     * @Return int
     */
    int batchInsertProductImg(List<ProductImg> productImgList);

    /*
     * @Description 列出某个商品的详情图列表
     * @Param [productId]
     * @Return java.util.List<cn.yang.o2o.entity.ProductImg>
     */
    List<ProductImg> queryProductImgList(long productId);

    /*
     * @Description 删除指定商品下的所有详情图
     * @Param [productId]
     * @Return int
     */
    int deleteProductImgByProductId(long productId);
}
