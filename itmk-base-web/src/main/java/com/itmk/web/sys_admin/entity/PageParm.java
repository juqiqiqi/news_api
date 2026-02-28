package com.itmk.web.sys_admin.entity;


import lombok.Data;

@Data
public class PageParm {
    private Long currentPage;
    private Long pageSize;
    private String name;
}
