package com.yf.producer.dao.modoo;

import com.yf.producer.pojo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @author: yf
 * @create: 2019/11/21  10:51
 */
@Component
public interface InsertDataMapper {

    /**
     * 获取客户信息
     * @return
     */
    List<BrdCustomerInfo> selectBrdCustomerInfoList();

    void insertBrdCustomerInfo(BrdCustomerInfo brdCustomerInfo);

    void insertBrdMemberInfo(BrdMemberInfo memberInfo);

    void insertBrdProduct(BrdProduct brdProduct);

    void insertMemberRecharge(MemberRecharge e);

    void insertBrdCoupon(BrdCoupon e);

    void insertBrdMemberCard(BrdMemberCard e);

    void insertBrdOrder(BrdOrder e);

    void insertBrdConsumptionDetails();

    void insertOrderProduct(BrdOrderProductVo orderProductVo);

    BrdMemberInfo selectBrdMemberInfo(@Param("sourceMId") String sourceMId, @Param("tigerBid") String tigerBid);

    BrdCustomerInfo selectBrdCustomerInfo(@Param("cid") String cid);

    void updateBrdMemberInfo(BrdMemberInfo sourceBrdMemberInfo);

    void updateBrdCustomerInfo(BrdCustomerInfo sourceBrdCustomerInfo);

    BrdProduct selectBrdProduct(@Param("uid") String uid, @Param("bid") String tigerBid);

    void updateBrdProduct(BrdProduct newBrdProduct);

    BrdOrder selectBrdOrder(@Param("uid") String uid, @Param("bid") String tigerBid);

    BrdCoupon selectBrdCoupon(@Param("couponId") String couponId, @Param("tigerBid") String tigerBid);

    MemberRecharge selectMemberRecharge(@Param("uid") String uid, @Param("tigerBid") String tigerBid);

    BrdMemberCard selectBrdMemberCard(@Param("uid") String uid, @Param("tigerBid") String tigerBid);

    void insertBrdProductStock(BrdProductStock brdProductStock);

    void updateBrdProductStock(BrdProductStock updateBrdProductStock);

    void insertBrdShop(BrdShop newBrdShop);

    BrdShop selectBrdShop(@Param("resourceId") String resourceId, @Param("tigerBid") String tigerBid);

    void updateBrdShop(@Param("record") BrdShop newBrdShop);

    void insertFound(Found found);

    String testIot();
}
