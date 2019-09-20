package com.macro.vsearch.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * @author yuans
 * @create 2019-09-21-0:33
 */
public class SpringValidateUtil {

    /**
     * spring框架校验入参
     *
     * @param bindingResult 实体类校验结果
     * @return resultcode:0
     */
    public static JSONObject validateResult(BindingResult bindingResult) {
        //为了避免大量的校验在前端堆积,影响用户体验，只返回一个错误提示
        JSONObject errorMsg = new JSONObject();
        FieldError error = bindingResult.getFieldErrors().get(0);
        errorMsg.put("msg", error.getDefaultMessage());
        errorMsg.put("resultcode", 0);
        return errorMsg;
    }
}
