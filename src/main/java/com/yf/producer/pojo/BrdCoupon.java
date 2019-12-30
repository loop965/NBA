package com.yf.producer.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: yf
 * @create: 2019/11/22  10:52
 */
public class BrdCoupon implements Serializable {

    private static final long serialVersionUID = 1228134033219031624L;

    private String id;

    private String cid;

    private String bid;

    private BigDecimal coupon;

    private Date endDate;

    private String state;

    private Date createTime;

    private String type;

    private String couponId;

    private String remark;

    private String repaidCoupons;

    private String rechargeId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public BigDecimal getCoupon() {
        return coupon;
    }

    public void setCoupon(BigDecimal coupon) {
        this.coupon = coupon;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRepaidCoupons() {
        return repaidCoupons;
    }

    public void setRepaidCoupons(String repaidCoupons) {
        this.repaidCoupons = repaidCoupons;
    }

    public String getRechargeId() {
        return rechargeId;
    }

    public void setRechargeId(String rechargeId) {
        this.rechargeId = rechargeId;
    }


    @Override
    public String toString() {
        return "BrdCoupon{" +
                "id='" + id + '\'' +
                ", cid='" + cid + '\'' +
                ", bid='" + bid + '\'' +
                ", coupon=" + coupon +
                ", endDate=" + endDate +
                ", state='" + state + '\'' +
                ", createTime=" + createTime +
                ", type='" + type + '\'' +
                ", couponId='" + couponId + '\'' +
                ", remark='" + remark + '\'' +
                ", repaidCoupons='" + repaidCoupons + '\'' +
                ", rechargeId='" + rechargeId + '\'' +
                '}';
    }
}
