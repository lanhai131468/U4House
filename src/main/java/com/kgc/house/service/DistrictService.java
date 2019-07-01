package com.kgc.house.service;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.District;

import java.util.List;

public interface DistrictService {

 //分页查询区域
  PageInfo<District> getAllPage(Integer page,Integer rows);

  //添加数据
 int insertSelective(District record);

 //修改数据
 int updateByPrimaryKey(District record);

 //删除单条数据
 int deleteByPrimaryKey(Integer id);

 //批量删除数据
 int deleteAll(Integer[] ids);

 //查询所有的区域
 List<District> getAllDistrict();

}
