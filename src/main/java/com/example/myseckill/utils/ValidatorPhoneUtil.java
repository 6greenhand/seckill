package com.example.myseckill.utils;

import com.example.myseckill.vo.RespBean;

public class ValidatorPhoneUtil {
    public static boolean validatorPhone(String phone){
        //手机号的正则表达式
        if(!phone.matches("^1[3-9]\\d{9}$")){
            //不符合，返回错误信息
            return false;
        }
        return true;
    }
}
