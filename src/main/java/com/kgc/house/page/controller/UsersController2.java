package com.kgc.house.page.controller;

import com.kgc.house.entity.Users;
import com.kgc.house.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

//登录和注册页面控制器
@Controller
@RequestMapping("/page/")
public class UsersController2 {

 //注入业务层
 @Autowired
 private UsersService usersService;

 @RequestMapping("checkUname") //  /
 @ResponseBody
 public String username(String username){

  System.out.println(username);

  //都用业务层
  int i = usersService.gerUser(username);

  System.out.println(i);

  return "{\"result\":"+i+"}";  //返回json数据
 }

 //添加注册的用户
 @RequestMapping("addUser")
 public String addUser(Users users){

  //调用业务层
  int temp = usersService.addUser(users);

  //用户注册成功进入登录页面
  if(temp>0){

   //转发至登录页面
   return "login";
  }
  else {

   return "error";
  }
 }

 //用户登录
 @RequestMapping("login")
 public String login(String name, String password, Model model, HttpSession httpSession){

//调用业务层
  Users users = usersService.login(name, password);

  if (users!=null){
//登录成功进入管理页面

   //只要登录成功  使用session和cookie保存用户的信息
   httpSession.setAttribute("users",users);  //保存用户的信息
   httpSession.setMaxInactiveInterval(600); //保存数据的声明周期是30秒
   return "guanli";  //管理页面
  }else {
//登录失败 提示错误信息  并且继续选择登录

   //通过model保存提示的错误信息
   model.addAttribute("info","输入的用户名或密码错误");

   //返回到登录的页面  继续登录
   return "login";
  }


 }



}
