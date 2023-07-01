package com.example.myseckill.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;

public class MD5Util {

    private static String salt = "1a2b3c4d";

    //md5加密
    public static String md5(String password){
        return DigestUtils.md5Hex(password);
    }

    //第一次加密(用工具类的盐加密)
    public static String md5_first(String password){
        //给原密码加点盐
        String str = ""+salt.charAt(0)+salt.charAt(2) + password +salt.charAt(5) +
                salt.charAt(4);
        //返回加密结果
        return md5(str);
    }

    //第二次加密(用传来的盐加密)
    public static String md5_second(String password,String salt){
        String str = ""+salt.charAt(0)+salt.charAt(2) + password +salt.charAt(5) +
                salt.charAt(4);
        return md5(str);
    }

    public static String inputPassToDbPass(String password,String salt){
        String s = md5_first(password);
        String s1 = md5_second(s, salt);
        return s1;
    }

    public static void main(String[] args) {
        System.out.println(inputPassToDbPass("123456", "1a2b3c4d"));
    }

}
