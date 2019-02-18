package cn.yang.o2o.util.wechat;

import cn.yang.o2o.dto.UserAccessToken;
import cn.yang.o2o.dto.WechatUser;
import cn.yang.o2o.entity.PersonInfo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * @Description 微信工具类
 * @Author yanglan
 * @Date 2019/1/16 19:48
 */
public class WechatUtil {
    private static Logger log = LoggerFactory.getLogger(WechatUtil.class);

    /*
     * @Description 获取UserAccessToken实体类
     * @Param [code]
     * @Return cn.yang.o2o.dto.UserAccessToken
     */
    public static UserAccessToken getUserAccessToken(String code) throws IOException {
        // 测试号信息里的appId
        String appId = "wx96c616c6aa9afd3c";
        log.debug("appId:" + appId);
        // 测试号信息里的appsecret
        String appsecret = "41efd652d7d3fcba2d4f10b6ee605edb";
        log.debug("secret:" + appsecret);
        // 根据传入的code,拼接出访问微信定义好的接口的URL
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appId + "&secret=" + appsecret
                + "&code=" + code + "&grant_type=authorization_code";
        // 向相应URL发送请求获取token json字符串
        String tokenStr = httpsRequest(url, "GET", null);
        log.debug("userAccessToken:" + tokenStr);
        UserAccessToken token = new UserAccessToken();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // 将json字符串转换成相应对象
            token = objectMapper.readValue(tokenStr, UserAccessToken.class);
        } catch (JsonParseException e) {
            log.error("获取用户accessToken失败: " + e.getMessage());
            e.printStackTrace();
        } catch (JsonMappingException e) {
            log.error("获取用户accessToken失败: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            log.error("获取用户accessToken失败: " + e.getMessage());
            e.printStackTrace();
        }
        if (token == null) {
            log.error("获取用户accessToken失败。");
            return null;
        }
        return token;
    }

    public static WechatUser getUserInfo(String accessToken, String openId) {
        // 根据传入的accessToken以及openId拼接出访问微信定义的端口并获取用户信息的URL
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken + "&openid=" + openId
                + "&lang=zh_CN";
        // 访问该URL获取用户信息json 字符串
        String userStr = httpsRequest(url, "GET", null);
        log.debug("user info:" + userStr);
        WechatUser user = new WechatUser();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // 将json字符串转换成相应对象
            user = objectMapper.readValue(userStr, WechatUser.class);
        } catch (JsonParseException e) {
            log.error("获取用户信息失败: " + e.getMessage());
            e.printStackTrace();
        } catch (JsonMappingException e) {
            log.error("获取用户信息失败: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            log.error("获取用户信息失败: " + e.getMessage());
            e.printStackTrace();
        }
        if (user == null) {
            log.error("获取用户信息失败。");
            return null;
        }
        return user;
    }

    /*
     * @Description 将WechatUser里的信息转换成PersonInfo的信息并返回PersonInfo实体类
     * @Param [user]
     * @Return cn.yang.o2o.entity.PersonInfo
     */
    public static PersonInfo getPersonInfoFromRequest(WechatUser user) {
        PersonInfo personInfo = new PersonInfo();
        personInfo.setName(user.getNickName());
        personInfo.setGender(user.getSex() + "");
        personInfo.setProfileImg(user.getHeadimgurl());
        personInfo.setEnableStatus(1);
        return personInfo;
    }

    /*
     * @Description 发起https请求并获取结果
     * @Param [requestUrl, requestMethod, outputStr]
     *         请求地址，请求方式，提交的数据
     * @Return java.lang.String
     */
    private static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        StringBuffer buffer = new StringBuffer();
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpsUrlConn = (HttpsURLConnection) url.openConnection();
            httpsUrlConn.setSSLSocketFactory(ssf);

            httpsUrlConn.setDoOutput(true);
            httpsUrlConn.setDoInput(true);
            httpsUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpsUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod)) {
                httpsUrlConn.connect();
            }

            // 当有数据需要提交时
            if (null != outputStr) {
                OutputStream outputStream = httpsUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpsUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            // 释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            httpsUrlConn.disconnect();
            log.debug("https buffer:" + buffer.toString());

        } catch (ConnectException ce) {
            log.error("Weixin server connection timed out.");
        } catch (Exception e) {
            log.error("https request error:{}", e);
        }
        return buffer.toString();

    }
}
