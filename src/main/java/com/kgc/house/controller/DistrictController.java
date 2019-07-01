package com.kgc.house.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.District;
import com.kgc.house.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/")
public class DistrictController {

 //注入业务类
 @Autowired
 private DistrictService districtService;


 //分页查询所有区域
 @RequestMapping("getDistrict")
 @ResponseBody
 public Map<String, Object> getDistrict(Integer page, Integer rows) {

  //调用业务层
  PageInfo<District> allPage = districtService.getAllPage(page, rows);

  Map<String, Object> map = new HashMap<>();

  map.put("rows", allPage.getList());
  map.put("total", allPage.getTotal());

  return map;

 }

 //添加数据
 @RequestMapping("add")
 @ResponseBody
 public String add(District district) {

  int i = districtService.insertSelective(district);

  return "{\"result\":" + i + "}";

 }

 //修改数据
 @RequestMapping("up")
 @ResponseBody
 public String upDate(District district) {

  int i = districtService.updateByPrimaryKey(district);

  return "{\"result\":" + i + "}";

 }

 //删除单条数据(同时删除区域和街道)
 @RequestMapping("deleteById")
 @ResponseBody
 public String deleteById(Integer id) {

  int i = districtService.deleteByPrimaryKey(id);

  return "{\"result\":" + i + "}";
 }

 //批量删除数据  前段页面不能返回数组 只能用String类型去接收
 @RequestMapping("deleteAll")
 @ResponseBody
 public String deleteAll(String ids) {

  //将字符串转换成字符数组
  String[] strings = ids.split(",");

  //创建Integer类型的数组
  Integer[] integers = new Integer[strings.length];

  for (int i = 0; i < strings.length; i++) {

   //需要将string类型的进行转换
   integers[i] = Integer.parseInt(strings[i]);

  }

  //调用业务
  int i = districtService.deleteAll(integers);

  return "{\"result\":" + i + "}";

 }

}


