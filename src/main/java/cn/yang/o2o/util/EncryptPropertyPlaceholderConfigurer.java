package cn.yang.o2o.util;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * @Description TODO
 * @Author yanglan
 * @Date 2019/1/22 19:27
 */
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
    // 需要加密的字段数组
    private String[] encryptPropNames = {"jdbc.name", "jdbc.pwd"};

    /*
     * @Description 对关键的属性进行转换
     * @Param [propertyName, propertyValue]
     * @Return java.lang.String
     */
    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
        if (isEncryptProp(propertyName)) {
            // 对已加密的字段进行解密工作
            String decryptValue = DESUtil.getDecryptString(propertyValue);
            return decryptValue;
        } else {
            return propertyValue;
        }
    }

    /*
     * @Description 该属性是否已加密
     * @Param [propertyName]
     * @Return boolean
     */
    private boolean isEncryptProp(String propertyName) {
        // 若等于需要加密的field，则进行加密
        for (String encryptpropertyName : encryptPropNames) {
            if (encryptpropertyName.equals(propertyName)) {
                return true;
            }
        }
        return false;
    }
}
