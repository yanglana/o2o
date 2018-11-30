package cn.yang.o2o.dao;

import cn.yang.o2o.entity.Shop;

public interface ShopDao {
    /*
     * @Description 增加商铺
     * @Param [shop]
     * @Return int
     */
    int insertShop(Shop shop);

    /*
     * @Description 更新商铺
     * @Param 
     * @Return 
     */
    int updateShop(Shop shop);
}
