package com.kgc.house.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.HouseExt;
import com.kgc.house.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/house/")
public class HouseController2 {

 //注入业务层
 @Autowired
 private HouseService houseService;

 //查询未审核的房屋信息并分页   /house/getHouseByState
 @RequestMapping("getHouseByState")
 @ResponseBody
 public Map<String,Object> getHouseByState(Integer page, Integer rows){

  //调用业务层
  PageInfo<HouseExt> houseByState = houseService.getHouseByState(page, rows, 0);

  //创建map集合
  Map<String,Object> map = new HashMap<>();

  map.put("total",houseByState.getTotal());
  map.put("rows",houseByState.getList());

  System.out.println(houseByState.getList().get(0).toString());
  return map;

 }

 //通过审核
 @RequestMapping("passHouse")
 @ResponseBody
 public Map<String,Object> passHouse(String hid){

  //调用业务层的方法
  int temp = houseService.passHouse(hid);
  //创建map集合
  Map<String, Object> map = new HashMap<>();

  map.put("result",temp);

  return map;

 }

 //查询审核的房屋信息
 @RequestMapping("getHouseYesPass")
 @ResponseBody
 public Map<String,Object> getHouseYesPass(Integer page,Integer rows){

  //调用业务层 返回审核通过的信息
  PageInfo<HouseExt> houseYesPass = houseService.getHouseYesPass(page, rows, 1);

  Map<String, Object> map = new HashMap<>();

  map.put("rows",houseYesPass.getList());
  map.put("total",houseYesPass.getTotal());

  return map;

 }

}
