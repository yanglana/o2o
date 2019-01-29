package cn.yang.o2o.service;

import cn.yang.o2o.BaseTest;
import cn.yang.o2o.dto.HeadLineExecution;
import cn.yang.o2o.dto.ImageHolder;
import cn.yang.o2o.entity.HeadLine;
import cn.yang.o2o.enums.HeadLineStateEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

/**
 * @Description TODO
 * @Author yanglan
 * @Date 2018/12/29 15:13
 */
public class HeadLineServiceTest extends BaseTest {
    @Autowired
    private HeadLineService headLineService;

    @Test
    public void testAddHeadLine() throws FileNotFoundException {
        HeadLine headLine = new HeadLine();
        headLine.setLineName("头条四");
        File file = new File("C:/Users/Administrator/Desktop/image/upload/images/item/headtitle/2017061320315746624.jpg");
        InputStream is = new FileInputStream(file);
        ImageHolder thumbnail = new ImageHolder(file.getName(), is);
        HeadLineExecution headLineExecution = headLineService.addHeadLine(headLine, thumbnail);
        assertEquals(HeadLineStateEnum.SUCCESS.getState(), headLineExecution.getState());
    }

    @Test
    public void testGetHeadLineList(){

    }
}
