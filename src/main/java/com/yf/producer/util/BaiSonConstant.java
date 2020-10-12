package com.yf.producer.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: yf
 * @date: 2020/08/31  16:48
 * @desc:
 */
public class BaiSonConstant {

    public static final Map<String,String> STYLE_MAP = new HashMap<>();

    public static final Map<String,String> SEASON_MAP = new HashMap<>();

    public static final Map<String,Integer> ONE_CATEGORY_MAP = new HashMap<>();

    public static final Map<String,Integer> TWO_CATEGORY_MAP = new HashMap<>();

    public static final Map<String,Integer> COLOR_MAP = new HashMap<>();

    public static final String BASE_URL = "https://mc3.sqzw.com/release/mc3/webopm/web/?app_act=api/ec&app_mode=func&customer_id=2804";

    public static final String KEY = "modoo";

    public static final String SECRET = "W2w2niQCyRu8YB";

    public static final String VERSION = "0.1";



    static {
        // 季节
        SEASON_MAP.put("01","1");
        SEASON_MAP.put("02","2");
        SEASON_MAP.put("03","3");
        SEASON_MAP.put("10016","3");
        SEASON_MAP.put("04","4");
        SEASON_MAP.put("00","4");

        // 风格
        STYLE_MAP.put("001","2");
        STYLE_MAP.put("002","1");
        STYLE_MAP.put("003","3");
        STYLE_MAP.put("004","6");

        // 一级品类
        ONE_CATEGORY_MAP.put("035",1);
        ONE_CATEGORY_MAP.put("003",1);
        ONE_CATEGORY_MAP.put("030",1);
        ONE_CATEGORY_MAP.put("013",1);
        ONE_CATEGORY_MAP.put("014",1);
        ONE_CATEGORY_MAP.put("019",1);
        ONE_CATEGORY_MAP.put("001",1);
        ONE_CATEGORY_MAP.put("004",1);
        ONE_CATEGORY_MAP.put("028",1);
        ONE_CATEGORY_MAP.put("009",1);
        ONE_CATEGORY_MAP.put("016",2);
        ONE_CATEGORY_MAP.put("008",2);
        ONE_CATEGORY_MAP.put("002",2);
        ONE_CATEGORY_MAP.put("011",3);
        ONE_CATEGORY_MAP.put("020",3);
        ONE_CATEGORY_MAP.put("022",3);
        ONE_CATEGORY_MAP.put("100143",3);
        ONE_CATEGORY_MAP.put("033",3);
        ONE_CATEGORY_MAP.put("034",3);
        ONE_CATEGORY_MAP.put("012",3);
        ONE_CATEGORY_MAP.put("015",3);
        ONE_CATEGORY_MAP.put("017",3);
        ONE_CATEGORY_MAP.put("018",3);
        ONE_CATEGORY_MAP.put("021",3);
        ONE_CATEGORY_MAP.put("006",3);
        ONE_CATEGORY_MAP.put("005",3);
        ONE_CATEGORY_MAP.put("010",4);
        ONE_CATEGORY_MAP.put("007",4);
        ONE_CATEGORY_MAP.put("029",4);
        ONE_CATEGORY_MAP.put("023",4);
        ONE_CATEGORY_MAP.put("024",6);
        ONE_CATEGORY_MAP.put("026",7);
        ONE_CATEGORY_MAP.put("025",7);
        ONE_CATEGORY_MAP.put("027",7);

        // 二级品类
        TWO_CATEGORY_MAP.put("035",1);
        TWO_CATEGORY_MAP.put("003",1);
        TWO_CATEGORY_MAP.put("030",2);
        TWO_CATEGORY_MAP.put("013",2);
        TWO_CATEGORY_MAP.put("014",2);
        TWO_CATEGORY_MAP.put("019",2);
        TWO_CATEGORY_MAP.put("001",2);
        TWO_CATEGORY_MAP.put("004",4);
        TWO_CATEGORY_MAP.put("028",29);
        TWO_CATEGORY_MAP.put("009",29);
        TWO_CATEGORY_MAP.put("016",10);
        TWO_CATEGORY_MAP.put("008",10);
        TWO_CATEGORY_MAP.put("002",11);
        TWO_CATEGORY_MAP.put("011",5);
        TWO_CATEGORY_MAP.put("020",6);
        TWO_CATEGORY_MAP.put("022",6);
        TWO_CATEGORY_MAP.put("100143",9);
        TWO_CATEGORY_MAP.put("033",9);
        TWO_CATEGORY_MAP.put("034",9);
        TWO_CATEGORY_MAP.put("012",9);
        TWO_CATEGORY_MAP.put("015",9);
        TWO_CATEGORY_MAP.put("017",9);
        TWO_CATEGORY_MAP.put("018",9);
        TWO_CATEGORY_MAP.put("021",9);
        TWO_CATEGORY_MAP.put("006",9);
        TWO_CATEGORY_MAP.put("005",31);
        TWO_CATEGORY_MAP.put("010",13);
        TWO_CATEGORY_MAP.put("007",13);
        TWO_CATEGORY_MAP.put("029",13);
        TWO_CATEGORY_MAP.put("023",12);
        TWO_CATEGORY_MAP.put("024",34);
        TWO_CATEGORY_MAP.put("026",27);
        TWO_CATEGORY_MAP.put("025",19);
        TWO_CATEGORY_MAP.put("027",35);

        // 颜色
        COLOR_MAP.put("050",16);
        COLOR_MAP.put("002",1);
        COLOR_MAP.put("001",2);
        COLOR_MAP.put("341",22);
        COLOR_MAP.put("006",11);
        COLOR_MAP.put("003",7);
        COLOR_MAP.put("010",10);
        COLOR_MAP.put("032",6);
        COLOR_MAP.put("342",6);
        COLOR_MAP.put("004",3);
        COLOR_MAP.put("007",9);
        COLOR_MAP.put("055",7);
        COLOR_MAP.put("009",7);
        COLOR_MAP.put("082",7);
        COLOR_MAP.put("035",2);
        COLOR_MAP.put("013",7);
        COLOR_MAP.put("005",13);
        COLOR_MAP.put("225",11);
        COLOR_MAP.put("312",7);
        COLOR_MAP.put("303",16);
        COLOR_MAP.put("306",11);
        COLOR_MAP.put("218",7);
        COLOR_MAP.put("052",11);
        COLOR_MAP.put("041",11);
        COLOR_MAP.put("016",3);
        COLOR_MAP.put("021",8);
        COLOR_MAP.put("231",6);
        COLOR_MAP.put("073",1);
        COLOR_MAP.put("029",1);
        COLOR_MAP.put("000",16);
        COLOR_MAP.put("110",7);
        COLOR_MAP.put("020",6);
        COLOR_MAP.put("184",1);
        COLOR_MAP.put("048",11);
        COLOR_MAP.put("097",11);
        COLOR_MAP.put("025",10);
        COLOR_MAP.put("206",16);
        COLOR_MAP.put("022",10);
        COLOR_MAP.put("077",6);
        COLOR_MAP.put("304",11);
        COLOR_MAP.put("313",6);
        COLOR_MAP.put("098",9);
        COLOR_MAP.put("096",1);
        COLOR_MAP.put("017",3);
        COLOR_MAP.put("289",10);
        COLOR_MAP.put("150",11);
        COLOR_MAP.put("144",6);
        COLOR_MAP.put("308",2);
        COLOR_MAP.put("299",6);
        COLOR_MAP.put("053",11);
        COLOR_MAP.put("255",11);
        COLOR_MAP.put("076",11);
        COLOR_MAP.put("121",10);
        COLOR_MAP.put("314",10);
        COLOR_MAP.put("109",3);
        COLOR_MAP.put("070",22);
        COLOR_MAP.put("315",7);
        COLOR_MAP.put("239",10);
        COLOR_MAP.put("187",8);
        COLOR_MAP.put("250",16);
        COLOR_MAP.put("047",7);
        COLOR_MAP.put("181",10);
        COLOR_MAP.put("220",11);
        COLOR_MAP.put("122",6);
        COLOR_MAP.put("063",7);
        COLOR_MAP.put("129",7);
        COLOR_MAP.put("034",7);
        COLOR_MAP.put("165",7);
        COLOR_MAP.put("189",1);
        COLOR_MAP.put("057",15);
        COLOR_MAP.put("062",2);
        COLOR_MAP.put("049",3);
        COLOR_MAP.put("012",6);
        COLOR_MAP.put("136",3);
        COLOR_MAP.put("229",1);
        COLOR_MAP.put("051",13);
        COLOR_MAP.put("139",7);
        COLOR_MAP.put("075",6);
        COLOR_MAP.put("115",6);
        COLOR_MAP.put("126",7);
        COLOR_MAP.put("317",11);
        COLOR_MAP.put("316",18);
        COLOR_MAP.put("296",3);
        COLOR_MAP.put("259",11);
        COLOR_MAP.put("033",10);
        COLOR_MAP.put("018",13);
        COLOR_MAP.put("318",10);
        COLOR_MAP.put("178",6);
        COLOR_MAP.put("183",9);
        COLOR_MAP.put("176",8);
        COLOR_MAP.put("039",9);
        COLOR_MAP.put("236",9);
        COLOR_MAP.put("111",11);
        COLOR_MAP.put("339",7);
        COLOR_MAP.put("081",2);
        COLOR_MAP.put("038",3);
        COLOR_MAP.put("219",8);
        COLOR_MAP.put("185",7);
        COLOR_MAP.put("203",1);
        COLOR_MAP.put("066",1);
        COLOR_MAP.put("123",13);
        COLOR_MAP.put("266",18);
        COLOR_MAP.put("028",6);
        COLOR_MAP.put("101",11);
        COLOR_MAP.put("080",1);
        COLOR_MAP.put("290",10);
        COLOR_MAP.put("087",11);
        COLOR_MAP.put("267",1);
        COLOR_MAP.put("114",11);
        COLOR_MAP.put("128",1);
        COLOR_MAP.put("093",10);
        COLOR_MAP.put("285",22);
        COLOR_MAP.put("340",6);
        COLOR_MAP.put("174",11);
        COLOR_MAP.put("030",10);
        COLOR_MAP.put("158",6);
        COLOR_MAP.put("079",3);
        COLOR_MAP.put("186",18);
        COLOR_MAP.put("067",11);
        COLOR_MAP.put("133",6);
        COLOR_MAP.put("283",22);
        COLOR_MAP.put("083",1);
        COLOR_MAP.put("157",6);
        COLOR_MAP.put("094",6);
        COLOR_MAP.put("278",17);
        COLOR_MAP.put("131",7);
        COLOR_MAP.put("275",2);
        COLOR_MAP.put("132",7);
        COLOR_MAP.put("105",6);
        COLOR_MAP.put("300",22);
        COLOR_MAP.put("092",7);
        COLOR_MAP.put("023",21);
        COLOR_MAP.put("310",11);
        COLOR_MAP.put("195",6);
        COLOR_MAP.put("064",7);
        COLOR_MAP.put("059",2);
        COLOR_MAP.put("014",22);
        COLOR_MAP.put("234",1);
        COLOR_MAP.put("279",1);
        COLOR_MAP.put("251",7);
        COLOR_MAP.put("118",22);
        COLOR_MAP.put("262",11);
        COLOR_MAP.put("271",22);
        COLOR_MAP.put("120",2);
        COLOR_MAP.put("286",7);
        COLOR_MAP.put("107",1);
        COLOR_MAP.put("090",2);
        COLOR_MAP.put("248",1);
        COLOR_MAP.put("343",22);
        COLOR_MAP.put("044",13);
        COLOR_MAP.put("142",6);
        COLOR_MAP.put("056",14);
        COLOR_MAP.put("024",9);
        COLOR_MAP.put("264",1);
        COLOR_MAP.put("167",7);
        COLOR_MAP.put("322",6);
        COLOR_MAP.put("191",8);
        COLOR_MAP.put("116",8);
        COLOR_MAP.put("113",10);
        COLOR_MAP.put("060",10);
        COLOR_MAP.put("344",22);
        COLOR_MAP.put("102",3);
        COLOR_MAP.put("089",7);
        COLOR_MAP.put("309",1);
        COLOR_MAP.put("036",1);
        COLOR_MAP.put("331",7);
        COLOR_MAP.put("345",9);
        COLOR_MAP.put("085",3);
        COLOR_MAP.put("282",1);
        COLOR_MAP.put("338",22);
        COLOR_MAP.put("337",9);
        COLOR_MAP.put("196",2);
        COLOR_MAP.put("164",11);
        COLOR_MAP.put("026",13);
        COLOR_MAP.put("071",11);
        COLOR_MAP.put("130",2);
        COLOR_MAP.put("027",22);
    }


}
