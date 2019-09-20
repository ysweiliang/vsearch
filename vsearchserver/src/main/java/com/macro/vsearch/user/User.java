package com.macro.vsearch.user;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Data
public class User {

    private String id;
    @NotBlank(message = "姓名不能为空")
    private String name;
    @NotBlank(message = "密码不能为空")
    private String password;
    @Email
    private String email;
    @Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$", message = "手机号码格式错误")
    private String phone;
}