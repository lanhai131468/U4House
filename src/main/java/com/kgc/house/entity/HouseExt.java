package com.kgc.house.entity;

public class HouseExt extends House {

 private String sname;//街道名称
 private String dname;//区域名称
 private String tname;//类型名称

 private Integer distrctid;//区域的ID


 public Integer getDistrctid() {
  return distrctid;
 }

 public void setDistrctid(Integer distrctid) {
  this.distrctid = distrctid;
 }

 public String getSname() {
  return sname;
 }

 public void setSname(String sname) {
  this.sname = sname;
 }

 public String getDname() {
  return dname;
 }

 public void setDname(String dname) {
  this.dname = dname;
 }

 public String getTname() {
  return tname;
 }

 public void setTname(String tname) {
  this.tname = tname;
 }



 @Override
 public String toString() {
  return super.toString()+"HouseExt{" +
   "sname='" + sname + '\'' +
   ", dname='" + dname + '\'' +
   ", tname='" + tname + '\'' +
   ", distrctid=" + distrctid +
   '}';
 }
}
