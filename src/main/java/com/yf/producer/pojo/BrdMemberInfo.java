package com.yf.producer.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class BrdMemberInfo implements Serializable {

    private static final long serialVersionUID = -7555774833911344368L;

    private String id;

    private String cid;

    private String bid;

    private String sid;

    private Integer fashion;

    private Integer provinceid;

    private Integer cityid;

    private Integer countryid;

    private String contactAddress;

    private String workArea;

    private String jobType;

    private Integer conference;

    private Integer onBusiness;

    private String contact;

    private String contactTime;

    private String state;

    private Integer sourceType;

    private Integer sourceId;

    private Integer type;

    private String createUser;

    private String officialAccount;

    private String remark;


    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String updateUser;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String uid;

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

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public Integer getFashion() {
        return fashion;
    }

    public void setFashion(Integer fashion) {
        this.fashion = fashion;
    }

    public Integer getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(Integer provinceid) {
        this.provinceid = provinceid;
    }

    public Integer getCityid() {
        return cityid;
    }

    public void setCityid(Integer cityid) {
        this.cityid = cityid;
    }

    public Integer getCountryid() {
        return countryid;
    }

    public void setCountryid(Integer countryid) {
        this.countryid = countryid;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getWorkArea() {
        return workArea;
    }

    public void setWorkArea(String workArea) {
        this.workArea = workArea;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public Integer getConference() {
        return conference;
    }

    public void setConference(Integer conference) {
        this.conference = conference;
    }

    public Integer getOnBusiness() {
        return onBusiness;
    }

    public void setOnBusiness(Integer onBusiness) {
        this.onBusiness = onBusiness;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactTime() {
        return contactTime;
    }

    public void setContactTime(String contactTime) {
        this.contactTime = contactTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "BrdMemberInfo{" +
                "id='" + id + '\'' +
                ", cid='" + cid + '\'' +
                ", bid='" + bid + '\'' +
                ", sid='" + sid + '\'' +
                ", fashion=" + fashion +
                ", provinceid=" + provinceid +
                ", cityid=" + cityid +
                ", countryid=" + countryid +
                ", contactAddress='" + contactAddress + '\'' +
                ", workArea='" + workArea + '\'' +
                ", jobType='" + jobType + '\'' +
                ", conference=" + conference +
                ", onBusiness=" + onBusiness +
                ", contact='" + contact + '\'' +
                ", contactTime='" + contactTime + '\'' +
                ", state='" + state + '\'' +
                ", sourceType=" + sourceType +
                ", sourceId=" + sourceId +
                ", type=" + type +
                ", createUser='" + createUser + '\'' +
                ", officialAccount='" + officialAccount + '\'' +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                ", updateUser='" + updateUser + '\'' +
                ", updateTime=" + updateTime +
                ", uid='" + uid + '\'' +
                '}';
    }
}