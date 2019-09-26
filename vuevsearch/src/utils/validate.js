//验证手机号
export function isPhone(rule, value, callback) {
    if (!value) {
        return callback(new Error('输入不可以为空'));
    }
    var pattern = /^1[34578]\d{9}$/;
    if(pattern.test(value)){
        return callback()
    }
    return callback(new Error('输入的手机号错误'))
}

/* 是否是邮箱*/
export function isEmail(rule, value, callback) {
    if (!value) {
        return callback(new Error('输入不可以为空'));
    }
    var pattern =/^\w+((.\w+)|(-\w+))@[A-Za-z0-9]+((.|-)[A-Za-z0-9]+).[A-Za-z0-9]+$/;
    if(pattern.test(value)){
        return callback()
    }
    return callback(new Error('输入的邮箱错误'))
}