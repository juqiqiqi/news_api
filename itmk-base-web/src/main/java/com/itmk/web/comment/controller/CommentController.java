package com.itmk.web.comment.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itmk.status.ResultUtils;
import com.itmk.utils.ResultVo;
import com.itmk.web.comment.entity.Comment;
import com.itmk.web.comment.entity.CommentListParm;
import com.itmk.web.comment.service.CommentService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hc/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    //新增
    @PostMapping
    public ResultVo add(@RequestBody Comment comment){
        if(commentService.save(comment)){
            return ResultUtils.success("新增成功！");
        }
        return ResultUtils.error("新增失败！");
    }
    //编辑
    @PutMapping
    public ResultVo edit(@RequestBody Comment comment){
        if(commentService.updateById(comment)){
            return ResultUtils.success("编辑成功！");
        }
        return ResultUtils.error("编辑失败！");
    }
    //删除
    @DeleteMapping("/{id}")
    public ResultVo delete(@PathVariable("id") long id){
        if(commentService.removeById(id)){
            return ResultUtils.success("删除成功！");
        }
        return ResultUtils.error("删除失败！");
    }
    //列表
    @GetMapping("/list")
    public ResultVo getlist(CommentListParm parm){
        //构造分页对象
        IPage<Comment> page=new Page<>(parm.getCurrentPage(),parm.getPageSize());
        //构造查询条件
        QueryWrapper<Comment> query=new QueryWrapper<>();
        query.lambda().like(StringUtils.isNotEmpty(parm.getContent()), Comment::getContent,parm.getContent())
                .orderByDesc(Comment::getCreateTime);
        IPage<Comment> list=commentService.page(page,query);
        return ResultUtils.success("查询成功！",list);
    }
}
