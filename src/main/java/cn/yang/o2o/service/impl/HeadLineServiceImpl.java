package cn.yang.o2o.service.impl;

import cn.yang.o2o.dao.HeadLineDao;
import cn.yang.o2o.dto.HeadLineExecution;
import cn.yang.o2o.dto.ImageHolder;
import cn.yang.o2o.entity.HeadLine;
import cn.yang.o2o.enums.HeadLineStateEnum;
import cn.yang.o2o.exceptions.HeadLineOperationException;
import cn.yang.o2o.service.HeadLineService;
import cn.yang.o2o.util.ImageUtil;
import cn.yang.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
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

    @Override
    @Transactional
    public List<HeadLine> getHeadLineList(HeadLine headLineCondition) {
        return headLineDao.queryHeadLine(headLineCondition);
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
