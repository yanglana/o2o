package cn.yang.o2o.dto;

import cn.yang.o2o.entity.ShopCategory;
import cn.yang.o2o.enums.ShopCategoryStateEnum;

import java.util.List;

/**
 * @Description TODO
 * @Author yanglan
 * @Date 2018/12/28 11:01
 */
public class ShopCategoryExecution {
    // 结果状态
    private int state;
    // 状态标识
    private String stateInfo;

    private ShopCategory shopCategory;

    // 操作的商铺类别
    private List<ShopCategory> shopCategoryList;

    public ShopCategoryExecution(ShopCategoryStateEnum shopCategoryStateEnum) {
        this.state = shopCategoryStateEnum.getState();
        this.stateInfo = shopCategoryStateEnum.getStateInfo();
    }

    public ShopCategoryExecution(ShopCategoryStateEnum shopCategoryStateEnum, ShopCategory shopCategory) {
        this.state = shopCategoryStateEnum.getState();
        this.stateInfo = shopCategoryStateEnum.getStateInfo();
        this.shopCategory = shopCategory;
    }

    public ShopCategoryExecution(ShopCategoryStateEnum shopCategoryStateEnum, List<ShopCategory> shopCategoryList) {
        this.state = shopCategoryStateEnum.getState();
        this.stateInfo = shopCategoryStateEnum.getStateInfo();
        this.shopCategoryList = shopCategoryList;
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

    public ShopCategory getShopCategory() {
        return shopCategory;
    }

    public void setShopCategory(ShopCategory shopCategory) {
        this.shopCategory = shopCategory;
    }

    public List<ShopCategory> getShopCategoryList() {
        return shopCategoryList;
    }

    public void setShopCategoryList(List<ShopCategory> shopCategoryList) {
        this.shopCategoryList = shopCategoryList;
    }
}
