package com.macro.vsearch.login;

import com.alibaba.fastjson.JSONObject;
import com.macro.vsearch.user.User;
import com.macro.vsearch.util.SpringValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登陆控制器
 *
 * @author yuans
 * @create 2019-09-21-0:08
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 登陆返回用户信息
     *
     * @param user          用户实体
     * @param bindingResult 用户信息校验结果
     * @return
     */
    @PostMapping(value = "/login")
    public JSONObject login(@RequestBody @Validated User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return SpringValidateUtil.validateResult(bindingResult);
        }
        return loginService.login(user);
    }
}
