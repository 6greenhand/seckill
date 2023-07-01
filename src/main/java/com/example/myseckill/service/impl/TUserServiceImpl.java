package com.example.myseckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myseckill.pojo.TUser;
import com.example.myseckill.service.TUserService;
import com.example.myseckill.mapper.TUserMapper;
import com.example.myseckill.utils.CookieUtil;
import com.example.myseckill.utils.MD5Util;
import com.example.myseckill.utils.UUIDUtil;
import com.example.myseckill.utils.ValidatorPhoneUtil;
import com.example.myseckill.vo.LoginVo;
import com.example.myseckill.vo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.concurrent.TimeUnit;

/**
* @author admin
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2023-06-19 21:13:12
*/
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser>
    implements TUserService{
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public RespBean doLogin(LoginVo loginvo, HttpServletRequest request, HttpServletResponse response) {
        //登陆校验
        String mobile = loginvo.getMobile();
        String password = loginvo.getPassword();


        //校验手机号格式
        if(!ValidatorPhoneUtil.validatorPhone(mobile)){
            return RespBean.mobile_error();
        }
        //校验密码
        TUser id = query().eq("id", mobile).one();
        if(id==null){
            return RespBean.login_error();
        }
        String password1 = id.getPassword();
        if(!MD5Util.md5_second(password,id.getSalt()).equals(password1)){
            return RespBean.login_error("密码错误");
        }

        //生成cookie
        String ticket = UUIDUtil.uuid();
        CookieUtil.setCookie(request,response,"userTicket",ticket);
        //把用户信息存储到redis中
        redisTemplate.opsForValue().set("user:"+ticket,id,2, TimeUnit.HOURS);

        return RespBean.success(ticket);
    }
}




