package cn.yang.o2o.service.impl;

import cn.yang.o2o.dao.ShopDao;
import cn.yang.o2o.dto.ShopExecution;
import cn.yang.o2o.entity.Shop;
import cn.yang.o2o.enums.ShopStateEnum;
import cn.yang.o2o.exceptions.ShopOperationException;
import cn.yang.o2o.service.ShopService;
import cn.yang.o2o.util.ImageUtil;
import cn.yang.o2o.util.PageCalculator;
import cn.yang.o2o.util.PathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

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
    public ShopExecution getShopList(Shop shopCondition,int pageIndex,int pageSize) {
        //将页码转换成行码
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex,pageSize);
        //依据查询条件，调用dao层返回相关的店铺列表
        List<Shop> shopList = shopDao.queryShopList(shopCondition,rowIndex,pageSize);
        //依据相同的查询条件，返回店铺总数
        int count = shopDao.queryShopCount(shopCondition);
        ShopExecution se = new ShopExecution();
        if (shopList != null) {
            se.setShopList(shopList);
            se.setCount(count);
        } else {
            se.setState(ShopStateEnum.INNER_ERROR.getState());
        }
        return se;
    }

    @Override
    public Shop getByShopId(long shopId) throws ShopOperationException {
        return shopDao.queryByShopId(shopId);
    }

    @Override
    public ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException {
        if (shop == null && shop.getShopId() == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        } else {
            try {
                //1.判断是否需要处理图片
                if (shopImgInputStream != null && fileName != null && !"".equals(fileName)) {
                    Shop tempShop = shopDao.queryByShopId(shop.getShopId());
                    if (tempShop.getShopImg() != null) {
                        ImageUtil.deleteFileOrPath(tempShop.getShopImg());
                    }
                    addShopImg(shop, shopImgInputStream, fileName);
                }
                //2.新店铺信息
                shop.setLastEditTime(new Date());
                int effectedNum = shopDao.updateShop(shop);
                if (effectedNum <= 0) {
                    return new ShopExecution(ShopStateEnum.INNER_ERROR);
                } else {
                    shop = shopDao.queryByShopId(shop.getShopId());
                    return new ShopExecution(ShopStateEnum.SUCCESS, shop);
                }
            } catch (Exception e) {
                throw new ShopOperationException("modifyShop error:" + e.getMessage());
            }
        }
    }

    @Override
    public ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) {
        //空值判断
        if (shop == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try {
            // 给店铺信息赋初始值
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            // 添加店铺信息
            int effectedNum = shopDao.insertShop(shop);
            logger.debug("effectedNum:" + effectedNum);
            logger.debug("shopImg:" + shopImgInputStream);
            if (effectedNum <= 0) {
                throw new ShopOperationException("店铺创建失败");
            } else {
                if (shopImgInputStream != null) {
                    logger.debug("shopImg2:" + shopImgInputStream);
                    try {
                        // 存储图片
                        addShopImg(shop, shopImgInputStream, fileName);
                        logger.debug("addShopImg");
                    } catch (Exception e) {
                        throw new ShopOperationException("addShopImg erro:" + e.getMessage());
                    }
                    effectedNum = shopDao.updateShop(shop);
                    logger.debug("effectedNum2:" + effectedNum);
                    if (effectedNum <= 0) {
                        throw new ShopOperationException("更新图片地址失败");
                    }
                }
            }
        } catch (Exception e) {
            throw new ShopOperationException("addShop erro:" + e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK, shop);
    }

    private void addShopImg(Shop shop, InputStream shopImgInputStream, String fileName) {
        //获取shop图片目录的相对值路径
        logger.debug("addShopImg2");
        logger.debug("shopId:" + shop.getShopId());
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        logger.debug("dest2:" + dest);
        String shopImgAddr = ImageUtil.generateThumbnail(shopImgInputStream, fileName, dest);
        logger.debug("shopImgAddr:" + shopImgAddr);
        shop.setShopImg(shopImgAddr);
    }
}
