package cn.yang.o2o.service.impl;

import cn.yang.o2o.dao.ShopDao;
import cn.yang.o2o.dto.ShopExecution;
import cn.yang.o2o.entity.Shop;
import cn.yang.o2o.enums.ShopStateEnum;
import cn.yang.o2o.exceptions.ShopOperationException;
import cn.yang.o2o.service.ShopService;
import cn.yang.o2o.util.ImageUtil;
import cn.yang.o2o.util.PathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Date;

/**
 * @Description TODO
 * @Author yanglan
 * @Date 2018/11/29 11:42
 */
@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopDao shopDao;

    Logger logger = LoggerFactory.getLogger(ShopService.class);

    @Override
    public ShopExecution addShop(Shop shop, File shopImg) {
       //空值判断
        if (shop == null){
           return new ShopExecution(ShopStateEnum.NULL_SHOP);
       }
        try {
            // 给店铺信息赋初始值
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            // 添加店铺信息
            int effectedNum = shopDao.insertShop(shop);
            logger.debug("effectedNum:"+effectedNum);
            logger.debug("shopImg:"+shopImg);
            if (effectedNum <= 0) {
                throw new ShopOperationException("店铺创建失败");
            }else {
                if (shopImg != null) {
                    logger.debug("shopImg2:"+shopImg);
                    try {
                        // 存储图片
                        addShopImg(shop,shopImg);
                        logger.debug("addShopImg");
                    } catch (Exception e) {
                        throw new ShopOperationException("addShopImg erro:"+e.getMessage());
                    }
                    effectedNum = shopDao.updateShop(shop);
                    logger.debug("effectedNum2:"+effectedNum);
                    if (effectedNum <= 0) {
                        throw new ShopOperationException("更新图片地址失败");
                    }
                }
            }
        } catch (Exception e) {
            throw new ShopOperationException("addShop erro:"+e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK,shop);
    }

    private void addShopImg(Shop shop,File shopImg) {
        //获取shop图片目录的相对值路径
        logger.debug("addShopImg2");
        logger.debug("shopId:"+shop.getShopId());
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        logger.debug("dest2:"+dest);
        String shopImgAddr = ImageUtil.generateThumbnail(shopImg,dest);
        logger.debug("shopImgAddr:"+shopImgAddr);
        shop.setShopImg(shopImgAddr);
    }
}
