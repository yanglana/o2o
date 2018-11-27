package cn.yang.o2o.service.impl;

import cn.yang.o2o.dao.AreaDao;
import cn.yang.o2o.entity.Area;
import cn.yang.o2o.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description TODO
 * @Author yanglan
 * @Date 2018/11/27 9:56
 */

@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaDao areaDao;

    @Override
    public List<Area> getAreaList() {
        return areaDao.queryArea();
    }
}
