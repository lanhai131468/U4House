package com.kgc.house.entity;

//查询条件的实体类
public class UserCondition {

 private Integer page;  //当前页码
 private Integer rows;   //页大小

 //查询条件
 private Integer startAge;  //开始年龄
 private Integer endAge; //结束年龄
 private Integer tel; //电话

 public Integer getPage() {
  return page;
 }

 public void setPage(Integer page) {
  this.page = page;
 }

 public Integer getRows() {
  return rows;
 }

 public void setRows(Integer rows) {
  this.rows = rows;
 }

 public Integer getStartAge() {
  return startAge;
 }

 public void setStartAge(Integer startAge) {
  this.startAge = startAge;
 }

 public Integer getEndAge() {
  return endAge;
 }

 public void setEndAge(Integer endAge) {
  this.endAge = endAge;
 }

 public Integer getTel() {
  return tel;
 }

 public void setTel(Integer tel) {
  this.tel = tel;
 }


}
