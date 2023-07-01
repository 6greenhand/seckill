package com.example.myseckill.service;

import com.example.myseckill.pojo.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.myseckill.pojo.TUser;

/**
* @author admin
* @description 针对表【t_order】的数据库操作Service
* @createDate 2023-06-20 22:38:48
*/
public interface TOrderService extends IService<TOrder> {

    public void secKill(TUser user,Long goodsId);
}
