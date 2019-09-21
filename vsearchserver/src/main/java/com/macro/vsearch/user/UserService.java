package com.macro.vsearch.user;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

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
        int count = validateNameOnly(user.getName());
        if (count > 0) {
            resultObj.put("msg", "用户已存在");
            resultObj.put("resultcode", 0);
        } else {
            //密码md5加密
            String md5Password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
            user.setPassword(md5Password);
            userMapper.userRegister(user);
            resultObj.put("id", user.getId());
            resultObj.put("resultcode", 1);
        }
        return resultObj;
    }

    /**
     * 校验用户是否存在
     *
     * @param name 用户名
     * @return 存在则返回1
     */
    private int validateNameOnly(String name) {
        return userMapper.validateNameOnly(name);
    }
}
