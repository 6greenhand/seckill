package com.example.myseckill.vo;

import com.example.myseckill.pojo.TGoods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Date;
/**
* 商品返回对象
*
* @author zhoubin
* @since 1.0.0
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsVo extends TGoods {
private BigDecimal seckillPrice;
private Integer stockCount;
private Date startDate;
private Date endDate;
}
