package com.macro.vsearch.service;

import com.macro.vsearch.domain.User;
import com.macro.vsearch.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yuans
 * @create 2019-07-18-22:29
 */
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public List<User> getUserList() {
        return userMapper.getUserList();
    }
}
