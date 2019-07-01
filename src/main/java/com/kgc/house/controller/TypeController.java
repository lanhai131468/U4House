package com.kgc.house.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Type;
import com.kgc.house.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/type/")
public class TypeController {

 //注入业务层
 @Autowired
 private TypeService typeService;

 //分页查询类型/type/getTypeAll
 @RequestMapping("getTypeAll")
 @ResponseBody
 public Map<String,Object> getTypeAll(Integer page,Integer rows){

  //调用业务层
  PageInfo<Type> typeAll = typeService.getTypeAll(page, rows);

  //创建map集合
  Map<String,Object> map=new HashMap<>();

  map.put("rows",typeAll.getList());
  map.put("total",typeAll.getTotal());

  return map;

 }

}
