package com.macro.vsearch.controller;

import com.macro.vsearch.domain.User;
import com.macro.vsearch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
