package com.kgc.house.service;


import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.House;
import com.kgc.house.entity.HouseCondition;
import com.kgc.house.entity.HouseExt;

public interface HouseService {

 //添加房屋发布信息
 int insertSelective(House record);

 //查询显示在管理页面的信息
 PageInfo<HouseExt> selectHouseById(Integer page, Integer rows, Integer uid);

 //获取要修改的页面显示
 HouseExt getHouse(String huoseid);

 //发布已修改的房屋信息
 int upfabu(House house);

 //删除房屋信息：修改isdel的值将0改为1，则视为删除
 int delHouse(String hid);

 //查询未审核的房屋信息并且实现分页功能
 PageInfo<HouseExt> getHouseByState(Integer page,Integer rows,Integer ispass);

 //审核房屋信息：将ispass的值修改为1 则视为审核通过
 int passHouse(String hid);

 //查询已审核的房屋信息并且实现分页功能
 PageInfo<HouseExt> getHouseYesPass(Integer page,Integer rows,Integer ispass);

 //查询审核通过的所有房屋信息
PageInfo<HouseExt> getHouseByBrowser(HouseCondition houseCondition);


}
