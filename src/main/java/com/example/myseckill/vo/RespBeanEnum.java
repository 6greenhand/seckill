package com.example.myseckill.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 公共返回对象
 */
@Getter
@ToString
@AllArgsConstructor
public enum RespBeanEnum {
    SUCCESS(200,"SUCCESS"),
    ERROR(500,"服务端异常"),
    LOGIN_ERROR(500210,"手机号码或密码不正确"),
    MOBILE_ERROR(500,"手机号码格式不正确"),
    BIND_ERROR(500212,"参数检验异常"),
    EMPTY_STOCK(500500,"库存不足"),
    REPEATE_ERROR(500501,"该商品每人限购一件");
    private final Integer code;
    private final String message;
}
