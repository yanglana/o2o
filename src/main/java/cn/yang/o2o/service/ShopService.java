package cn.yang.o2o.service;

import cn.yang.o2o.dto.ImageHolder;
import cn.yang.o2o.dto.ShopExecution;
import cn.yang.o2o.entity.Shop;
import cn.yang.o2o.exceptions.ShopOperationException;

import java.io.InputStream;

public interface ShopService {

    /*
     * @Description 根据shopCondition分页返回相应店铺列表
     * @Param [shopCondition, pageIndex, pageSize]
     * @Return cn.yang.o2o.dto.ShopExecution
     */
    ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);

    /*
     * @Description 通过店铺Id获取店铺信息
     * @Param [shopId]
     * @Return cn.yang.o2o.entity.Shop
     */
    Shop getByShopId(long shopId) throws ShopOperationException;

    /*
     * @Description 更新店铺信息，包括对图片的处理
     * @Param [shop, shopImgInputStream, fileName]
     * @Return cn.yang.o2o.dto.ShopExecution
     */
    ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;

    /*
     * @Description 注册店铺信息，包括图片处理
     * @Param [shop, shopImgInputStream, fileName]
     * @Return cn.yang.o2o.dto.ShopExecution
     */
    ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;
}
