package com.macro.vsearch.login;


import com.macro.vsearch.user.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yuans
 * @create 2019-09-21-0:10
 */
public interface LoginMapper {

    List<User> validateUser(@Param("user") User user);
}
