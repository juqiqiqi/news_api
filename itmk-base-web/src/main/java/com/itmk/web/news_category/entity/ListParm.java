package com.itmk.web.news_category.entity;

import lombok.Data;

@Data
public class ListParm {
    private Integer currentPage;
    private Integer pageSize;
    private String name;
}
