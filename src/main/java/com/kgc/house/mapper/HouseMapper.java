package com.kgc.house.mapper;

import com.kgc.house.entity.House;
import com.kgc.house.entity.HouseCondition;
import com.kgc.house.entity.HouseExample;
import com.kgc.house.entity.HouseExt;

import java.util.List;

public interface HouseMapper {
    int deleteByPrimaryKey(String id);

    int insert(House record);

    int insertSelective(House record);

    List<House> selectByExample(HouseExample example);

    House selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);

    //查询显示在管理页面的信息
    List<HouseExt> selectHouseById(Integer uid);

    //获取要修改的页面显示
    HouseExt getHouse(String huoseid);

    //查询未审核的房屋信息
    List<HouseExt> getHouseByState(Integer ispass);

    //查询审核通过的所有房屋信息
    List<HouseExt> getHouseByBrowser(HouseCondition houseCondition);


}