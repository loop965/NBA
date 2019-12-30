package com.yf.producer.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BrdOrder implements Serializable {

    private static final long serialVersionUID = 1244883669894039665L;

    private String id;

    private String bid;

    private String uid;

    private String cid;

    private String state;

    private Date buyTime;

    private Integer discount;

    private Integer count;

    private BigDecimal actualAmount;

    private BigDecimal retailAmount;

    private BigDecimal discountAmount;

    private String cids;

    private Integer usingIntegra;

    private BigDecimal integralDeduction;

    private BigDecimal prepaidAmount;

    private BigDecimal cash;

    private List<BrdOrderProductVo> brdOrderProductVoList = new ArrayList<>();

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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
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

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public BigDecimal getRetailAmount() {
        return retailAmount;
    }

    public void setRetailAmount(BigDecimal retailAmount) {
        this.retailAmount = retailAmount;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
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

    public BigDecimal getIntegralDeduction() {
        return integralDeduction;
    }

    public void setIntegralDeduction(BigDecimal integralDeduction) {
        this.integralDeduction = integralDeduction;
    }

    public BigDecimal getPrepaidAmount() {
        return prepaidAmount;
    }

    public void setPrepaidAmount(BigDecimal prepaidAmount) {
        this.prepaidAmount = prepaidAmount;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public List<BrdOrderProductVo> getBrdOrderProductVoList() {
        return brdOrderProductVoList;
    }

    public void setBrdOrderProductVoList(List<BrdOrderProductVo> brdOrderProductVoList) {
        this.brdOrderProductVoList = brdOrderProductVoList;
    }

    @Override
    public String toString() {
        return "BrdOrder{" +
                "id='" + id + '\'' +
                ", bid='" + bid + '\'' +
                ", uid='" + uid + '\'' +
                ", cid='" + cid + '\'' +
                ", state='" + state + '\'' +
                ", buyTime=" + buyTime +
                ", discount=" + discount +
                ", count=" + count +
                ", actualAmount=" + actualAmount +
                ", retailAmount=" + retailAmount +
                ", discountAmount=" + discountAmount +
                ", cids='" + cids + '\'' +
                ", usingIntegra=" + usingIntegra +
                ", integralDeduction=" + integralDeduction +
                ", prepaidAmount=" + prepaidAmount +
                ", cash=" + cash +
                '}';
    }
}