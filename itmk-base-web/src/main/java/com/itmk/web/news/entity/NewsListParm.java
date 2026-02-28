package com.itmk.web.news.entity;

import lombok.Data;

@Data
public class NewsListParm {
    private Long currentPage;
    private Long pageSize;
    private String content;
}
