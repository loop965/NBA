package com.yf.producer.web;

import com.google.gson.reflect.TypeToken;
import com.yf.producer.pojo.*;
import com.yf.producer.service.InsertDataService;
import com.yf.producer.thread.InsertBrdCustomerInfoThread;
import com.yf.producer.util.GsonUtils;
import com.yf.producer.util.UUIDGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: yf
 * @create: 2019/10/17  10:56
 */
@RestController
@Slf4j
public class ProducerController {

    @Autowired
    private InsertDataService selectDataService;

    /**
     * 同步用户和会员数据
     * @param data
     * @throws IOException
     */
    @RequestMapping(value = "/insertCustomerAndMember")
    public void test(String data) throws IOException {
        log.info("===== begin insert or update table brd_customer_info and brd_member_info =====");
        long s1 = System.currentTimeMillis();
        List<BrdCustomerInfo> list = GsonUtils.fromJson(data, new TypeToken<List<BrdCustomerInfo>>(){}.getType());
        AtomicInteger cInsertNum = new AtomicInteger();
        AtomicInteger cUpdateNum = new AtomicInteger();
        AtomicInteger mInsertNum = new AtomicInteger();
        AtomicInteger mUpdateNum = new AtomicInteger();

        if (list == null){
            return;
        }
        list.parallelStream().forEach(newBrdCustomerInfo ->{
            BrdMemberInfo newBrdMemberInfo = newBrdCustomerInfo.getBrdMemberInfo();
            // 获取原系统的会员id
            String sourceMId = newBrdMemberInfo.getUid();
            // 判断是否导入过该会员记录
            BrdMemberInfo brdMemberInfo = selectDataService.selectBrdMemberInfo(sourceMId,Constant.TIGER_BID);
            if (brdMemberInfo == null){
                // 插入一条新纪录
                String cid = UUIDGenerator.getUUID();
                String mid = UUIDGenerator.getUUID();
                newBrdCustomerInfo.setId(cid);
                if (newBrdCustomerInfo.getCustomerSex() == null){
                    newBrdCustomerInfo.setCustomerSex(1);
                }
                selectDataService.insertBrdCustomerInfo(newBrdCustomerInfo);
                cInsertNum.getAndIncrement();

                newBrdMemberInfo.setBid(Constant.TIGER_BID);
                newBrdMemberInfo.setId(mid);
                newBrdMemberInfo.setCid(cid);
                selectDataService.insertBrdMemberInfo(newBrdMemberInfo);
                mInsertNum.getAndIncrement();
            }else {
                // 存在的话就根据更新时间判断是否需要更新
                Date mUpdateTime = brdMemberInfo.getUpdateTime();
                Date mNewUpdateTime = newBrdMemberInfo.getUpdateTime();
                if (mUpdateTime == null || mNewUpdateTime == null || mNewUpdateTime.compareTo(mUpdateTime) > 0) {
                    newBrdMemberInfo.setId(brdMemberInfo.getId());
                    selectDataService.updateBrdMemberInfo(newBrdMemberInfo);
                    mUpdateNum.getAndIncrement();
                }
                // 获取用户信息
                BrdCustomerInfo brdCustomerInfo = selectDataService.selectBrdCustomerInfo(brdMemberInfo.getCid());
                Date cUpdateTime = brdCustomerInfo.getUpdateTime();
                Date cNewUpdateTime = newBrdCustomerInfo.getUpdateTime();
                if (cUpdateTime == null || cNewUpdateTime == null || cNewUpdateTime.compareTo(cUpdateTime) > 0) {
                    newBrdCustomerInfo.setId(brdCustomerInfo.getId());
                    selectDataService.updateBrdCustomerInfo(newBrdCustomerInfo);
                    cUpdateNum.getAndIncrement();
                }
            }
        });

        long s2 = System.currentTimeMillis();
        log.info("===== table brd_customer_info insert {}条数据；update {}条数据；table brd_member_info insert {}条数据；update {}条数据；=====",
                cInsertNum,cUpdateNum,mInsertNum,mUpdateNum);
        log.info("总耗时: {}s",(s2-s1)/1000);
    }

