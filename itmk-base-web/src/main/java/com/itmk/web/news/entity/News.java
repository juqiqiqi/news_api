package com.itmk.web.news.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@TableName("news")
public class News {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String category;
    private String title;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date time;
    private String image;
    @TableField("viewCount")
    private String viewCount;
    @TableField("likeCount")
    private String likeCount;
    private String status;
}
