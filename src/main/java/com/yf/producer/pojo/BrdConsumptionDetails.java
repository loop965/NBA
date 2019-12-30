package com.yf.producer.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class BrdConsumptionDetails {
    private String id;

    private String bid;

    private String cid;

    private String orderId;

    private Date buyTime;

    private BigDecimal retailAmount;

    private Long actualSalesAmount;

    private Long actualAmount;

    private BigDecimal discount;

    private Long discountAmount;

    private String cids;

    private Integer usingIntegra;

    private Long integralDeduction;

    private Long prepaidAmount;

    private Long cash;

    private String state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    public BigDecimal getRetailAmount() {
        return retailAmount;
    }

    public void setRetailAmount(BigDecimal retailAmount) {
        this.retailAmount = retailAmount;
    }

    public Long getActualSalesAmount() {
        return actualSalesAmount;
    }

    public void setActualSalesAmount(Long actualSalesAmount) {
        this.actualSalesAmount = actualSalesAmount;
    }

    public Long getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(Long actualAmount) {
        this.actualAmount = actualAmount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Long getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Long discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getCids() {
        return cids;
    }

    public void setCids(String cids) {
        this.cids = cids;
    }

    public Integer getUsingIntegra() {
        return usingIntegra;
    }

    public void setUsingIntegra(Integer usingIntegra) {
        this.usingIntegra = usingIntegra;
    }

    public Long getIntegralDeduction() {
        return integralDeduction;
    }

    public void setIntegralDeduction(Long integralDeduction) {
        this.integralDeduction = integralDeduction;
    }

    public Long getPrepaidAmount() {
        return prepaidAmount;
    }

    public void setPrepaidAmount(Long prepaidAmount) {
        this.prepaidAmount = prepaidAmount;
    }

    public Long getCash() {
        return cash;
    }

    public void setCash(Long cash) {
        this.cash = cash;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}