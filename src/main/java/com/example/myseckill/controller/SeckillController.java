package com.example.myseckill.controller;

import com.example.myseckill.pojo.TUser;

import com.example.myseckill.rabbitmq.MQSender;
import com.example.myseckill.service.TGoodsService;
import com.example.myseckill.service.TSeckillOrderService;
import com.example.myseckill.utils.CookieUtil;

import com.example.myseckill.utils.JsonUtil;
import com.example.myseckill.vo.GoodsVo;

import com.example.myseckill.vo.RespBean;
import com.example.myseckill.vo.RespBeanEnum;
import com.example.myseckill.vo.SeckillMessage;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/seckill")
public class SeckillController {

    @Resource
    TGoodsService goodsService;

    @Resource
    TSeckillOrderService seckillOrderService;
    @Resource
    RedisTemplate redisTemplate;

    @Resource
    MQSender mqSender;


    @RateLimiter(name = "backendA")
    @RequestMapping("/doSeckill")
    public String doSeckill(Model model, HttpServletRequest request, long goodsId){
        System.out.println(new Date());
        System.out.println(getClass());
        TUser user = (TUser) redisTemplate.opsForValue().get("user:" + CookieUtil.getCookieValue(request, "userTicket"));
        //如果用户为空则跳转到登陆页面
        if(user==null){
            return "login";
        }

        model.addAttribute("user",user);
        GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(goodsId);


        //判断库存
        if(goodsVo.getStockCount()<1){
            model.addAttribute("errmsg", RespBeanEnum.EMPTY_STOCK.getMessage());

            return "seckillFail";
        }


            SeckillMessage seckillMessag = new SeckillMessage(user, goodsId);
            mqSender.sendSeckillMessage(JsonUtil.object2JsonStr(seckillMessag));



        model.addAttribute("goods",goodsVo);
        return "goodsDetail";
    }
}
