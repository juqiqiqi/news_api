package com.itmk.web.news.entity;

import lombok.Data;

@Data
public class WxIndexParm {
    private Long currentPage;
    private Long pageSize;
    private String keyword;
}
