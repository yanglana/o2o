package cn.yang.o2o.web.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description TODO
 * @Author yanglan
 * @Date 2018/12/29 11:10
 */
@Controller
@RequestMapping(value = "/frontend",method = {RequestMethod.GET})
public class FrontendController {

    /*
     * @Description 首页路由
     * @Param []
     * @Return java.lang.String
     */
    @RequestMapping(value = "/index")
    private String index() {
        return "/frontend/index";
    }

    /*
     * @Description 商品列表页路由
     * @Param []
     * @Return java.lang.String
     */
    @RequestMapping(value = "/shoplist")
    private String showShopList(){
        return "/frontend/shoplist";
    }

    /*
     * @Description 店铺详情页路由
     * @Param []
     * @Return java.lang.String
     */
    @RequestMapping(value = "/shopdetail")
    private String showShopDetail(){
        return "/frontend/shopdetail";
    }

    /*
     * @Description 商品详情页路由
     * @Param []
     * @Return java.lang.String
     */
    @RequestMapping(value = "/productdetail")
    private String showProductDetail(){
        return "/frontend/productdetail";
    }
}
