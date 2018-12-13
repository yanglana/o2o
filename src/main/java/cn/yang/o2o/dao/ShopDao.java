package cn.yang.o2o.dao;

import cn.yang.o2o.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopDao {
    
    /*
     * @Description 分页查询店铺，可输入的条件有：店铺名(模糊)，店铺状态，店铺类别，区域Id,owner
     * @Param [shopCondition, rowIndex:从第几行开始读数据, pageSize:返回的条数]
     * @Return java.util.List<cn.yang.o2o.entity.Shop>
     */
    List<Shop> queryShopList(@Param("shopCondition")Shop shopCondition, @Param("rowIndex")int rowIndex,
                             @Param("pageSize")int pageSize);
    
    /*
     * @Description 返回queryShopList总数
     * @Param [shopCondition]
     * @Return int
     */
    int queryShopCount(@Param("shopCondition")Shop shopCondition);
    /*
     * @Description 通过shop id 查询店铺
     * @Param [shopId]
     * @Return cn.yang.o2o.entity.Shop
     */
    Shop queryByShopId(long shopId);
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
