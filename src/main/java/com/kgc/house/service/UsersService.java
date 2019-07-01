package com.kgc.house.service;

import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.UserCondition;
import com.kgc.house.entity.Users;

public interface UsersService {

 //查询所有包含条件查询
 PageInfo<Users> getUsersAll(UserCondition condition);

 //查询用户名是否存在
 int gerUser(String username);

 //添加注册用户
 int addUser(Users users);

 //用户登录
 Users login(String name,String password);
}
