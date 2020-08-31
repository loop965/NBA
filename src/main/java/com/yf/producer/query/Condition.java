package com.yf.producer.query;

import java.io.Serializable;
import java.util.List;


/**
 * Created by Administrator on 2016/11/4.
 */
public class Condition implements Serializable{

  @JField2DCondition(sources = "sex",columns = "a.sex", operators = Operator.Equal)
  public String sex;

  @JField2DCondition(sources = "time",columns = "c.time", operators = Operator.Equal)
  public String time;

  @JField2DCondition(sources = "locationId",columns = "c.location_id", operators = Operator.Equal)
  public String locationId;

  @JField2DCondition(sources = "province",columns = "a.province", operators = Operator.Equal)
  private String province;

  @JField2DCondition(sources = {"city","b.city"},columns = {"a.city","b.city"}, operators = Operator.Equal)
  private String city;

  public List<Phrase> getList() {
    return list;
  }

  public void setList(List<Phrase> list) {
    this.list = list;
  }

  private List<Phrase> list;

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getLocationId() {
    return locationId;
  }

  public void setLocationId(String locationId) {
    this.locationId = locationId;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }
}
