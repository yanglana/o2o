package cn.yang.o2o.web.superadmin;

import cn.yang.o2o.entity.Area;
import cn.yang.o2o.service.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author yanglan
 * @Date 2018/11/27 10:46
 */
@Controller
@RequestMapping("/superadmin")
public class AreaController {
    @Autowired
    private AreaService areaService;

    Logger logger = LoggerFactory.getLogger(AreaController.class);

    @RequestMapping(value = "/listarea",method = RequestMethod.GET)
    @ResponseBody/*返回体标志，页面结果格式为json*/
    private Map<String, Object> listArea() {

        logger.info("=====start=====");
        long startTime = System.currentTimeMillis();

        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<Area> list = new ArrayList<Area>();
        try {
            list = areaService.getAreaList();
            modelMap.put("rows", list);
            modelMap.put("total", list.size());
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
        }
        logger.error("test error!");
        long endTime = System.currentTimeMillis();
        logger.debug("costTimes:[{}ms]",endTime-startTime);
        logger.info("=====end=====");
        return modelMap;
    }


   @RequestMapping(value = "/in")
    private String index() {
        return "index";
    }


}
