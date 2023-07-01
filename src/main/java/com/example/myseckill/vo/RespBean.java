package com.example.myseckill.vo;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespBean {
    private long code;
    private String message;
    private Object obj;
    /**
     * 成功返回结果
     */
    public static RespBean success(){
        return new RespBean(RespBeanEnum.SUCCESS.getCode(),RespBeanEnum.SUCCESS.getMessage(),null);
    }

    public static RespBean success(Object obj){
        return new RespBean(RespBeanEnum.SUCCESS.getCode(),RespBeanEnum.SUCCESS.getMessage(),obj);
    }

    /**
     * 失败返回结果
     * @return
     */
    public static RespBean error(){
        return new RespBean(RespBeanEnum.ERROR.getCode(),RespBeanEnum.ERROR.getMessage(),null);
    }

    public static RespBean error(Object obj){
        return new RespBean(RespBeanEnum.ERROR.getCode(),RespBeanEnum.ERROR.getMessage(),obj);
    }
    public static RespBean login_error(){
        return new RespBean(RespBeanEnum.LOGIN_ERROR.getCode(),RespBeanEnum.LOGIN_ERROR.getMessage(),null);
    }
    public static RespBean login_error(Object obj){
        return new RespBean(RespBeanEnum.LOGIN_ERROR.getCode(),RespBeanEnum.LOGIN_ERROR.getMessage(),obj);
    }
    public static RespBean mobile_error(){
        return new RespBean(RespBeanEnum.MOBILE_ERROR.getCode(),RespBeanEnum.MOBILE_ERROR.getMessage(),null);
    }

    public static RespBean mobile_error(Object obj)
    {
        return new RespBean(RespBeanEnum.MOBILE_ERROR.getCode(),RespBeanEnum.MOBILE_ERROR.getMessage(),obj);
    }
}
