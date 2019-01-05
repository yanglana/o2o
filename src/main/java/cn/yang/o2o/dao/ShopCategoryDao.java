package cn.yang.o2o.dao;

import cn.yang.o2o.entity.Shop;
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

    /*
     * @Description 通过Id返回唯一的店铺类别信息
     * @Param [shopCategoryId]
     * @Return cn.yang.o2o.entity.ShopCategory
     */
    ShopCategory queryShopCategoryById(long shopCategoryId);

    /*
     * @Description 根据传入的Id列表查询店铺类别信息(供超级管理员选定删除类别的时候用，主要是处理图片)
     * @Param [shopCategoryIdList]
     * @Return java.util.List<cn.yang.o2o.entity.ShopCategory>
     */
    List<ShopCategory> queryShopCategoryByIds(List<Long> shopCategoryIdList);

    /*
     * @Description 插入一条店铺类别信息
     * @Param [shopCategory]
     * @Return int
     */
    int insertShopCategory(@Param("shopCategoryCondition") ShopCategory shopCategory);

    /*
     * @Description 更新店铺类别信息
     * @Param [shopCategory]
     * @Return int
     */
    int updateShopCategory(ShopCategory shopCategory);

    /*
     * @Description 删除店铺类别
     * @Param [shopCategoryId]
     * @Return int
     */
    int deleteShopCategory(long shopCategoryId);

    /*
     * @Description 批量删除店铺类别
     * @Param [shopCategoryIdList]
     * @Return int
     */
    int batchDeleteShopCategory(List<Long> shopCategoryIdList);
}
