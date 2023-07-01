package com.example.myseckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myseckill.pojo.TOrder;
import com.example.myseckill.pojo.TUser;
import com.example.myseckill.service.TOrderService;
import com.example.myseckill.mapper.TOrderMapper;
import org.springframework.stereotype.Service;

/**
* @author admin
* @description 针对表【t_order】的数据库操作Service实现
* @createDate 2023-06-20 22:38:47
*/
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder>
    implements TOrderService{

    @Override
    public void secKill(TUser user, Long goodsId) {

    }
}




