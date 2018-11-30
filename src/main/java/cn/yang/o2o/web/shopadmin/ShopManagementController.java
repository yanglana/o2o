package cn.yang.o2o.web.shopadmin;

import cn.yang.o2o.dto.ShopExecution;
import cn.yang.o2o.entity.PersonInfo;
import cn.yang.o2o.entity.Shop;
import cn.yang.o2o.enums.ShopStateEnum;
import cn.yang.o2o.service.ShopService;
import cn.yang.o2o.util.HttpServletRequestUtil;
import cn.yang.o2o.util.ImageUtil;
import cn.yang.o2o.util.PathUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description TODO
 * @Author yanglan
 * @Date 2018/11/29 17:13
 */
@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {

    @Autowired
    private ShopService shopService;

    @RequestMapping(value = "/registershop", method = RequestMethod.PUT)
    @ResponseBody
    private Map<String, Object> registerShop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();

        // 1.接收并转化相应的参数，包括店铺信息以及图片信息
        String shopStr = HttpServletRequestUtil.getString(request,"shopStr");
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try {
            shop = mapper.readValue(shopStr,Shop.class);
        } catch (IOException e) {
            modelMap.put("success", false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }
        //文件上传类
        CommonsMultipartFile shopImg =null;
        //文件上传处理类
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断是否有上传文件流
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        } else {
            modelMap.put("success",false);
            modelMap.put("errMsg","上传图片不能为空");
            return modelMap;
        }

        //注册店铺
        if (shop != null && shopImg != null) {
            //PersonInfo owner = (PersonInfo) request.getSession().getAttribute("user");
            PersonInfo owner = new PersonInfo();
            owner.setUserId(1L);
            shop.setOwner(owner);
            File shopImgFile = new File(PathUtil.getImgBasePath()+ ImageUtil.getRandomFileName());
            try {
                shopImgFile.createNewFile();
                inputStreamToFile(shopImg.getInputStream(),shopImgFile);
            } catch (IOException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg",e.getMessage());
                return modelMap;
            }
            ShopExecution se = shopService.addShop(shop,shopImgFile);
            if (se.getState() == ShopStateEnum.CHECK.getState()) {
                modelMap.put("success", true);
            } else {
                modelMap.put("success",false);
                modelMap.put("errMsg",se.getStateInfo());
            }
            return modelMap;
        } else {
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入店铺信息");
            return modelMap;
        }
    }

    //将shopImg转化成File类型
    private static void inputStreamToFile(InputStream ins, File file) {
        FileOutputStream os=null;
        try {
            os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = ins.read(buffer)) != -1) {
                os.write(buffer,0,bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (ins != null) {
                    ins.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
