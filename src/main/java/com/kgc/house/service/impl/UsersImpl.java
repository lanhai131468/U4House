package com.kgc.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.house.entity.UserCondition;
import com.kgc.house.entity.Users;
import com.kgc.house.entity.UsersExample;
import com.kgc.house.mapper.UsersMapper;
import com.kgc.house.service.UsersService;
import com.kgc.house.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersImpl implements UsersService {

 //注入Dao
 @Autowired
 private UsersMapper usersMapper;

 //查询
 @Override
 public PageInfo<Users> getUsersAll(UserCondition condition) {

  //开启分页插件支持
  PageHelper.startPage(condition.getPage(),condition.getRows());

  //添加条件
  UsersExample example = new UsersExample();
  UsersExample.Criteria criteria = example.createCriteria();

   //添加只显示管理员的条件
  criteria.andIsadminEqualTo(new Integer(1));

  //添加其他查询条件

  //添加电话条件
  if (condition.getTel()!=null){

   criteria.andTelephoneLike("%"+condition.getTel()+"%");
  }

  //添加开始年龄的条件
  if (condition.getStartAge()!=null){

   criteria.andAgeGreaterThan(condition.getStartAge());
  }

  //添加结束年龄的条件
  if (condition.getEndAge()!=null){

   criteria.andAgeLessThan(condition.getEndAge());
  }

  //调用dao
  List<Users> users = usersMapper.selectByExample(example);

  //创建分页对象
  PageInfo<Users> usersPageInfo = new PageInfo<>(users);

  return usersPageInfo;
 }

 //查询用户名是否存在
 @Override
 public int gerUser(String username) {

  //创建添加条件类对象
  UsersExample usersExample = new UsersExample();
  UsersExample.Criteria criteria = usersExample.createCriteria();
  //添加条件
  criteria.andNameEqualTo(username);
  //普通用户为0
  criteria.andIsadminEqualTo(0);

  //调用dao层
  List<Users> users = usersMapper.selectByExample(usersExample);

  return users.size()==0?0:1;
 }

 //添加注册用户
 @Override
 public int addUser(Users users) {

  //设置isadmin的属性值为0,代表是注册的普通用户
  users.setIsadmin(0);

  //使用MD5Utils 对用户的密码进行加密操作
  users.setPassword(MD5Utils.md5Encrypt(users.getPassword()));

  //调用dao进行持久化操作
  int i = usersMapper.insertSelective(users);

  return i;
 }


 //用户登录
 @Override
 public Users login(String name, String password) {

  //创建Example类的对象
  UsersExample usersExample = new UsersExample();

  //添加条件
  UsersExample.Criteria criteria = usersExample.createCriteria();
  criteria.andIsadminEqualTo(0); //普通用户为0
  criteria.andNameEqualTo(name);
  //密码加密
  criteria.andPasswordEqualTo(MD5Utils.md5Encrypt(password));

  //调用dao
  List<Users> users = usersMapper.selectByExample(usersExample);
  //判断集合的长度
  if (users.size()==1){

   //返回获取到的用户对象
   return users.get(0);
  }

  return null;
 }
}
