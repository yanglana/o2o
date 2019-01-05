package cn.yang.o2o.dao;

import cn.yang.o2o.entity.HeadLine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HeadLineDao {

    /*
     * @Description 根据传入的查询条件(头条名查询头条)
     * @Param [headLineCondition]
     * @Return java.util.List<cn.yang.o2o.entity.HeadLine>
     */
    List<HeadLine> queryHeadLine(@Param("headLineCondition") HeadLine headLineCondition);

    /*
     * @Description 插入头条
     * @Param [headLine]
     * @Return int
     */
    int insertHeadLine(HeadLine headLine);
}
