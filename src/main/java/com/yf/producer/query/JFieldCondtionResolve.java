package com.yf.producer.query;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2016/11/4.
 */
public class JFieldCondtionResolve {

  /**
   * 存储分析JField2DCondition注解信息
   * Map<String, Map<String, String>> 中 key是类名
   * value：Map<String, String> 中key是对接注解下的source值，value对用注解上的column值
   */
  private static Map<String, Map<String, String>> jSource2ColumnInfo = new ConcurrentHashMap<String, Map<String, String>>();

  /**
   * 存储分析JField2DCondition注解信息
   * Map<String, Map<String, Operator>> 中 key是类名
   * value：Map<String, Operator> 中key是对接注解下的source值，value对用注解上的Operator值
   */
  private static Map<String, Map<String, Operator>> jSource2OperatorInfo = new ConcurrentHashMap<String, Map<String, Operator>>();

  /**
   * 存储分析JField2DCondition注解信息
   * Map<String, Map<String, Operator>> 中 key是类名
   * value：Map<String, Operator> 中key是这个类下的Field名称，value是这个Field对应的注解上的sources数组
   */
  private static Map<String, Map<String, String[]>> jField2SourceInfo = new ConcurrentHashMap<String, Map<String, String[]>>();

  //存储类的Field类型，key是类名，Field[]是当前类所有的Field，不包含继承过来的Field
  private static Map<String, Field[]> declaredFieldInfo = new ConcurrentHashMap<String, Field[]>();

  private JFieldCondtionResolve(){}

  public static JFieldCondtionResolve getInstance(){
    return JFieldCondtionResolveHolder.instance;
  }

  static class JFieldCondtionResolveHolder{
    static JFieldCondtionResolve instance = new JFieldCondtionResolve();
  }

  public Field[] getDeclaredField(String clazz){
    Field[] fields = declaredFieldInfo.get(clazz);
    if(fields == null){
      try {
        fields = Class.forName(clazz).getDeclaredFields();
        declaredFieldInfo.put(clazz, fields);
      } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
      }
    }
    return fields;
  }

  public Map<String, Operator> getOperatorMap(String clazz){
    Map<String, Operator> rtn = jSource2OperatorInfo.get(clazz);
    if(rtn == null){
      resolveAnnotation(clazz);
      rtn = jSource2OperatorInfo.get(clazz);
    }
    return rtn;
  }

  public String[] getSources(String clazz, String fieldName){
    Map<String, String[]> field2SourceMap = jField2SourceInfo.get(clazz);
    if(field2SourceMap == null){
      resolveAnnotation(clazz);
      field2SourceMap = jField2SourceInfo.get(clazz);
    }
    if(field2SourceMap == null){
      return null;
    }else{
      return field2SourceMap.get(fieldName);
    }
  }

  public Map<String, String> getColumnMap(String clazz){
    Map<String, String> rtn = jSource2ColumnInfo.get(clazz);
    if(rtn == null){
      resolveAnnotation(clazz);
      rtn = jSource2ColumnInfo.get(clazz);
    }
    return rtn;
  }

  private void resolveAnnotation(String clazz) {
    Map<String, Operator> operatorMap = new HashMap<String, Operator>();
    Map<String, String> columnMap = new HashMap<String, String>();
    Map<String, String[]> field2SourceMap = new HashMap<String, String[]>();
    Field[] fields = getDeclaredField(clazz);
    for(Field field : fields){
      JField2DCondition annotation = field.getAnnotation(JField2DCondition.class);
      if(annotation == null){
        continue;
      }
      String[] sources = annotation.sources();
      String[] columns = annotation.columns();
      Operator[] operators = annotation.operators();
      if(sources == null){
        sources = new String[]{field.getName()};
      }
      if(sources.length != columns.length){
        throw new RuntimeException(clazz + "配置JField2DCondition注解错误");
      }else{
        field2SourceMap.put(field.getName(), sources);
        int len = sources.length;
        int operatorsLen = operators.length;
        for(int i=0; i<len; i++){
          Operator operator = null;
          if(operatorsLen > i){
            operator = operators[i];
          }else{
            operator = Operator.Equal;
          }
          operatorMap.put(sources[i], operator);
          columnMap.put(sources[i], columns[i]);
        }
      }
    }
    jSource2ColumnInfo.put(clazz, columnMap);
    jSource2OperatorInfo.put(clazz, operatorMap);
    jField2SourceInfo.put(clazz, field2SourceMap);
  }


}
