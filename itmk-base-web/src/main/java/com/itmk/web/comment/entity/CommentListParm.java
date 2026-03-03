package com.itmk.web.comment.entity;

import lombok.Data;

@Data
public class CommentListParm {
    private Long currentPage;
    private Long pageSize;
    private String content;
}
