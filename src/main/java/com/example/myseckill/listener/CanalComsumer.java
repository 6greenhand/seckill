package com.example.myseckill.listener;

import com.alibaba.fastjson.JSONObject;
import com.example.myseckill.canalVo.CanalBean;
import com.example.myseckill.pojo.TGoods;
import com.example.myseckill.vo.GoodsVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.BeanUtils;
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
    RedisTemplate redisTemplate;
        @RabbitHandler
        @RabbitListener(bindings = {@QueueBinding(value = @Queue(value = "canal_queue"), exchange = @Exchange(value = "canal_exchange01"), key = "canal_key")})
        public void process(String message) throws JsonProcessingException {
            // 创建 ObjectMapper 实例
            ObjectMapper objectMapper = new ObjectMapper();
            CanalBean canalBean = JSONObject.parseObject(message, CanalBean.class);

            GoodsVo goodsVo = new GoodsVo();
            List<TGoods> data = canalBean.getData();
            if (canalBean.getType().equals("INSERT")&&canalBean.getTable().equals("t_seckill_goods")) {
                //不是ddl语句
                for (TGoods data01:data) {
                    BeanUtils.copyProperties(data01,goodsVo);
                    redisTemplate.opsForList().rightPush("goods:",goodsVo);
                }
            }else if (canalBean.getType().equals("DELETE")&&canalBean.getTable().equals("t_seckill_goods")){
                for (TGoods data01:data) {
                    BeanUtils.copyProperties(data01,goodsVo);
                    redisTemplate.opsForList().remove("goods:",0,goodsVo);
                }
            }else if(canalBean.getType().equals("UPDATE")&&canalBean.getTable().equals("t_seckill_goods")){
                String old = canalBean.getOld();
                GoodsVo goodsVo1 = goodsVo;
                old = old.replace("\"", "");
                old = old.replace("{", "");
                old = old.replace("}", "");
                String[] split = old.split(":");
                if(split[0].equals("seckill_price")){
                    goodsVo1.setSeckillPrice(BigDecimal.valueOf(Integer.valueOf(split[1])));
                }
                if(split[0].equals("stock_count")){
                    goodsVo1.setStockCount(Integer.valueOf(split[1]));
                }
                if(split[0].equals("start_date")){
                    goodsVo1.setStartDate(Date.valueOf(split[1]));
                }
                if(split[0].equals("end_date")){
                    goodsVo1.setEndDate(Date.valueOf(split[1]));
                }
            }
            System.out.println("++++++++++++++++++++++");
            System.out.println("testMessage = " + message);
        }



}