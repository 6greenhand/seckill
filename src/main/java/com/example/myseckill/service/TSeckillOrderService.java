package com.example.myseckill.service;

import com.example.myseckill.pojo.TOrder;
import com.example.myseckill.pojo.TSeckillOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.myseckill.pojo.TUser;

/**
* @author admin
* @description 针对表【t_seckill_order】的数据库操作Service
* @createDate 2023-06-20 22:38:48
*/
public interface TSeckillOrderService extends IService<TSeckillOrder> {

    /**
     * 秒杀商品
     *
     * @param user
     * @param goodsId
     * @return
     */
    TOrder doSeckill(TUser user, long goodsId);
}
