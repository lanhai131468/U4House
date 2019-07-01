package com.kgc.house.page.controller;


import com.kgc.house.entity.Type;
import com.kgc.house.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/page/")
public class TypeController2 {

 //注入业务层
 @Autowired
 private TypeService typeService;

 //页面发送异步请求获取所有的房屋类型
 @RequestMapping("getAllType")
 @ResponseBody
 public List<Type> getAllType(){

  //调用业务层
  List<Type> allType = typeService.getAllType();

  return allType;

 }
}
