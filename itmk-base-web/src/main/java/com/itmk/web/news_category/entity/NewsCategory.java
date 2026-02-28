package com.itmk.web.news_category.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("news_category")
public class NewsCategory {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer number;
}
