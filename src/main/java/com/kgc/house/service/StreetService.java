package com.kgc.house.service;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.Street;

import java.util.List;

public interface StreetService {

 //分页查询街道
 PageInfo<Street> getStreetAll(Integer page,Integer rows,Integer id);

//删除单条街道的数据
 int deleteById(Integer id);

 //获取街道
 List<Street> getStreet(Integer did);

}
