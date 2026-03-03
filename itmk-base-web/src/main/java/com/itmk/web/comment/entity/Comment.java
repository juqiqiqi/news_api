package com.itmk.web.comment.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@TableName("comment")
public class Comment {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long pid;
    private Long user;
    private Long news;
    private String image;
    private String content;
    private Long rating;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField("create_time")
    private Date createTime;
    private String status;
}
