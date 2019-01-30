package cn.yang.o2o.service.impl;

import cn.yang.o2o.cache.JedisUtil;
import cn.yang.o2o.dao.HeadLineDao;
import cn.yang.o2o.dto.HeadLineExecution;
import cn.yang.o2o.dto.ImageHolder;
import cn.yang.o2o.entity.HeadLine;
import cn.yang.o2o.enums.HeadLineStateEnum;
import cn.yang.o2o.exceptions.HeadLineOperationException;
import cn.yang.o2o.service.HeadLineService;
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
 * @Date 2018/12/26 19:09
 */
@Service
public class HeadLineServiceImpl implements HeadLineService {
    @Autowired
    private HeadLineDao headLineDao;
    @Autowired
    private JedisUtil.Keys jedisKeys;
    @Autowired
    private JedisUtil.Strings jedisStrings;

    private static Logger logger = LoggerFactory.getLogger(HeadLineServiceImpl.class);

    @Override
    @Transactional
    public List<HeadLine> getHeadLineList(HeadLine headLineCondition) {
        // 定义redis的key前缀
        String key = HLLISTKEY;
        // 定义接受对象
        List<HeadLine> headLineList = null;
        // 定义jackson数据转换操作类
        ObjectMapper mapper = new ObjectMapper();
        // 拼接出redis的key
        if (headLineCondition != null && headLineCondition.getEnableStatus() != null) {
            key = key + "_" + headLineCondition.getEnableStatus();
        }
        // 判断key是否存在
        if (!jedisKeys.exists(key)) {
            // 若不存在，则从数据库里面取出相应数据
            headLineList = headLineDao.queryHeadLine(headLineCondition);
            // 将相关的实体类集合转换成string,存入redis里面对应的key中
            String jsonString;
            try {
                jsonString = mapper.writeValueAsString(headLineList);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
                throw new HeadLineOperationException(e.getMessage());
            }
            jedisStrings.set(key, jsonString);
        } else {
            // 若存在，则直接从redis里面取出相应数据
            String jsonString = jedisStrings.get(key);
            // 指定要将string转换成的集合类型
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, HeadLine.class);
            try {
                // 将相关key对应的value里的的string转换成对象的实体类集合
                headLineList = mapper.readValue(jsonString,javaType);
            } catch (IOException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
                throw new HeadLineOperationException(e.getMessage());
            }
        }
        return headLineList;
    }

    @Override
    @Transactional
    public HeadLineExecution addHeadLine(HeadLine headLine, ImageHolder thumbnail) throws HeadLineOperationException {
        if (headLine != null) {
            //设置默认属性
            headLine.setCreateTime(new Date());
            headLine.setLastEditTime(new Date());
            headLine.setEnableStatus(1);
            if (thumbnail != null) {
                addImage(headLine, thumbnail);
            }
            try {
                int effectedNum = headLineDao.insertHeadLine(headLine);
                if (effectedNum <= 0) {
                    throw new HeadLineOperationException("创建头条失败");
                }
            } catch (Exception e) {
                throw new HeadLineOperationException("创建头条失败:" + e.getMessage());
            }
            return new HeadLineExecution(HeadLineStateEnum.SUCCESS, headLine);
        } else {
            return new HeadLineExecution(HeadLineStateEnum.EMPTY);
        }
    }

    private void addImage(HeadLine headLine, ImageHolder thumbnail) {
        String dest = PathUtil.getHeadLineImagePath();
        String thumbnailAddr = ImageUtil.generateNormalImg(thumbnail,dest);
        headLine.setLineImg(thumbnailAddr);
        String path = "http://localhost:8080"+thumbnailAddr;
        path = PathUtil.transferToLink(path);
        headLine.setLineLink(path);
    }
}
