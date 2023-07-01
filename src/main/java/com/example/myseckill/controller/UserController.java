package com.example.myseckill.controller;

import com.example.myseckill.pojo.TUser;
import com.example.myseckill.vo.RespBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    /**
     * 获取当前登录用户信息(测试)
     * @param user
     * @return
     */
    @RequestMapping("/info")
    public RespBean getUserInfo(TUser user){
        return RespBean.success(user);
    }
}
