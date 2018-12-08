package cn.yang.o2o.util;

import cn.yang.o2o.dto.ShopExecution;
import cn.yang.o2o.entity.Area;
import cn.yang.o2o.entity.PersonInfo;
import cn.yang.o2o.entity.Shop;
import cn.yang.o2o.entity.ShopCategory;
import cn.yang.o2o.enums.ShopStateEnum;
import cn.yang.o2o.service.ShopService;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @Description Thumbnailator处理图片工具类
 * @Author yanglan
 * @Date 2018/11/28 15:11
 */
public class ImageUtil {
    //获取图片的classpath绝对值路径(该方法是通过线程执行的)
    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final SimpleDateFormat sDateFormate = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random r = new Random();
    private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    
    /*
     * @Description 将CommonsMultipartFile转换成File类
     * @Param [cFile]
     * @Return java.io.File
     */
    public static File transferCommonsMultipartFile(CommonsMultipartFile cFile){
        File newFile =new File(cFile.getOriginalFilename());
        try {
            cFile.transferTo(newFile);
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        return newFile;
    }

    /*
     * @Description 处理缩略图(用户传递过来的),并返回新生成图片的相对值路径
     * @Param [thumbnail, targetAddr:图片存储路径]
     * @Return java.lang.String
     */
    public static String generateThumbnail(InputStream thumbnailInputStream, String fileName, String targetAddr) {
        //获取不重复的随机名
        String realFileName = getRandomFileName();
        //获取扩展名  随机名+扩展名=新名字
        String extension = getFileExtension(fileName);
        //如果目标路径不存在，则自动创建
        makeDirPath(targetAddr);
        //获取文件存储的相对路径(带文件名)
        String relativeAddr = targetAddr + realFileName + extension;
        logger.debug("current relativeAddr is:" + relativeAddr);
        //获取文件要保存到的目标路径
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        logger.debug("current complete addr is:" + PathUtil.getImgBasePath() + relativeAddr);
        logger.debug("basePath is:" + basePath);
        // 调用Thumbnails生成带有水印的图片
        try {
            Thumbnails.of(thumbnailInputStream).size(200, 200)
                    .watermark(Positions.BOTTOM_RIGHT,
                            ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)
                    .outputQuality(0.8f).toFile(dest);
        } catch (IOException e) {
            logger.error(e.toString());
            throw new RuntimeException("创建缩略图失败：" + e.toString());
        }
        // 返回图片相对路径地址
        return relativeAddr;
    }

    /*
     * @Description 创建目标路径所涉及到的目录，即E:/projectdev/image/xxx.jpg, 那么 projectdev image
     * 这2个文件夹都得自动创建
     * @Param [targetAddr]
     * @Return void
     */
    private static void makeDirPath(String targetAddr) {
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
        File dirPath = new File(realFileParentPath);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
    }

    /*
     * @Description 获取输入文件流的扩展名
     * @Param [thumbnail]
     * @Return java.lang.String
     */
    private static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /*
     * @Description 生成随机文件名，当前年月日小时分钟秒钟+五位随机数
     * @Param []
     * @Return java.lang.String
     */
    public static String getRandomFileName() {
        // 获取随机的五位数
        int rannum = r.nextInt(89999) + 10000;
        String nowTimeStr = sDateFormate.format(new Date());
        return nowTimeStr + rannum;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(basePath);

        Thumbnails.of(new File("E:/image/xiaohuangren.jpg"))
                .size(200, 200).watermark(Positions.BOTTOM_RIGHT,
                ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)
                .outputQuality(0.8f).toFile("E:/image/xiaohuangrennew.jpg");

    }
}
