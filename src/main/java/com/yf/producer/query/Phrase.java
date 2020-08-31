package com.yf.producer.query;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/7.
 */
public class Phrase implements Serializable{
  public String colnum;
  public Operator operator;
  public Object value;
  public Object value1;

  public Phrase(){

  }
  public Phrase(String colnum, Operator operator, Object value) {
    this.colnum = colnum;
    this.operator = operator;
    this.value = value;
  }
  public Phrase(String colnum, Operator operator, Object value,Object value1) {
    this.colnum = colnum;
    this.operator = operator;
    this.value = value;
    this.value1 = value1;
  }

  public String getColnum() {
    return colnum;
  }

  public void setColnum(String colnum) {
    this.colnum = colnum;
  }

  public Object getValue() {
    return value;
  }

  public void setValue(Object value) {
    this.value = value;
  }

  public Operator getOperator() {
    return operator;
  }

  public void setOperator(Operator operator) {
    this.operator = operator;
  }

  public Object getValue1() {
    return value1;
  }

  public void setValue1(Object value1) {
    this.value1 = value1;
  }
}