    @RequestMapping(value = "insertMemberList",produces="application/json;charset=UTF-8")
    public void insertMemberList(@RequestBody String requestBody,String name,String address, HttpServletRequest request) throws IOException {
//        List<BrdMemberInfo> list =GsonUtils.fromJson(data, new TypeToken<List<BrdMemberInfo>>(){}.getType());
//        list.forEach(e ->{
//            System.out.println(e);
//            e.setBid("84ebd5740b204f3f9618ecbddc89548e");
//            e.setId(UUIDGenerator.getUUID());
//            selectDataService.insertBrdMemberInfo(e);
//        });
        Map<String,Object>  params = GsonUtils.fromJson(requestBody,new TypeToken<Map<String,Object>>(){}.getType());
        String line = IOUtils.toString(request.getInputStream());

        InputStream is = request.getInputStream();

    }

    /**
     * 同步产品表数据
     * @param data
     */
    @RequestMapping("insertProductList")
    public void insertProductList(String data){
        log.info("===== begin insert or update product =====");
        long s1 = System.currentTimeMillis();
        AtomicInteger insetNum = new AtomicInteger();
        AtomicInteger updateNum = new AtomicInteger();
        List<BrdProduct> list =GsonUtils.fromJson(data, new TypeToken<List<BrdProduct>>(){}.getType());
        if (list == null){
            return;
        }
        list.parallelStream().forEach(newBrdProduct ->{
            String uid = newBrdProduct.getUid();
            String resourceId = newBrdProduct.getShopId();
            BrdProduct brdProduct = selectDataService.selectBrdProduct(uid,Constant.TIGER_BID);

            if (brdProduct == null){
                BrdShop brdShop = selectDataService.selectBrdShop(resourceId,Constant.TIGER_BID);
                // 新增产品
                String productId = (UUIDGenerator.getUUID());
                insetNum.getAndIncrement();
                newBrdProduct.setBid(Constant.TIGER_BID);
                newBrdProduct.setId(productId);
                selectDataService.insertBrdProduct(newBrdProduct);
                // 保存产品库存
                // TODO: 2019/12/6/006 shopId
                BrdProductStock brdProductStock = new BrdProductStock();
//                BeanUtils.copyProperties(newBrdProduct,brdProductStock);
                brdProductStock.setId(UUIDGenerator.getUUID());
                brdProductStock.setProductId(productId);
                brdProductStock.setShopId(brdShop.getId());
                brdProductStock.setBrandId(Constant.TIGER_BID);
                brdProductStock.setStock(newBrdProduct.getInStock());
                brdProductStock.setSourceSid(resourceId);
                brdProductStock.setCreateTime(newBrdProduct.getCreateTime());
                brdProductStock.setCreateUser(newBrdProduct.getCreateUser());
                brdProductStock.setUpdateTime(newBrdProduct.getUpdateTime());
                brdProductStock.setCreateUser(newBrdProduct.getUpdateUser());
                brdProductStock.setState(newBrdProduct.getState());
                selectDataService.insertBrdProductStock(brdProductStock);

            }else{
                Date updateTime = brdProduct.getUpdateTime();
                Date newUpdateTime = newBrdProduct.getUpdateTime();
                if (updateTime == null || newUpdateTime == null || newUpdateTime.compareTo(updateTime) > 0){
                    // 更新产品
                    updateNum.getAndIncrement();
                    newBrdProduct.setId(brdProduct.getId());
                    selectDataService.updateBrdProduct(newBrdProduct);
                    // 更新产品库存
                    BrdProductStock updateBrdProductStock = new BrdProductStock();
                    updateBrdProductStock.setProductId(brdProduct.getId());
                    updateBrdProductStock.setSourceSid(newBrdProduct.getShopId());
                    updateBrdProductStock.setStock(newBrdProduct.getInStock());
                    updateBrdProductStock.setUpdateTime(newBrdProduct.getUpdateTime());
                    updateBrdProductStock.setUpdateUser(newBrdProduct.getUpdateUser());
                    updateBrdProductStock.setState(newBrdProduct.getState());
                    selectDataService.updateBrdProductStock(updateBrdProductStock);
                }
            }
        });
        long s2 = System.currentTimeMillis();
        log.info("===== table brd_product insert {}条，update {}条，耗时：{}s =====",insetNum,updateNum,(s2 - s1)/1000);
    }

