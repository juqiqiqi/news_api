package com.itmk.web.comment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itmk.web.comment.entity.Comment;
import com.itmk.web.comment.mapper.CommentMapper;
import com.itmk.web.comment.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
}
