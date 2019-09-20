package com.macro.vsearch.user;


import com.alibaba.fastjson.JSONObject;
import com.macro.vsearch.util.SpringValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yuans
 * @create 2019-07-18-21:37
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public List<User> getUserList() {
        return userService.getUserList();
    }

    /**
     * 用户注册
     *
     * @param user          用户实体
     * @param bindingResult 用户信息校验结果
     * @return
     */
    @PostMapping(value = "/user/register")
    public JSONObject userRegister(@RequestBody @Validated User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return SpringValidateUtil.validateResult(bindingResult);
        }
        return userService.userRegister(user);
    }
}