    /**
     *导入充值记录表
     * @param data
     */
    @RequestMapping("insertRechargeList")
    public void insertRechargeList(String data){
        log.info("===== begin insert table brd_recharge_details =====");
        long s1 = System.currentTimeMillis();
        AtomicInteger insertNum = new AtomicInteger();
        List<MemberRecharge> list =GsonUtils.fromJson(data, new TypeToken<List<MemberRecharge>>(){}.getType());
        if (list == null){
            return;
        }
        list.parallelStream().forEach(newMemberRecharge ->{
            // 原系统的会员id
            String memberId = newMemberRecharge.getMid();
            MemberRecharge memberRecharge = selectDataService.selectMemberRecharge(newMemberRecharge.getUid(),Constant.TIGER_BID);
            if(memberRecharge != null){
                return;
            }
            BrdMemberInfo brdMemberInfo = selectDataService.selectBrdMemberInfo(memberId,Constant.TIGER_BID);
            if (brdMemberInfo == null){
                return;
            }
            newMemberRecharge.setMid(brdMemberInfo.getId());
            newMemberRecharge.setBid(Constant.TIGER_BID);
            newMemberRecharge.setId(UUIDGenerator.getUUID());
            selectDataService.insertMemberRecharge(newMemberRecharge);
            insertNum.getAndIncrement();
        });
        long s2 = System.currentTimeMillis();
        log.info("===== insert table brd_recharge_details {}条数据，耗时：{}s =====",insertNum,(s2 -s1)/1000);
    }

    /**
     * 同步优惠券数据
     * @param data 数据
     */
    @RequestMapping("insertBrdCouponList")
    public void insertBrdCouponList(String data){
        log.info("===== begin insert table brd_coupon =====");
        long s1 = System.currentTimeMillis();
        AtomicInteger insertNum = new AtomicInteger();
        List<BrdCoupon> list =GsonUtils.fromJson(data, new TypeToken<List<BrdCoupon>>(){}.getType());
        if (list == null){
            return;
        }
        list.parallelStream().forEach(newBrdCoupon ->{
            BrdCoupon coupon = selectDataService.selectBrdCoupon(newBrdCoupon.getId(),Constant.TIGER_BID);
            if (coupon != null){
                return;
            }
            // 获取原系统的会员id
            String memberId = newBrdCoupon.getCid();
            BrdMemberInfo brdMemberInfo = selectDataService.selectBrdMemberInfo(memberId,Constant.TIGER_BID);
            if (brdMemberInfo == null){
                return;
            }
            newBrdCoupon.setCid(brdMemberInfo.getCid());
            newBrdCoupon.setId(UUIDGenerator.getUUID());
            newBrdCoupon.setBid(Constant.TIGER_BID);
            selectDataService.insertBrdCoupon(newBrdCoupon);
            insertNum.getAndIncrement();
        });
        long s2 = System.currentTimeMillis();
        log.info("===== insert table brd_coupon {}条数据，耗时：{}s =====",insertNum,(s2 - s1)/1000);
    }


    /**
     *  同步会员卡数据
     * @param data
     */
    @RequestMapping("insertBrdMemberCardList")
    public void insertBrdMemberCardList(String data){
        log.info("===== begin insert table brd_member_card =====");
        long s1 = System.currentTimeMillis();
        List<BrdMemberCard> list = GsonUtils.fromJson(data, new TypeToken<List<BrdMemberCard>>(){}.getType());
        if (list == null){
            return;
        }
        AtomicInteger insertNum = new AtomicInteger();
        list.parallelStream().forEach(newBrdMemberCard ->{
            String uid = newBrdMemberCard.getUid();
            BrdMemberCard brdMemberCard = selectDataService.selectBrdMemberCard(uid,Constant.TIGER_BID);
            if (brdMemberCard != null){
                return;
            }
            String memberId = newBrdMemberCard.getMid();
            Optional<BrdMemberInfo> optional = selectDataService.selectBrdMemberInfo1(memberId,Constant.TIGER_BID);
            boolean flag = optional.isPresent();
            optional.ifPresent(e -> {
                newBrdMemberCard.setCid(e.getCid());
                newBrdMemberCard.setMid(e.getId());
                newBrdMemberCard.setId(UUIDGenerator.getUUID());
                newBrdMemberCard.setBid(Constant.TIGER_BID);
                selectDataService.insertBrdMemberCard(newBrdMemberCard);
                insertNum.getAndIncrement();
            });
        });
        long s2 = System.currentTimeMillis();
        log.info("===== insert table brd_member_card {}条数据；耗时: {}s =====",insertNum,(s2 - s1)/1000);
    }

