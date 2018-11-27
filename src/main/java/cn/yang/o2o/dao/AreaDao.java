package cn.yang.o2o.dao;

import cn.yang.o2o.entity.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description TODO
 * @Author yanglan
 * @Date 2018/11/26 10:29
 */
public interface AreaDao {
    /*
     * @Description 列出区域列表
     * @Param []
     * @Return java.util.List<cn.yang.o2o.entity.Area>
     */
    List<Area> queryArea();
}
