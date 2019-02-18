package cn.yang.o2o.service;

import cn.yang.o2o.BaseTest;
import cn.yang.o2o.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @Description TODO
 * @Author yanglan
 * @Date 2018/11/27 9:55
 */
public class AreaServiceTest extends BaseTest {
    @Autowired
    private AreaService areaService;
    @Autowired
    private CacheService cacheService;

    @Test
    public void getAreaList() {
        List<Area> areaList = areaService.getAreaList();
        assertEquals(4, areaList.size());
        cacheService.removeFromCache(areaService.AREALISTKEY);
        areaList = areaService.getAreaList();
    }
}
