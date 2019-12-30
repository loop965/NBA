package com.yf.producer.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author: yf
 * @date: 2019/12/18  13:12
 * @desc:
 */
@Data
@Accessors(chain = true)
public class Found {

    private Integer id;

    private String name;

    private String dwjz;

    private String gsz;

    private String gszzl;

    private String gztime;

}
