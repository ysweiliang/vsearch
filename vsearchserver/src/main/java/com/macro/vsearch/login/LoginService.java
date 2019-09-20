package com.macro.vsearch.login;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.macro.vsearch.user.User;
import org.springframework.stereotype.Service;

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
        List<User> list = loginMapper.validateUser(user);
        if (list.isEmpty()) {
            JSONObject resultObj = new JSONObject();
            resultObj.put("msg", "用户不存在");
            resultObj.put("resultcode", 0);
            return resultObj;
        } else {
            return (JSONObject) JSON.toJSON(list.get(0));
        }
    }
}
