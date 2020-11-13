package com.yf.producer.dao.modoo;

import com.yf.producer.pojo.BrdProduct;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: yf
 * @date: 2020/08/05  12:46
 * @desc:
 */
@Component
public interface ModooMapper {

    String selectRoleName();

    List<BrdProduct> selectBrdProductList();
}