    /**
     * 同步订单数据
     * @param data
     */
    @RequestMapping("insertBrdOrderList")
    public void insertBrdOrderList(String data){
        log.info("===== begin insert table brd_order and brd_order_product =====");
        long s1 = System.currentTimeMillis();
        AtomicInteger orderNum = new AtomicInteger();
        AtomicInteger orderProductNum = new AtomicInteger();
        List<BrdOrder> list =GsonUtils.fromJson(data, new TypeToken<List<BrdOrder>>(){}.getType());
        if (list == null){
            return;
        }
        list.parallelStream().forEach(newBrdOrder ->{
            // 原系统order id
            String id = newBrdOrder.getId();
            BrdOrder brdOrder = selectDataService.selectBrdOrder(id,Constant.TIGER_BID);
            if (brdOrder != null){
                return;
            }
            // 原系统会员id
            String memberId = newBrdOrder.getUid();
            BrdMemberInfo brdMemberInfo = selectDataService.selectBrdMemberInfo(memberId,Constant.TIGER_BID);
            newBrdOrder.setCid(brdMemberInfo.getCid());
            String orderId = UUIDGenerator.getUUID();
            newBrdOrder.setId(orderId);
            newBrdOrder.setBid(Constant.TIGER_BID);
            newBrdOrder.setUid(id);
            List<BrdOrderProductVo> brdOrderProductVoList = newBrdOrder.getBrdOrderProductVoList();
            brdOrderProductVoList.forEach(orderProductVo ->{
                // 获取谟豆系统的产品
                BrdProduct product = selectDataService.selectBrdProduct(orderProductVo.getProductId(),Constant.TIGER_BID);
                if (product == null){
                    return;
                }
                orderProductVo.setProductId(product.getId());
                orderProductVo.setOid(orderId);
                selectDataService.insertOrderProduct(orderProductVo);
                orderProductNum.getAndIncrement();
            });
            selectDataService.insertBrdOrder(newBrdOrder);
            orderNum.getAndIncrement();
        });
        long s2 = System.currentTimeMillis();
        log.info("===== table brd_order insert {}条数据，table brd_order_product insert {}条数据 take time : {}s ===== ",
                orderNum,orderProductNum,(s2 - s1)/1000);

    }

    /**
     * 同步商店信息数据
     * @param data
     */
    @RequestMapping(value = "insertBrdShopList")
    public void insertBrdShop(String data){
        log.info("===== begin insert table brd_shop =====");
        long s1 = System.currentTimeMillis();
        AtomicInteger shopInsertNum = new AtomicInteger();
        AtomicInteger shopUpdateNum = new AtomicInteger();
        List<BrdShop> list =GsonUtils.fromJson(data, new TypeToken<List<BrdShop>>(){}.getType());
        if (list == null){
            return;
        }
        list.parallelStream().forEach(newBrdShop ->{
            BrdShop brdShop = selectDataService.selectBrdShop(newBrdShop.getResourceId(),Constant.TIGER_BID);
            if (brdShop == null ){
                String id = UUIDGenerator.getUUID();
                newBrdShop.setId(id);
                newBrdShop.setBid(Constant.TIGER_BID);
                newBrdShop.setParentId("defaultId");
                newBrdShop.setLeaf("0");
                selectDataService.insertBrdShop(newBrdShop);
                shopInsertNum.getAndIncrement();
            }else {
                newBrdShop.setId(brdShop.getId());
                selectDataService.updateBrdShop(newBrdShop);
                shopUpdateNum.getAndIncrement();
            }
        });
        long s2 = System.currentTimeMillis();
        log.info("===== table brd_shop insert {}条数据，table brd_shop update {}条数据 take time : {}s ===== ",
                shopInsertNum,shopUpdateNum,(s2 - s1)/1000);
    }




    @RequestMapping(value = "testThread")
    public void testThread(String data){
        InsertBrdCustomerInfoThread insertBrdCustomerInfoThread = new InsertBrdCustomerInfoThread(data);
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(insertBrdCustomerInfoThread);
    }


}
