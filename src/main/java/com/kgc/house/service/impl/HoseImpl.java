package com.kgc.house.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.House;
import com.kgc.house.entity.HouseCondition;
import com.kgc.house.entity.HouseExt;
import com.kgc.house.mapper.HouseMapper;
import com.kgc.house.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoseImpl implements HouseService {

 //注入dao
 @Autowired
 private HouseMapper houseMapper;

 //添加房屋发布信息
 @Override
 public int insertSelective(House record) {

  int i = houseMapper.insertSelective(record);

  return i;
 }

 //查询显示在管理页面的信息
 @Override
 public  PageInfo<HouseExt> selectHouseById(Integer page,Integer rows,Integer uid) {

  //开启分页插件支持
  PageHelper.startPage(page,rows);

  //插件修改sql语句
  List<HouseExt> houseExts = houseMapper.selectHouseById(uid);

  //创建pageInfo对象
  PageInfo<HouseExt> houseExtPageInfo = new PageInfo<>(houseExts);

  return houseExtPageInfo;
 }


 //修改页面的房屋信息
 @Override
 public HouseExt getHouse(String huoseid) {

  HouseExt house = houseMapper.getHouse(huoseid);

  return house;
 }

 //发布已修改的房屋信息
 @Override
 public int upfabu(House house) {

  int i = houseMapper.updateByPrimaryKeySelective(house);
  return i;
 }


 //删除房屋信息：修改isdel的值将0改为1，则视为删除
 @Override
 public int delHouse(String hid) {

  House house = new House();

  //设置house的id
  house.setId(hid);

  //设置isdel的值为1
  house.setIsdel(new Integer(1));

  //持久化操作
  int i = houseMapper.updateByPrimaryKeySelective(house);

  return i;
 }

 //查询未审核的房屋信息
 @Override
 public PageInfo<HouseExt> getHouseByState(Integer page, Integer rows, Integer ispass) {

  //开启分页插件支持
  PageHelper.startPage(page,rows);

  //调用dao查询未审核的房屋信息
  List<HouseExt> houseExtList = houseMapper.getHouseByState(ispass);

  //创建pageinfo对象
  PageInfo<HouseExt> houseExtPageInfo = new PageInfo<>(houseExtList);

  //返回分页对象
  return houseExtPageInfo;
 }


 //审核房屋信息：将ispass的值修改为1 则视为审核通过
 @Override
 public int passHouse(String hid) {

  //创建房屋信息实体类
  House house = new House();

  //设置主键
  house.setId(hid);

  //设置ispass为1通过审核
  house.setIspass(1);

  //调用dao层
  int i = houseMapper.updateByPrimaryKeySelective(house);
  return i;

 }

 //查询已审核的房屋信息并且实现分页功能
 @Override
 public PageInfo<HouseExt> getHouseYesPass(Integer page, Integer rows, Integer ispass) {

  //开启分页插件支持
  PageHelper.startPage(page,rows);

  //调用dao查询未审核的房屋信息
  List<HouseExt> houseExtList = houseMapper.getHouseByState(ispass);

  //创建pageinfo对象
  PageInfo<HouseExt> houseExtPageInfo = new PageInfo<>(houseExtList);

  return houseExtPageInfo;
 }

 //查询审核通过的所有房屋信息
 @Override
 public PageInfo<HouseExt> getHouseByBrowser(HouseCondition houseCondition) {
  //开启分页插件支持
  PageHelper.startPage(houseCondition.getPage(),houseCondition.getPageSize());

  //如果标题不为空就给标题加入占位符
  if (houseCondition.getTitle()!=null) {
   houseCondition.setTitle("%" + houseCondition.getTitle() + "%");
  }

  //调用dao层
  List<HouseExt> houseByBrowser = houseMapper.getHouseByBrowser(houseCondition);
  //创建pageinfo对象
  PageInfo<HouseExt> houseExtPageInfo = new PageInfo<>(houseByBrowser);

  return houseExtPageInfo;
 }
}
