package com.itmk.web.user.entity;

import lombok.Data;

@Data
public class LoginVo {
    private Long id;
    private String phone;
    private String name;
    private String username;
    private String college;
}
