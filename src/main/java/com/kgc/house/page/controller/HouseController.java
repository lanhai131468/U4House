package com.kgc.house.page.controller;

//显示房子所在区域和类型的控制器

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.*;
import com.kgc.house.service.DistrictService;
import com.kgc.house.service.HouseService;
import com.kgc.house.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/page/")
public class HouseController {

 //注入类型业务层
 @Autowired
 private TypeService typeService;

 //注入区域业务层
 @Autowired
 private DistrictService districtService;

 //注入房屋发布信息业务层
 @Autowired
 private HouseService houseService;

 //将查询到的区域和类型 通过Model数据模型转发至发布页面
 @RequestMapping("goFaBu")
 public String goFaBu(Model model){

  List<Type> allType = typeService.getAllType();

  List<District> allDistrict = districtService.getAllDistrict();

  model.addAttribute("allDistrict",allDistrict);
  model.addAttribute("allType",allType);

  //转发至发布页面
  return "fabu";

 }


 //添加房屋发布信息
 //有时候需要从浏览器传入文件，然后后台分析用，后台需要将CommonsMultipartFile格式的文件转换为File
 //@RequestParam(value = "pfile", required = false)
 //          设置false，如果传入空值会报错
 //           设置true，如果传入空值会赋值null
 @RequestMapping("addHouse")
 public String addHouse(House house, @RequestParam(value = "pfile",required = false) CommonsMultipartFile file,
                        HttpSession session) throws IOException {

  //将图片文件存放在D://image目录下

  //获取传入的文件名称
  String filename = file.getOriginalFilename();
  //截取文件的类型
  String substring = filename.substring(filename.lastIndexOf("."));
  //利用当前时间的毫秒值命名为新文件名
  String newFileName=System.currentTimeMillis()+substring;

  //保存文件
  File filePath = new File("D:\\image\\" + newFileName);
  file.transferTo(filePath);

  //接收发布信息的表单  保存到数据库中

  //设置信息编号
  house.setId(System.currentTimeMillis()+"");

  //保存用户的id
  Users users=(Users)session.getAttribute("users");
  house.setUserId(users.getId());

  //设置审核状态
  house.setIspass(0);

  //设置是否删除
  house.setIsdel(0);

  //保存文件名称
  house.setPath(newFileName);

  if (houseService.insertSelective(house)>0){

   //成功添加了发布的信息
   //跳转发布页面 重定向请求
   return "redirect:goFaBu";

  }
  else {

   //删除图片
   filePath.delete();
   return "redirect:goFaBu";
  }
 }

//查询出租房信息   用户id在session域中
 @RequestMapping("getUserHouse")
 public String getUserHouse(Integer page,Integer rows,Model model,HttpSession session){

  //获取保存在session域中的users对象
  Users users=(Users)session.getAttribute("users");

  //调用业务层的方法
  PageInfo<HouseExt> houseExtPageInfo = houseService.selectHouseById(page==null?1:page, 5, users.getId());

  //数据存储到model
  model.addAttribute("houseExtPageInfo",houseExtPageInfo);

  //返回到管理页面
  return "guanli";

 }

 //修改页面回显数据
 @RequestMapping("upfabu")
public String upfabu(String huoseid,Model model){

  System.out.println(huoseid);

  //所有的房屋类型
  List<Type> allType = typeService.getAllType();

  //查询所有区域
  List<District> allDistrict = districtService.getAllDistrict();

  //查询出租房信息
  HouseExt house = houseService.getHouse(huoseid);

  model.addAttribute("allType",allType);
  model.addAttribute("allDistrict",allDistrict);
  model.addAttribute("house",house);

  return "upfabu";

 }


 //发布修改的房屋信息
 @RequestMapping("fabuHouse")
public String fabuHouse(String oldfile,House house,@RequestParam(value = "pfile",required = false) CommonsMultipartFile pfile, HttpSession session) throws IOException {

  //1.修改时判断用户有没有修改图片
  File file=null;
  if(pfile.getOriginalFilename().equals("")){
   //System.out.println("不修改图片");
   //不需要实现文件上传，同时house实体的path属性无需设置属性
  }else {
   //System.out.println("修改图片");
   //上传新的图片，删除旧的图片，设置path为上传新的图片名称
   file=new File("D:\\image\\"+oldfile);
   pfile.transferTo(file);  //保存
   //设置图片名称
   house.setPath(oldfile);
   System.out.println(oldfile);
  }

  //保存数据库的记录  house已经接收部分表单数据
  //设置编号  从表单获取
  //设置审核状态 0  如果表中有默认值 可不设
  //house.setIspass(0);
  //设置是否删除  0
  //house.setIsdel(0);
  //设置图片路径
  // house.setPath(saveName);
  if(houseService.upfabu(house)<=0){
   //成功上传的图片删除
   if(file!=null) file.delete();
  }

  return "redirect:getUserHouse";  //跳转到查询用户出租房

 }

 //删除房屋信息
 @RequestMapping("delhouse")
 @ResponseBody
 public String delHouse(String hid){

  //调用业务层
  int temp = houseService.delHouse(hid);

  return "{\"result\":"+temp+"}";


 }

 //查询所有房屋信息并分页  将数据填充到list页面上
 @RequestMapping("goList")
 public String getHouseByBrowser(Model model,HouseCondition houseCondition){
  //调用业务层
  PageInfo<HouseExt> pageInfo = houseService.getHouseByBrowser(houseCondition);

  //将数据设置到model作用域中
  model.addAttribute("pageInfo",pageInfo);

  //回显标题时,要判断标题是否为空,不为空需要去掉百分号
  if (houseCondition.getTitle()!=null){
  houseCondition.setTitle(houseCondition.getTitle().replaceAll("%",""));
  }
  //查询条件的数据进行回显
  model.addAttribute("houseCondition",houseCondition);

  //返回到list页面
  return "list";

 }



}
