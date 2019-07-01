package com.kgc.house.service;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Type;

import java.util.List;

public interface TypeService {

 //分页显示所有类型
 PageInfo<Type> getTypeAll(Integer page,Integer rows);

 //查询所有的类型
 List<Type> getAllType();
}
