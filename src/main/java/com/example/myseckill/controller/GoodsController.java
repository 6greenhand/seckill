package com.example.myseckill.controller;

import com.example.myseckill.pojo.TSeckillGoods;
import com.example.myseckill.pojo.TUser;

import com.example.myseckill.service.TGoodsService;
import com.example.myseckill.utils.CookieUtil;
import com.example.myseckill.vo.GoodsVo;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 login.html
 goodsList.html
 * 商品
 *
 * @author zhoubin
 * @since 1.0.0
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Resource
    RedisTemplate redisTemplate;

    @Resource
    TGoodsService tGoodsService;

    @Resource
    RedissonClient redissonClient;


    /**
     * 跳转登录页
     *
     * @return
     */
    @RequestMapping("/toList")
    public String toLogin(HttpSession session, Model model,@CookieValue("userTicket")String ticket) {

        TUser user = (TUser)redisTemplate.opsForValue().get("user:" + ticket);
        if(user==null){
            return "login";
        }

        List<GoodsVo> list_GoodsVo =null;



        list_GoodsVo = redisTemplate.opsForList().range("goods:",0,-1);
        if(list_GoodsVo.size()<=0){
                //添加到redis
                List<GoodsVo> goodsVo = tGoodsService.findGoodsVo();

                redisTemplate.opsForList().rightPushAll("goods:",goodsVo);
//                redisTemplate.expire("goods:"+ticket,2,TimeUnit.HOURS);
                model.addAttribute("goodsList", goodsVo);
                model.addAttribute("user", user);
                return "goodsList";
        }



        model.addAttribute("goodsList",list_GoodsVo);
        model.addAttribute("user", user);
        return "goodsList";
    }

    /**
     * 跳转商品详情页
     *
     * @param model
     * @param
     * @param goodsId
     * @return
     */
    @RequestMapping("/toDetail/{goodsId}")
    public String toDetail(Model model, HttpServletRequest request, @PathVariable Long goodsId) {
        TUser user = (TUser) redisTemplate.opsForValue().get("user:" + CookieUtil.getCookieValue(request, "userTicket"));
        model.addAttribute("user", user);
        GoodsVo goods = tGoodsService.findGoodsVoByGoodsId(goodsId);
        model.addAttribute("goods", goods);
        Date startDate = goods.getStartDate();
        Date endDate = goods.getEndDate();
        Date nowDate = new Date();
//秒杀状态
        int secKillStatus = 0;
//剩余开始时间
        int remainSeconds = 0;
//秒杀还未开始
        if (nowDate.before(startDate)) {
            remainSeconds = (int) ((startDate.getTime()-nowDate.getTime())/1000);
// 秒杀已结束
        } else if (nowDate.after(endDate)) {
            secKillStatus = 2;
            remainSeconds = -1;
// 秒杀中
        } else {
            secKillStatus = 1;
            remainSeconds = 0;
        }
        model.addAttribute("secKillStatus",secKillStatus);
        model.addAttribute("remainSeconds",remainSeconds);
        return "goodsDetail";
    }


}
