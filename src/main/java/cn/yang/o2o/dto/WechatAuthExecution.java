package cn.yang.o2o.dto;

import cn.yang.o2o.entity.WechatAuth;
import cn.yang.o2o.enums.WechatAuthStateEnum;

import java.util.List;

/**
 * @Description TODO
 * @Author yanglan
 * @Date 2019/1/16 14:40
 */
public class WechatAuthExecution {
    // 结果状态
    private int state;

    // 状态标识
    private String stateInfo;

    private int count;

    private WechatAuth wechatAuth;

    private List<WechatAuth> wechatAuthList;

    public WechatAuthExecution() {
    }

    // 失败的构造器
    public WechatAuthExecution(WechatAuthStateEnum wechatAuthStateEnum) {
        this.state = wechatAuthStateEnum.getState();
        this.stateInfo = wechatAuthStateEnum.getStateInfo();
    }

    // 成功的构造器
    public WechatAuthExecution(WechatAuthStateEnum wechatAuthStateEnum, WechatAuth wechatAuth) {
        this.state = wechatAuthStateEnum.getState();
        this.stateInfo = wechatAuthStateEnum.getStateInfo();
        this.wechatAuth = wechatAuth;
    }

    // 成功的构造器
    public WechatAuthExecution(WechatAuthStateEnum wechatAuthStateEnum, List<WechatAuth> wechatAuthList) {
        this.state = wechatAuthStateEnum.getState();
        this.stateInfo = wechatAuthStateEnum.getStateInfo();
        this.wechatAuthList = wechatAuthList;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public WechatAuth getWechatAuth() {
        return wechatAuth;
    }

    public void setWechatAuth(WechatAuth wechatAuth) {
        this.wechatAuth = wechatAuth;
    }

    public List<WechatAuth> getWechatAuthList() {
        return wechatAuthList;
    }

    public void setWechatAuthList(List<WechatAuth> wechatAuthList) {
        this.wechatAuthList = wechatAuthList;
    }
}
