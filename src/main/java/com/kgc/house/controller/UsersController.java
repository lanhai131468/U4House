package com.kgc.house.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.UserCondition;
import com.kgc.house.entity.Users;
import com.kgc.house.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/users/")
public class UsersController {

 //注入业务层
 @Autowired
 private UsersService usersService;

 @RequestMapping("usersAll")
 @ResponseBody
 public Map<String,Object> usersAll(UserCondition userCondition){

  //调用业务层
  PageInfo<Users> usersAll = usersService.getUsersAll(userCondition);

  //创建Map集合  存储当前页数据和总记录数
  Map<String,Object> map=new HashMap<>();
  map.put("total",usersAll.getTotal());
  map.put("rows",usersAll.getList());

  System.out.println(map.size());

  return map;

 }
}
