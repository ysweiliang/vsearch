package com.macro.vsearch.user;


import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> getUserList();

    int userRegister(User user);

    int validateNameOnly(String name);
}