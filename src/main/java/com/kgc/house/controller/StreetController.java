package com.kgc.house.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Street;
import com.kgc.house.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/street/")
public class StreetController {

 //注入业务
 @Autowired
 private StreetService streetService;

 //分页查询区域对应的街道
 @RequestMapping("streetGetAll")
 @ResponseBody
 public Map<String,Object> streetGetAll(Integer page,Integer rows,Integer id){

  PageInfo<Street> streetAll = streetService.getStreetAll(page, rows,id);

  Map<String,Object> map=new HashMap<>();

  map.put("rows",streetAll.getList());
  map.put("total",streetAll.getTotal());

  return map;
 }

 //删除街道
 @RequestMapping("deleteStreetId")
 @ResponseBody
 public String deleteStreetId(Integer id){

 /* System.out.println(id);*/
  int i = streetService.deleteById(id);

  return "{\"result\":"+i+"}";

 }
}
