package cn.yang.o2o.dao;

import cn.yang.o2o.entity.ShopCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description TODO
 * @Author yanglan
 * @Date 2018/12/5 16:49
 */
public interface ShopCategoryDao {
    
    /*
     * @Description 根据传入的查询条件返回店铺类别列表
     * @Param [shopCategory]
     * @Return java.util.List<cn.yang.o2o.entity.ShopCategory>
     */
    List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition") ShopCategory shopCategory);

}
