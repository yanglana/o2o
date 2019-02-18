package cn.yang.o2o.service.impl;

import cn.yang.o2o.cache.JedisUtil;
import cn.yang.o2o.dao.ShopCategoryDao;
import cn.yang.o2o.dto.ImageHolder;
import cn.yang.o2o.dto.ShopCategoryExecution;
import cn.yang.o2o.entity.ShopCategory;
import cn.yang.o2o.enums.ShopCategoryStateEnum;
import cn.yang.o2o.exceptions.ShopCategoryOperationException;
import cn.yang.o2o.service.ShopCategoryService;
import cn.yang.o2o.util.ImageUtil;
import cn.yang.o2o.util.PathUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
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
    @Autowired
    private JedisUtil.Keys jedisKeys;
    @Autowired
    private JedisUtil.Strings jedisStrings;

    private static Logger logger = LoggerFactory.getLogger(ShopCategoryServiceImpl.class);

    @Override
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {
        // 定义redis的key前缀
        String key = SCLISTKEY;
        // 定义接收对象
        List<ShopCategory> shopCategoryList = null;
        // 定义jackson数据转换操作类
        ObjectMapper mapper = new ObjectMapper();
        // 拼接出redis的key
        if (shopCategoryCondition == null) {
            // 若查询条件为空，则列出所有首页大类，即parentId为空的店铺类别
            key += "_allFirstlevel";
        } else if (shopCategoryCondition != null && shopCategoryCondition.getParent() != null &&
                shopCategoryCondition.getParent().getShopCategoryId() != null) {
            // 若parentId为非空，则列出该parentId下的所有子类别
            key += "_parent" + shopCategoryCondition.getParent().getShopCategoryId();
        } else if (shopCategoryCondition != null) {
            // 列出所有子类别，不管其属于哪个类，都列出来
            key += "_allsecondlevel";
        }
        // 判断key是否存在
        if (!jedisKeys.exists(key)) {
            // 若不存在，则从数据库里面取出相应数据
            shopCategoryList = shopCategoryDao.queryShopCategory(shopCategoryCondition);
            // 将相关的实体类集合转换成string,存入redis里面对应的key中
            String jsonString = null;
            try {
                jsonString = mapper.writeValueAsString(shopCategoryList);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
                throw new ShopCategoryOperationException(e.getMessage());
            }
            jedisStrings.set(key, jsonString);
        } else {
            // 若存在，则直接从redis里面取出相应数据
            String jsonString = jedisStrings.get(key);
            // 指定要将string转换成的集合类型
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, ShopCategory.class);
            try {
                // 将相关key对应的value里的的string转换成对象的实体类集合
                shopCategoryList = mapper.readValue(jsonString, javaType);
            } catch (IOException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
                throw new ShopCategoryOperationException(e.getMessage());
            }
        }
        return shopCategoryList;
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
                throw new ShopCategoryOperationException("创建店铺类别失败!" + e.getMessage());
            }
            return new ShopCategoryExecution(ShopCategoryStateEnum.SUCCESS, shopCategory);
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
