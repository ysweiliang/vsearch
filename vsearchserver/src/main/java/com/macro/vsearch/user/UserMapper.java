package com.macro.vsearch.user;


import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

	List<User> getUserList();

	String userRegister(User user);
}