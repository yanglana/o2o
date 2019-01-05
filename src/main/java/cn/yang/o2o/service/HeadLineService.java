package cn.yang.o2o.service;

import cn.yang.o2o.dto.HeadLineExecution;
import cn.yang.o2o.dto.ImageHolder;
import cn.yang.o2o.entity.HeadLine;
import cn.yang.o2o.exceptions.HeadLineOperationException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface HeadLineService {
    
    /*
     * @Description 根据传入的条件返回指定的头条列表
     * @Param [headLineCondition]
     * @Return java.util.List<cn.yang.o2o.entity.HeadLine>
     */
    List<HeadLine> getHeadLineList(HeadLine headLineCondition);

    HeadLineExecution addHeadLine(HeadLine headLine, ImageHolder imageHolder) throws HeadLineOperationException;
}
