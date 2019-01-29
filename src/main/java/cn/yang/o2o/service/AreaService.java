package cn.yang.o2o.service;

import cn.yang.o2o.entity.Area;

import java.util.List;

public interface AreaService {
    public static final String AREALISTKEY = "arealist";
    
    /*
     * @Description 获取区域列表，优先从缓存获取
     * @Param []
     * @Return java.util.List<cn.yang.o2o.entity.Area>
     */
    List<Area> getAreaList();
}
