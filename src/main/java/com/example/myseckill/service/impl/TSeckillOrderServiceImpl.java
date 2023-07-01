package com.example.myseckill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myseckill.mapper.TGoodsMapper;
import com.example.myseckill.mapper.TOrderMapper;
import com.example.myseckill.mapper.TSeckillGoodsMapper;
import com.example.myseckill.pojo.*;
import com.example.myseckill.service.TGoodsService;
import com.example.myseckill.service.TSeckillGoodsService;
import com.example.myseckill.service.TSeckillOrderService;
import com.example.myseckill.mapper.TSeckillOrderMapper;
import com.example.myseckill.vo.RespBeanEnum;
import com.example.myseckill.vo.SeckillMessage;
import com.sun.xml.internal.ws.resources.SenderMessages;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
* @author admin
* @description 针对表【t_seckill_order】的数据库操作Service实现
* @createDate 2023-06-20 22:38:48
*/
@Service
public class TSeckillOrderServiceImpl extends ServiceImpl<TSeckillOrderMapper, TSeckillOrder>
    implements TSeckillOrderService{

    @Resource
    private TGoodsMapper goodsMapper;

    @Resource
    private TSeckillOrderMapper seckillOrderMapper;

    @Resource
    private TOrderMapper orderMapper;

    @Resource
    RedissonClient redissonClient;

    @Resource
    TSeckillGoodsMapper seckillGoodsMapper;


    @Override
//    @Transactional
    public TOrder doSeckill(TUser user, long goodsId) {
        RLock lockKey = redissonClient.getLock("lockKey");

        lockKey.lock();

        //首先是减库存
        TSeckillGoods tSeckillGoods = seckillGoodsMapper.selectById(goodsId);
        if(tSeckillGoods==null){
            return null;
        }
        if(tSeckillGoods.getStockCount()<1){

            return null;
        }
        tSeckillGoods.setStockCount(tSeckillGoods.getStockCount()-1);
        seckillGoodsMapper.updateById(tSeckillGoods);
        TGoods tGoods = goodsMapper.selectById(goodsId);
        if(tGoods==null){
            return null;
        }

        tGoods.setGoodsStock(tGoods.getGoodsStock()-1);
        goodsMapper.updateById(tGoods);

        //生成订单
        TOrder tOrder = new TOrder();

        tOrder.setUserId(user.getId());
        tOrder.setGoodsId(goodsId);
        tOrder.setDeliveryAddrId(0L);
        tOrder.setGoodsName(tGoods.getGoodsName());
        tOrder.setGoodsCount(1);
        tOrder.setGoodsPrice(tGoods.getGoodsPrice());
        tOrder.setOrderChannel(1);
        tOrder.setStatus(0);
        tOrder.setCreateDate(new Date());

        orderMapper.insert(tOrder);
        //然后把生成秒杀订单表并返回
        TSeckillOrder tSeckillOrder = new TSeckillOrder();

        tSeckillOrder.setOrderId(tOrder.getId());
        tSeckillOrder.setUserId(user.getId());
        tSeckillOrder.setGoodsId(goodsId);

        seckillOrderMapper.insert(tSeckillOrder);
        lockKey.unlock();
        return tOrder;
    }
}




