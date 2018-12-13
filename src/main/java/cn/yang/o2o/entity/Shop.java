package cn.yang.o2o.entity;

import java.util.Date;

/**
 * @Description 店铺实体类
 * @Author yanglan
 * @Date 2018/11/22 15:20
 */
public class Shop {
    //主键ID
    private Long shopId;
    //名称
    private String shopName;
    //简介
    private String shopDesc;
    //地址
    private String shopAddr;
    //号码
    private String phone;
    //门面图片地址
    private String shopImg;
    //权重, 越大越排前显示
    private Integer priority;
    //创建时间
    private Date createTime;
    //更新时间
    private Date lastEditTime;
    //状态(-1.不可用 0.审核中 1.可用)
    private Integer enableStatus;
    //超级管理员给店家的提醒,包括为什么审核不通过等..
    private String advice;
    //店铺所属区域
    private Area area;
    //店铺属于哪个店家的
    private PersonInfo owner;
    //店铺类别
    private ShopCategory shopCategory;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopDesc() {
        return shopDesc;
    }

    public void setShopDesc(String shopDesc) {
        this.shopDesc = shopDesc;
    }

    public String getShopAddr() {
        return shopAddr;
    }

    public void setShopAddr(String shopAddr) {
        this.shopAddr = shopAddr;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getShopImg() {
        return shopImg;
    }

    public void setShopImg(String shopImg) {
        this.shopImg = shopImg;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public Integer getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public PersonInfo getOwner() {
        return owner;
    }

    public void setOwner(PersonInfo owner) {
        this.owner = owner;
    }

    public ShopCategory getShopCategory() {
        return shopCategory;
    }

    public void setShopCategory(ShopCategory shopCategory) {
        this.shopCategory = shopCategory;
    }
}
