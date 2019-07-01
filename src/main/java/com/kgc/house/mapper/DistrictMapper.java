package com.kgc.house.mapper;

import com.kgc.house.entity.District;
import com.kgc.house.entity.DistrictExample;
import java.util.List;

public interface DistrictMapper {

    //删除单条数据(同时删除区域和街道)
    int deleteByPrimaryKey(Integer id);

    int insert(District record);

    //添加数据
    int insertSelective(District record);

    //查询全部
    List<District> selectByExample(DistrictExample example);

    District selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(District record);

    //修改数据
    int updateByPrimaryKey(District record);

    //批量删除数据
    int deleteAll(Integer[] ids);
}