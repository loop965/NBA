package com.yf.producer.query;


/**
 * Created by Administrator on 2016/11/4.
 */
public enum Operator {
  Equal("single", "="),
  NotEqual("single", "!="),
  Greater("single", ">"),
  Less("single", "<"),
  GreaterOrEqual("single", ">="),
  LessOrEqual("single", "<="),
  FullFuzzy("fuzzy", " like '%?%'"),
  PreFuzzy("fuzzy", " like '%?'"),
  PostFuzzy("fuzzy", " like '?%'"),
  Between("binary", " between "),
  In("multi", "in"),
  Contain("contain", "in"),
  NotIn("multi", "not in"),
  ;

  private String type;
  private String oparation;
  private Operator(String type, String oparation){
    this.type = type;
    this.oparation = oparation;
  }

  public String getOparation(){
    return oparation;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setOparation(String oparation) {
    this.oparation = oparation;
  }
}
