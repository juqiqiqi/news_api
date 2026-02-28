package com.itmk.web.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class User {
    @TableId(type = IdType.AUTO)
    private long id;
    private String name;
    private String picture;
    private String phone;
    private String username;
    private String password;
    //0：启用，1：停用
    private String status;
    private String college;
}
