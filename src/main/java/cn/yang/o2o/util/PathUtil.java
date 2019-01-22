package cn.yang.o2o.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description 路径处理
 * @Author yanglan
 * @Date 2018/11/28 17:47
 */
public class PathUtil {

    private static Logger logger = LoggerFactory.getLogger(PathUtil.class);
    private static String separator = System.getProperty("file.separator");
    /*
     * @Description 获取不同系统的图片存储基路径
     * @Param []
     * @Return java.lang.String
     */        
    public static String getImgBasePath(){
        String os=System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")) {
            basePath = "E:/image";
        } else {
            basePath="/home/yanglan/projectSrc/image";
        }
        basePath = basePath.replace("/",separator);
        return basePath;
    }

    /*
     * @Description 获取店铺图片存储路径，将图片分别存储在各自店铺下
     * @Param
     * @Return
     */
    public static String getShopImagePath(long shopId){
        String imagePath = "/upload/images/item/shop/"+shopId+"/";
        logger.debug("dest:"+imagePath);
        return imagePath.replace("/",separator);
    }

    public static String getShopCategoryImagePath(){
        String imagePath = "/upload/images/item/shopcategory/";
        return imagePath.replace("/",separator);
    }

    public static String getHeadLineImagePath(){
        String imagePath = "/upload/images/item/headtitle/";
        return imagePath.replace("/",separator);
    }

    public static String transferToLink(String path){
        return path.replace(separator,"/");
    }
}
