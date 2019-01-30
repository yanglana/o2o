package cn.yang.o2o.web.local;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description TODO
 * @Author yanglan
 * @Date 2019/1/29 15:35
 */
@Controller
@RequestMapping("/local")
public class LocalController {
    
    /*
     * @Description 绑定帐号页路由
     * @Param []
     * @Return java.lang.String
     */
    @RequestMapping(value = "/accountbind",method = {RequestMethod.GET})
    private String accountbind(){
        return "local/accountbind";
    }

    /*
     * @Description 修改密码页路由
     * @Param []
     * @Return java.lang.String
     */
    @RequestMapping(value = "/changepsw",method = {RequestMethod.GET})
    private String changepsw(){
        return "local/changepsw";
    }

    /*
     * @Description 登录页路由
     * @Param []
     * @Return java.lang.String
     */
    @RequestMapping(value = "/login",method = {RequestMethod.GET})
    private String login(){
        return "local/login";
    }
}
