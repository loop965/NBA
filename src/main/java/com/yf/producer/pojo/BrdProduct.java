package com.yf.producer.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author administrator
 * 产品
 */
public class BrdProduct implements Serializable {

    private static final long serialVersionUID = -4020392202564867900L;

    private String id;

    private String bid;

    private Integer catagory;

    private Integer oneCategory;

    private Integer customMadeFlag;

    private Integer colorId;

    private Integer cottonId;

    private Integer collarType;

    private Integer modelNum;

    private Integer shoulderSeam;

    private Integer profile;

    private Integer onLength;

    private Integer offLength;

    private Integer onMaterial;

    private Integer offMaterial;

    private Integer flowerStyle;

    private Integer flowerColor;

    private Integer flowerType;

    private String seasons;

    private String styles;

    private Integer modelA;

    private Integer modelB;

    private Integer gender;

    private String productNum;

    private BigDecimal marketPrice;

    private BigDecimal integralPrice;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date storageDate;

    private String productImage;

    private String thumbnail;

    private String sourceType;

    private String state;

    private String createUser;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String updateUser;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String uid;

    private Integer inStock;

    private String shopId;


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

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

    public Integer getCatagory() {
        return catagory;
    }

    public void setCatagory(Integer catagory) {
        this.catagory = catagory;
    }

    public Integer getOneCategory() {
        return oneCategory;
    }

    public void setOneCategory(Integer oneCategory) {
        this.oneCategory = oneCategory;
    }

    public Integer getCustomMadeFlag() {
        return customMadeFlag;
    }

    public void setCustomMadeFlag(Integer customMadeFlag) {
        this.customMadeFlag = customMadeFlag;
    }

    public Integer getColorId() {
        return colorId;
    }

    public void setColorId(Integer colorId) {
        this.colorId = colorId;
    }

    public Integer getCottonId() {
        return cottonId;
    }

    public void setCottonId(Integer cottonId) {
        this.cottonId = cottonId;
    }

    public Integer getCollarType() {
        return collarType;
    }

    public void setCollarType(Integer collarType) {
        this.collarType = collarType;
    }

    public Integer getModelNum() {
        return modelNum;
    }

    public void setModelNum(Integer modelNum) {
        this.modelNum = modelNum;
    }

    public Integer getShoulderSeam() {
        return shoulderSeam;
    }

    public void setShoulderSeam(Integer shoulderSeam) {
        this.shoulderSeam = shoulderSeam;
    }

    public Integer getProfile() {
        return profile;
    }

    public void setProfile(Integer profile) {
        this.profile = profile;
    }

    public Integer getOnLength() {
        return onLength;
    }

    public void setOnLength(Integer onLength) {
        this.onLength = onLength;
    }

    public Integer getOffLength() {
        return offLength;
    }

    public void setOffLength(Integer offLength) {
        this.offLength = offLength;
    }

    public Integer getOnMaterial() {
        return onMaterial;
    }

    public void setOnMaterial(Integer onMaterial) {
        this.onMaterial = onMaterial;
    }

    public Integer getOffMaterial() {
        return offMaterial;
    }

    public void setOffMaterial(Integer offMaterial) {
        this.offMaterial = offMaterial;
    }

    public Integer getFlowerStyle() {
        return flowerStyle;
    }

    public void setFlowerStyle(Integer flowerStyle) {
        this.flowerStyle = flowerStyle;
    }

    public Integer getFlowerColor() {
        return flowerColor;
    }

    public void setFlowerColor(Integer flowerColor) {
        this.flowerColor = flowerColor;
    }

    public Integer getFlowerType() {
        return flowerType;
    }

    public void setFlowerType(Integer flowerType) {
        this.flowerType = flowerType;
    }

    public String getSeasons() {
        return seasons;
    }

    public void setSeasons(String seasons) {
        this.seasons = seasons;
    }

    public String getStyles() {
        return styles;
    }

    public void setStyles(String styles) {
        this.styles = styles;
    }

    public Integer getModelA() {
        return modelA;
    }

    public void setModelA(Integer modelA) {
        this.modelA = modelA;
    }

    public Integer getModelB() {
        return modelB;
    }

    public void setModelB(Integer modelB) {
        this.modelB = modelB;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public BigDecimal getIntegralPrice() {
        return integralPrice;
    }

    public void setIntegralPrice(BigDecimal integralPrice) {
        this.integralPrice = integralPrice;
    }

    public Date getStorageDate() {
        return storageDate;
    }

    public void setStorageDate(Date storageDate) {
        this.storageDate = storageDate;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getInStock() {
        return inStock;
    }

    public void setInStock(Integer inStock) {
        this.inStock = inStock;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }
}