package com.kgc.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Type;
import com.kgc.house.entity.TypeExample;
import com.kgc.house.mapper.TypeMapper;
import com.kgc.house.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeImpl implements TypeService {

 //注入dao
 @Autowired
 private TypeMapper typeMapper;

 //分页显示所有类型
 @Override
 public PageInfo<Type> getTypeAll(Integer page, Integer rows) {

  //开启分页插件支持
  PageHelper.startPage(page,rows);

  //实体类
  TypeExample typeExample = new TypeExample();

  //持久化操作
  List<Type> types = typeMapper.selectByExample(typeExample);

  //创建分页对象
  PageInfo<Type> typePageInfo = new PageInfo<>(types);

  return typePageInfo;
 }

 //显示房子的类型
 @Override
 public List<Type> getAllType() {

  return typeMapper.selectByExample(new TypeExample());

 }
}
