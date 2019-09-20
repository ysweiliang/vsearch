package com.macro.vsearch.user;

import com.alibaba.fastjson.JSONObject;
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

    List<User> getUserList() {
        return userMapper.getUserList();
    }

    JSONObject userRegister(User user) {
        JSONObject resultObj = new JSONObject();
        String id = userMapper.userRegister(user);
        resultObj.put("id", id);
        return resultObj;
    }
}
