package com.example.myseckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.myseckill.pojo.TGoods;
import com.example.myseckill.service.TGoodsService;
import com.example.myseckill.mapper.TGoodsMapper;
import com.example.myseckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author admin
* @description 针对表【t_goods】的数据库操作Service实现
* @createDate 2023-06-19 21:13:12
*/
@Service
public class TGoodsServiceImpl extends ServiceImpl<TGoodsMapper, TGoods>
    implements TGoodsService {

        @Resource
        private TGoodsMapper goodsMapper;

        /**
         * 获取商品列表
         *
         * @return
         */
        @Override
        public List<GoodsVo> findGoodsVo() {
            return goodsMapper.findGoodsVo();
        }

        /**
         * 根据商品id获取商品详情
         * @param goodsId
         * @return
         */
        @Override
        public GoodsVo findGoodsVoByGoodsId(Long goodsId) {
                return goodsMapper.findGoodsVoByGoodsId(goodsId);
        }



}



