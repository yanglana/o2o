package cn.yang.o2o.dao;

import cn.yang.o2o.BaseTest;
import cn.yang.o2o.entity.HeadLine;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @Description TODO
 * @Author yanglan
 * @Date 2018/12/26 18:41
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HeadLineDaoTest extends BaseTest {
    @Autowired
    private HeadLineDao headLineDao;

    @Test
    public void testAInsertHeadLine(){
        HeadLine headLine = new HeadLine();
        headLine.setLineName("头条二");
        headLine.setLineLink("test");
        headLine.setLineImg("test");
        headLine.setEnableStatus(1);
        headLine.setPriority(2);
        headLine.setCreateTime(new Date());
        int effectedNum = headLineDao.insertHeadLine(headLine);
        assertEquals(1,effectedNum);
    }

    @Test
    public void testBQueryHeadLine(){
        HeadLine headLineCondition = new HeadLine();
        headLineCondition.setEnableStatus(0);
        List<HeadLine> headLineList = headLineDao.queryHeadLine(headLineCondition);
        assertEquals(1,headLineList.size());
    }
}
