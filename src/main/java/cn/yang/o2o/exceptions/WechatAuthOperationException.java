package cn.yang.o2o.exceptions;

/**
 * @Description TODO
 * @Author yanglan
 * @Date 2019/1/16 14:40
 */
public class WechatAuthOperationException extends RuntimeException {
    public WechatAuthOperationException(String message) {
        super(message);
    }
}
