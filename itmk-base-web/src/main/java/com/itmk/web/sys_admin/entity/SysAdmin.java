package com.itmk.web.sys_admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data //自动生成get和set方法
@TableName("admin")
public class SysAdmin {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String phone;
    private String sex;
    private String name;
    private String status;
    private String isAdmin;
}
