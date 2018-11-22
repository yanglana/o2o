package cn.yang.o2o.entity;

import java.util.Date;
/**
 * @Description 微信账号实体类
 * @Author yanglan
 * @Date 2018/11/22 15:26
 */
public class WechatAuth {
    //ID
    private Long wechatAuthId;
    //openID
    private String openId;
    //创建时间
    private Date createTime;
    //用户ID
    private PersonInfo personInfo;

    public Long getWechatAuthId() {
        return wechatAuthId;
    }

    public void setWechatAuthId(Long wechatAuthId) {
        this.wechatAuthId = wechatAuthId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public PersonInfo getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(PersonInfo personInfo) {
        this.personInfo = personInfo;
    }
}
