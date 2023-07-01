package com.example.myseckill.config;

import com.example.myseckill.pojo.TUser;
import com.example.myseckill.utils.CookieUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebHandlerInteceptor implements HandlerInterceptor {
    @Resource
    RedisTemplate redisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ticket = CookieUtil.getCookieValue(request, "userTicket");
        if (StringUtils.isEmpty(ticket)) {
            return false;
        }
//       TUser user = (TUser) session.getAttribute(ticket);
        TUser user = (TUser)redisTemplate.opsForValue().get("user:" + ticket);
        if (null == user) {
            return false;
        }
        return true;
    }
}
