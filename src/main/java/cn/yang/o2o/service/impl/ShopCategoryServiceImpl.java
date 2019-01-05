package cn.yang.o2o.service.impl;

import cn.yang.o2o.dao.ShopCategoryDao;
import cn.yang.o2o.dto.ImageHolder;
import cn.yang.o2o.dto.ShopCategoryExecution;
import cn.yang.o2o.entity.ShopCategory;
import cn.yang.o2o.enums.ShopCategoryStateEnum;
import cn.yang.o2o.exceptions.ShopCategoryOperationException;
import cn.yang.o2o.service.ShopCategoryService;
import cn.yang.o2o.util.ImageUtil;
import cn.yang.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Description TODO
 * @Author yanglan
 * @Date 2018/12/5 18:07
 */
@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {
    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @Override
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategory) {
        return shopCategoryDao.queryShopCategory(shopCategory);
    }

    @Override
    @Transactional
    public ShopCategoryExecution addShopCategory(ShopCategory shopCategory, ImageHolder thumbnail) {
        if (shopCategory != null) {
            shopCategory.setCreateTime(new Date());
            shopCategory.setLastEditTime(new Date());
            // 若店铺类别图不为空则添加
            if (thumbnail != null) {
                addThumbnail(shopCategory, thumbnail);
            }
            try {
                // 创建店铺类别信息
                int effectedNum = shopCategoryDao.insertShopCategory(shopCategory);
                if (effectedNum <= 0) {
                    throw new ShopCategoryOperationException("创建店铺类别失败!");
                }
            } catch (ShopCategoryOperationException e) {
                throw new ShopCategoryOperationException("创建店铺类别失败!"+e.getMessage());
            }
            return new ShopCategoryExecution(ShopCategoryStateEnum.SUCCESS,shopCategory);
        } else {
            return new ShopCategoryExecution(ShopCategoryStateEnum.EMPTY);
        }
    }

    private void addThumbnail(ShopCategory shopCategory, ImageHolder thumbnail) {
        String dest = PathUtil.getShopCategoryImagePath();
        String thumbnailAddr = ImageUtil.generateNormalImg(thumbnail, dest);
        shopCategory.setShopCategoryImg(thumbnailAddr);
    }

}
