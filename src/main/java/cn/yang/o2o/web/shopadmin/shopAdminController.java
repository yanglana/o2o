package cn.yang.o2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description TODO
 * @Author yanglan
 * @Date 2018/12/5 15:26
 */
@Controller
@RequestMapping(value = "shopadmin", method = {RequestMethod.GET})
public class shopAdminController {

    @RequestMapping("/shopoperation")
    public String shopoperation() {
        return "shop/shopoperation";//在spring-web.xml文件中设定了前后缀
    }

    @RequestMapping("/shoplist")
    public String shoplist() {
        return "shop/shoplist";
    }

    @RequestMapping("/shopmanagement")
    public String shopmanagement() {
        return "shop/shopmanagement";
    }
}
