package com.yf.producer.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

public class BrdOrderProductVo implements Serializable {

    private static final long serialVersionUID = -5956457180241955961L;

    private String oid;

    private String productId;

    private String productName;

    private BigDecimal retailAmount;

    private String state;

    private Integer discount;

    private Integer count;

    private Long actualAmount;

    private String productNum;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getRetailAmount() {
        return retailAmount;
    }

    public void setRetailAmount(BigDecimal retailAmount) {
        this.retailAmount = retailAmount;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(Long actualAmount) {
        this.actualAmount = actualAmount;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }
}