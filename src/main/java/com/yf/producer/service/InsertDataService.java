package com.yf.producer.service;

import com.yf.producer.dao.InsertDataMapper;
import com.yf.producer.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author: yf
 * @create: 2019/11/21  10:49
 */
@Service
public class InsertDataService {

    @Autowired
    private InsertDataMapper dataMapper;

    public List<BrdCustomerInfo> selectBrdCustomerInfoList(){
        return dataMapper.selectBrdCustomerInfoList();
    }

    public void insertBrdCustomerInfo(BrdCustomerInfo brdCustomerInfo){
        dataMapper.insertBrdCustomerInfo(brdCustomerInfo);
    }

    public void insertBrdMemberInfo(BrdMemberInfo brdMemberInfo){
        dataMapper.insertBrdMemberInfo(brdMemberInfo);
    }

    public void insertBrdProduct(BrdProduct brdProduct) {
        dataMapper.insertBrdProduct(brdProduct);
    }

    public void insertMemberRecharge(MemberRecharge e) {
        dataMapper.insertMemberRecharge(e);
    }

    public void insertBrdCoupon(BrdCoupon e) {
        dataMapper.insertBrdCoupon(e);
    }

    public void insertBrdMemberCard(BrdMemberCard e) {
        dataMapper.insertBrdMemberCard(e);
    }

    public void insertBrdOrder(BrdOrder e) {
        dataMapper.insertBrdOrder(e);
    }

    public void insertBrdConsumptionDetails(BrdConsumptionDetails e) {
        dataMapper.insertBrdConsumptionDetails();
    }

    public void insertOrderProduct(BrdOrderProductVo orderProductVo) {
        dataMapper.insertOrderProduct(orderProductVo);
    }

    public BrdMemberInfo selectBrdMemberInfo(String sourceMId, String tigerBid) {
        return dataMapper.selectBrdMemberInfo(sourceMId,tigerBid);
    }

    public Optional<BrdMemberInfo> selectBrdMemberInfo1(String sourceMId, String tigerBid) {
        return Optional.ofNullable(dataMapper.selectBrdMemberInfo(sourceMId,tigerBid));
    }

    public BrdCustomerInfo selectBrdCustomerInfo(String cid) {
        return dataMapper.selectBrdCustomerInfo(cid);
    }

    public void updateBrdMemberInfo(BrdMemberInfo sourceBrdMemberInfo) {
        dataMapper.updateBrdMemberInfo(sourceBrdMemberInfo);
    }

    public void updateBrdCustomerInfo(BrdCustomerInfo sourceBrdCustomerInfo) {
        dataMapper.updateBrdCustomerInfo(sourceBrdCustomerInfo);
    }

    public BrdProduct selectBrdProduct(String uid, String tigerBid) {
        return dataMapper.selectBrdProduct(uid,tigerBid);
    }

    public void updateBrdProduct(BrdProduct newBrdProduct) {
        dataMapper.updateBrdProduct(newBrdProduct);
    }

    public BrdOrder selectBrdOrder(String uid, String tigerBid) {
        return dataMapper.selectBrdOrder(uid,tigerBid);
    }

    public BrdCoupon selectBrdCoupon(String couponId, String tigerBid) {
        return dataMapper.selectBrdCoupon(couponId,tigerBid);
    }

    public MemberRecharge selectMemberRecharge(String uid, String tigerBid) {
        return dataMapper.selectMemberRecharge(uid,tigerBid);
    }

    public BrdMemberCard selectBrdMemberCard(String uid, String tigerBid) {
        return dataMapper.selectBrdMemberCard(uid,tigerBid);
    }

    public void insertBrdProductStock(BrdProductStock brdProductStock) {
        dataMapper.insertBrdProductStock(brdProductStock);
    }

    public void updateBrdProductStock(BrdProductStock updateBrdProductStock) {
        dataMapper.updateBrdProductStock(updateBrdProductStock);
    }

    public void insertBrdShop(BrdShop newBrdShop) {
        dataMapper.insertBrdShop(newBrdShop);
    }

    public BrdShop selectBrdShop( String resourceId, String tigerBid) {
        return dataMapper.selectBrdShop(resourceId,tigerBid);
    }

    public void updateBrdShop(BrdShop newBrdShop) {
        dataMapper.updateBrdShop(newBrdShop);
    }

    public void insertFound(Found found){
        dataMapper.insertFound(found);
    }

    public String testIot(){
        return dataMapper.testIot();
    }

}
