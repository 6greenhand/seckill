package com.example.myseckill.vo;

import com.example.myseckill.Validator.IsMobile;
import com.sun.istack.internal.NotNull;
import lombok.Data;

@Data
public class LoginVo {
    @NotNull
    @IsMobile
    private String mobile;

    @NotNull
    private String password;
}
