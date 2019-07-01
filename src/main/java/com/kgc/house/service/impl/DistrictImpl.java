package com.kgc.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.District;
import com.kgc.house.entity.DistrictExample;
import com.kgc.house.mapper.DistrictMapper;
import com.kgc.house.mapper.StreetMapper;
import com.kgc.house.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//业务层
@Service
public class DistrictImpl implements DistrictService {

 //注入区域的dao
 @Autowired
 private DistrictMapper districtMapper;

 //注入街道的dao
 @Autowired
 private StreetMapper streetMapper;

 //分页查询全部
 @Override
 public PageInfo<District> getAllPage(Integer page,Integer rows) {

    //开始分页插件支持
  PageHelper.startPage(page,rows);

  //创建查询条件实体
  DistrictExample example = new DistrictExample();

 /* //获得添加条件对象
  DistrictExample.Criteria criteria = example.createCriteria();

  //添加查询条件
  criteria.andNameLike("%东%");

  //调用dao层 查询全部  分页插件修改sql语句
  List<District> districts = districtMapper.selectByExample(example);*/

 //调用Dao查询所有
  List<District> districts =districtMapper .selectByExample(example);

  //创建分页对象
  PageInfo<District> districtPageInfo = new PageInfo<District>(districts);

  return districtPageInfo;

 }


 //添加数据
 @Override
 public int insertSelective(District record) {

  int i = districtMapper.insertSelective(record);

  return i;

 }

 //修改数据
 @Override
 public int updateByPrimaryKey(District record) {

  return districtMapper.updateByPrimaryKey(record);
 }

 //删除单条数据(同时删除街道和区域)
 @Transactional  //使用事务管理注解  开始事务管理
 public int deleteByPrimaryKey(Integer id) {

  try {

   streetMapper.deleteById(id);
   districtMapper.deleteByPrimaryKey(id);

   return 1;

  }catch (Exception e){

   return 0;

  }

 }

 //批量删除多条
 @Override
 public int deleteAll(Integer[] ids) {
  return districtMapper.deleteAll(ids);
 }


 //显示的区域
 @Override
 public List<District> getAllDistrict() {

  return districtMapper.selectByExample(new DistrictExample());
 }
}
