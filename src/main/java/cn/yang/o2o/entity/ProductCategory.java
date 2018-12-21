package cn.yang.o2o.entity;

import java.util.Date;

/**
 * @Description 商品类别实体类
 * @Author yanglan
 * @Date 2018/11/22 16:48
 */
public class ProductCategory {
    //ID
    private Long productCategoryId;
    //类别名
    private String productCategoryName;
    //该类别是属于哪个店铺
    private Long shopId;
    //权重
    private Integer priority;
    //创建时间
    private Date createTime;

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
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
}
