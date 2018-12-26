package cn.yang.o2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description 主要用来解析路由并转发到相应的html中
 * @Author yanglan
 * @Date 2018/12/5 15:26
 */
@Controller
@RequestMapping(value = "shopadmin", method = {RequestMethod.GET})
public class shopAdminController {

    @RequestMapping("/shopoperation")
    public String shopOperation() {
        // 转发至店铺注册/编辑页面
        return "shop/shopoperation";//在spring-web.xml文件中设定了前后缀
    }

    @RequestMapping("/shoplist")
    public String shopList() {
        // 转发至店铺列表页面
        return "shop/shoplist";
    }

    @RequestMapping("/shopmanagement")
    public String shopManagement() {
        // 转发至店铺管理页面
        return "shop/shopmanagement";
    }

    @RequestMapping(value = "/productcategorymanagement", method = RequestMethod.GET)
    public String productCategoryManagement(){
        // 转发至商品类别管理页面
        return "shop/productcategorymanagement";
    }

    @RequestMapping(value = "/productoperation")
    public String productOperation() {
        // 转发至商品添加/编辑页面
        return "shop/productoperation";
    }

    @RequestMapping(value = "/productmanagement")
    public String productManagement() {
        // 转发至商品管理页面
        return "shop/productmanagement";
    }
}
