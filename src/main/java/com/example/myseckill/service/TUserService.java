package com.example.myseckill.service;

import com.example.myseckill.pojo.TUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.myseckill.vo.LoginVo;
import com.example.myseckill.vo.RespBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* @author admin
* @description 针对表【t_user】的数据库操作Service
* @createDate 2023-06-19 21:13:12
*/
public interface TUserService extends IService<TUser> {

    RespBean doLogin(LoginVo loginvo, HttpServletRequest request, HttpServletResponse response);
}
