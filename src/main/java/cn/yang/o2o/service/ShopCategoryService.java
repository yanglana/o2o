package cn.yang.o2o.service;

import cn.yang.o2o.dto.ImageHolder;
import cn.yang.o2o.dto.ShopCategoryExecution;
import cn.yang.o2o.entity.ShopCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopCategoryService {
    public static final String SCLISTKEY = "shopcategorylist";
    /*
     * @Description 根据查询条件获取ShopCategory列表
     * @Param [shopCategory]
     * @Return java.util.List<cn.yang.o2o.entity.ShopCategory>
     */
    List<ShopCategory> getShopCategoryList(@Param("shopCategoryCondition") ShopCategory shopCategory);

    /*
     * @Description 增加商品类别
     * @Param [shopCategory]
     * @Return ShopCategoryExecution
     */
    ShopCategoryExecution addShopCategory(ShopCategory shopCategory, ImageHolder thumbnail);
}
