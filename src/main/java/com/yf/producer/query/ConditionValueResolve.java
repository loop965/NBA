package com.yf.producer.query;

import org.apache.commons.lang3.StringUtils;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/5.
 */
public class ConditionValueResolve {

  /**
   *
   * @param clazz
   * @param object
   * @return Map<String, String> key 是source， value是条件
   */
  public static List<Phrase> resolveBeanFieldValue(String clazz, Object object){
    Map<String, Operator> operatorMap = JFieldCondtionResolve.getInstance().getOperatorMap(clazz);
    Map<String, String> columnMap = JFieldCondtionResolve.getInstance().getColumnMap(clazz);
    List<Phrase> rtn = new ArrayList<Phrase>();
    if(object == null){
      return rtn;
    }
    try{
      Field[] fields = JFieldCondtionResolve.getInstance().getDeclaredField(clazz);
      for(Field field : fields){
        field.setAccessible(true);
        Object value = field.get(object);
        if(value != null && StringUtils.isNotBlank(value.toString()) && (field.getAnnotation(JField2DCondition.class) != null)){
          String key = field.getName();
          String[] sources = JFieldCondtionResolve.getInstance().getSources(clazz, key);
          if(sources != null){
            key = sources[0];
          }
          Phrase phrase = new Phrase(columnMap.get(key), operatorMap.get(key), value);
          rtn.add(phrase);
        }
      }
    }catch (Exception e){
      throw new RuntimeException(e);
    }
    return rtn;
  }

//  public static void resovleRequestValue(String clazz, HttpServletRequest request, Page page){
//    Map<String, Operator> operatorMap = JFieldCondtionResolve.getInstance().getOperatorMap(clazz);
//    Map<String, String> columnMap = JFieldCondtionResolve.getInstance().getColumnMap(clazz);
//    Map<String, String[]> parameterMap = request.getParameterMap();
//
//    String cPage = request.getParameter("cPage");
//    if (StringUtils.isNumeric(cPage)){
//      page.setCPage(Integer.parseInt(cPage));
//    }
//    String pSize = request.getParameter("pSize");
//    if (StringUtils.isNumeric(pSize)){
//      page.setPSize(Integer.parseInt(pSize));
//    }
//
//    for(Map.Entry<String, String[]> entry : parameterMap.entrySet()){
//      String key = entry.getKey();
//      String value = entry.getValue()[0];
//      if((!key.equals("cPage")) && (!key.equals("pSize")) && (!key.equals("tSize")) && (!key.equals("tSize"))){
//        String columStr = columnMap.get(key);
//        if(StringUtils.equals(value, Page.SORT_ASC_KEY) || StringUtils.equals(value, Page.SORT_DESC_KEY)){
//          page.setOrderByName(columStr);
//          page.setOrderByDirection(value);
//        }
//      }
//    }
//  }

  public static void main(String[] args) {
    Condition condition = new Condition();
    condition.setCity("NJ");
    condition.setSex("1");
    List<Phrase> phrases = resolveBeanFieldValue(Condition.class.getName(),condition);
    System.out.println(phrases);
  }


}
