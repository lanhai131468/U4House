package com.kgc.house.entity;

public class HouseCondition {

 //标题
 private String title;
 //开始价格
 private Integer startPrice;
 //结束价格
 private Integer endPrice;
 //区域id
 private Integer did;
 //街道id
 private Integer sid;
 //类型id
 private Integer tid;

 //面积
 private String flooa;
 //起始面积
 private Integer startFlooa;
 //结束面积
 private Integer endFlooa;

 //设置分页的属性
 private Integer page;
 private Integer pageSize=2;

 public String getTitle() {
  return title;
 }

 public void setTitle(String title) {
  this.title=title;
 }

 public Integer getStartPrice() {
  return startPrice;
 }

 public void setStartPrice(Integer startPrice) {
  this.startPrice = startPrice;
 }

 public Integer getEndPrice() {
  return endPrice;
 }

 public void setEndPrice(Integer endPrice) {
  this.endPrice = endPrice;
 }

 public Integer getDid() {
  return did;
 }

 public void setDid(Integer did) {
  this.did = did;
 }

 public Integer getSid() {
  return sid;
 }

 public void setSid(Integer sid) {
  this.sid = sid;
 }

 public Integer getTid() {
  return tid;
 }

 public void setTid(Integer tid) {
  this.tid = tid;
 }

 public String getFlooa() {
  return flooa;
 }

 public void setFlooa(String flooa) {
  this.flooa = flooa;
  if (!flooa.equals("")){
   //字符串拆分成数组
   String[] splitArr = flooa.split("-");
   //赋值起始面积和结束面积
   this.setStartFlooa(Integer.parseInt(splitArr[0]));
   this.setEndFlooa(Integer.parseInt(splitArr[1]));
  }
 }

 public Integer getStartFlooa() {
  return startFlooa;
 }

 public void setStartFlooa(Integer startFlooa) {
  this.startFlooa = startFlooa;
 }

 public Integer getEndFlooa() {
  return endFlooa;
 }

 public void setEndFlooa(Integer endFlooa) {
  this.endFlooa = endFlooa;
 }

 public Integer getPage() {
  return page;
 }

 public void setPage(Integer page) {
  this.page = page;
 }

 public Integer getPageSize() {
  return pageSize;
 }

 public void setPageSize(Integer pageSize) {
  this.pageSize = pageSize;
 }
}
