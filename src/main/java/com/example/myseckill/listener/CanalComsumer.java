package com.example.myseckill.listener;

import com.alibaba.fastjson.JSONObject;
import com.example.myseckill.canalVo.CanalBean;
import com.example.myseckill.mapper.TGoodsMapper;
import com.example.myseckill.pojo.TGoods;
import com.example.myseckill.pojo.TSeckillGoods;
import com.example.myseckill.vo.GoodsVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * Canal消息消费者
 */
@Component
public class CanalComsumer {

    @Resource
    TGoodsMapper tGoodsMapper;

    @Resource
    RedisTemplate redisTemplate;
        @RabbitHandler
        @RabbitListener(bindings = {@QueueBinding(value = @Queue(value = "canal_queue"), exchange = @Exchange(value = "canal_exchange01"), key = "canal_key")})
        public void process(String message) throws JsonProcessingException {
            // 创建 ObjectMapper 实例
            ObjectMapper objectMapper = new ObjectMapper();
            CanalBean canalBean = JSONObject.parseObject(message, CanalBean.class);

//            GoodsVo goodsVo = new GoodsVo();
            List<TSeckillGoods> data = canalBean.getData();
            if (canalBean.getType().equals("INSERT")&&canalBean.getTable().equals("t_seckill_goods")) {
                //不是ddl语句
                redisTemplate.delete("goods:");
            }else if (canalBean.getType().equals("DELETE")&&canalBean.getTable().equals("t_seckill_goods")){
                redisTemplate.delete("goods:");
            }else if(canalBean.getType().equals("UPDATE")&&canalBean.getTable().equals("t_seckill_goods")) {
              redisTemplate.delete("goods:");
            }
        }



}