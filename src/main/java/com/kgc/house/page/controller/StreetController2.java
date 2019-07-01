package com.kgc.house.page.controller;


import com.kgc.house.entity.Street;
import com.kgc.house.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

//页面街道控制器
@Controller
@RequestMapping("/page/")
public class StreetController2 {

 //注入业务层
 @Autowired
 private StreetService streetService;

 //返回所有街道的数据
 @RequestMapping("getStreet")
 @ResponseBody
 public List<Street> getStreet(Integer did){

  //调用业务层
  List<Street> street = streetService.getStreet(did);

/*  System.out.println(street.size());*/

  return street;

 }
}
