package com.yf.producer.study;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * @author: yf
 * @date: 2020/08/20  13:28
 * @desc:
 */
public class Test {


    public static void main(String[] args) throws ScriptException {
        List<Integer> list1 = Collections.singletonList(1);
        String skuNum = "TR170260BS111040D-ZZ12-12 ";
        int length = skuNum.length();
        String colorCode1 = skuNum.substring(length-5,skuNum.length()-2);
        String newSkuNum = skuNum.substring(0,length-5) + "-" + colorCode1;
        String sizeCode = skuNum.substring(length-2,length);
        ExcelReader reader = ExcelUtil.getReader(new File("D:\\品牌导入产品示例-Lulualways12.24.xlsx"),0);
        System.out.println(DateUtil.yesterday());
        String bid = "f93f305a904d44cfaf7d167ff0332386";
        List<String> lines = FileUtil.readLines("D:\\product.txt","utf-8");
        lines.forEach(line ->{
            String[] productArray = line.split("\t");
            String productNum = productArray[0];
            String colorCode = productArray[1];
            String one = productArray[3];
            String two = productArray[4];
            String stock = productArray[5];
            System.out.println(line);
        });
        String url = "//view/All/builds";
        System.out.println(url.replaceAll("(?<!http:)//", "/"));
        String json = "{formula:[{left:\"（body_height/body_weight）\",operator:\"<=\",right:\"0.5\"},{left:\"face_type\",operator:\"=\",right:\"1,4\"}]}";
        String s = json.replaceAll("（","(").replaceAll("）",")");
        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("formula");
        List<String> sqlStr = new ArrayList<>();
        jsonArray.forEach(e -> {
            StringBuilder sb = new StringBuilder();
            JSONObject object = (JSONObject)e;
            String right = object.getString("right");
            String operator = object.getString("operator");
            String left = object.getString("left");
            if (!right.contains(",")){
                sb.append(left).append(" ").append(operator).append(" ").append(right);
            }else {
                sb.append("(").append(strDispose(right,left)).append(")");
            }
            sqlStr.add(sb.toString());
        });
        System.out.println(StringUtils.join(sqlStr," and "));
        String operator = "<";
        String formula = "(3*4+5)*6 < 100.00";
        ScriptEngine scriptEngineManager = new ScriptEngineManager().getEngineByName("JavaScript");
        System.out.println(scriptEngineManager.eval(formula));
        double sum = 0;
        for (int j = 0; j < 24 ; j++) {
            double a = (0.6799 - (j * 0.0283))*1.2*30;
            System.out.println(a);
            sum = sum + a;
        }
        System.out.println(sum);
        int ef = 50;
        int c = ef++;
        max(ef,100);
        System.out.println(c);
        System.out.println(c);
        List<String> list = new ArrayList<>();
        int[] aa = {1,2,3};
        for (int i = 0; i < 1000; i++){
            list.add(i+"");
        }
        list.add("10");
        System.out.println("dev");
        for (String e : list){
            if (StringUtils.equals("10",e)){
                list.remove(e);
                break;
            }
        }

        System.out.println(list.size());
    }

    public static void max(int a, int b){
        a = 100;
        if (a > b){
            System.out.println(a);
        }
    }

    public static String strDispose(String str, String column) {
        List<String> sqlList = new ArrayList<>();
        Arrays.asList(str.split(",")).forEach(temp -> sqlList.add("FIND_IN_SET('" + temp + "', " + column + ") "));
        return StringUtils.join(sqlList," or ");
    }

}
