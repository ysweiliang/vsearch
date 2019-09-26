package com.macro.vsearch.login;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.macro.vsearch.user.User;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yuans
 * @create 2019-09-21-0:09
 */
@Service
public class LoginService {

    @Resource
    private LoginMapper loginMapper;

    /**
     * 用户登陆返回用户信息
     * @param user 用户名 密码
     * @return
     */
    public JSONObject login(User user) {
        //密码md5加密
        String md5Password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5Password);
        List<User> list = loginMapper.validateUser(user);
        JSONObject resultObj = new JSONObject();
        if (list.isEmpty()) {
            resultObj.put("msg", "用户不存在");
            resultObj.put("resultcode", 0);
            return resultObj;
        } else {
            User userInfo = list.get(0);
            resultObj.put("name",userInfo.getName());
            resultObj.put("id",userInfo.getId());
            resultObj.put("phone",userInfo.getPhone());
            resultObj.put("email",userInfo.getEmail());
            return resultObj;
        }
    }
}
