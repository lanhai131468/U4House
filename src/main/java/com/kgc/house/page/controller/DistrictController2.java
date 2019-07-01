package com.kgc.house.page.controller;

import com.kgc.house.entity.District;
import com.kgc.house.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/page/")
public class DistrictController2 {

 //注入业务层
 @Autowired
 private DistrictService districtService;


 //发型异步请求获取所有区域
 @RequestMapping("getAllDistrict")
 @ResponseBody
 public List<District> getAllDistrict(){

  //调用业务层
  List<District> allDistrict = districtService.getAllDistrict();

  return allDistrict;

 }
}
