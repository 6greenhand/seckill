package com.example.myseckill.mapper;

import com.example.myseckill.pojo.TGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.myseckill.vo.GoodsVo;

import java.util.List;

/**
* @author admin
* @description 针对表【t_goods】的数据库操作Mapper
* @createDate 2023-06-19 21:13:12
* @Entity com.example.myseckill.pojo.TGoods
*/
public interface TGoodsMapper extends BaseMapper<TGoods> {
    /**
     * 获取商品列表
     * @return
     */
    List<GoodsVo> findGoodsVo();

    /**
     * 根据商品id获取商品详情
     * @param goodsId
     * @return
     */
    GoodsVo findGoodsVoByGoodsId(Long goodsId);
}




