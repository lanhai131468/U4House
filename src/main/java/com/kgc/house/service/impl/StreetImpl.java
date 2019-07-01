package com.kgc.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Street;
import com.kgc.house.entity.StreetExample;
import com.kgc.house.mapper.StreetMapper;
import com.kgc.house.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StreetImpl implements StreetService {

 //注入dao
 @Autowired
 private StreetMapper streetMapper;

 //分页查询街道
 @Override
 public PageInfo<Street> getStreetAll(Integer page,Integer rows,Integer id) {

  //开启分页插件支持
  PageHelper.startPage(page,rows);

  //实体类
  StreetExample streetExample = new StreetExample();
  StreetExample.Criteria criteria = streetExample.createCriteria();
  //添加条件
  criteria.andDistrictIdEqualTo(id);

  //调用dao 获取数据
  List<Street> streetList = streetMapper.selectByExample(streetExample);

  //创建pageInfo对象
  PageInfo<Street> streetPageInfo = new PageInfo<Street>(streetList);

  return streetPageInfo;

 }

 //删除单条街道的数据()
 @Override
 public int deleteById(Integer id) {
  return streetMapper.deleteById(id);
 }


 //获取所有街道
 @Override
 public List<Street> getStreet(Integer did) {

  //创建条件类
  StreetExample streetExample = new StreetExample();
  //添加条件
  StreetExample.Criteria criteria = streetExample.createCriteria();
  criteria.andDistrictIdEqualTo(did);

  //调用dao查询
  List<Street> streets = streetMapper.selectByExample(streetExample);

  return streets;

 }
}
