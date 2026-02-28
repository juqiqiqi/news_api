package com.itmk.web.news.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itmk.status.ResultUtils;
import com.itmk.utils.ResultVo;
import com.itmk.web.news.entity.News;
import com.itmk.web.news.entity.NewsListParm;
import com.itmk.web.news.entity.WxIndexParm;
import com.itmk.web.news.service.NewsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hc/news")
public class NewsController {
    @Autowired
    private NewsService newsService;
    //新增
    @PostMapping
    public ResultVo add(@RequestBody News news){
        if (newsService.save(news)){
            return ResultUtils.success("新增成功！");
        }
        return ResultUtils.error("新增失败！");
    }
    //编辑
    @PutMapping
    public ResultVo edit(@RequestBody News news){
        if (newsService.updateById(news)){
            return ResultUtils.success("编辑成功！");
        }
        return ResultUtils.error("编辑失败！");
    }
    //删除
    @DeleteMapping("/{id}")
    public ResultVo delete(@PathVariable("id") long id){
        if (newsService.removeById(id)){
            return ResultUtils.success("删除成功！");
        }
        return ResultUtils.error("删除失败！");
    }
    //列表
    @GetMapping("/list")
    public ResultVo getList(NewsListParm parm) {
        //构造分页对象
        IPage<News> page=new Page<>(parm.getCurrentPage(),parm.getPageSize());
        //构造查询条件
        QueryWrapper<News> query=new QueryWrapper<>();
        query.lambda().like(StringUtils.isNotEmpty(parm.getContent()),News::getContent,parm.getContent())
                .orderByDesc(News::getTime);
        IPage<News> list=newsService.page(page,query);
        return ResultUtils.success("查询成功",list);
    }
    //小程序首页列表查询
    @GetMapping("/getIndexList")
    public ResultVo getIndexList(WxIndexParm parm) {
        //构造查询条件
        QueryWrapper<News> query=new QueryWrapper<>();
        query.lambda().like(StringUtils.isNotEmpty(parm.getKeyword()),News::getContent,parm.getKeyword())
                .orderByDesc(News::getTime);
        //构造分页对象
        IPage<News> page=new Page<>(parm.getCurrentPage(),parm.getPageSize());
        IPage<News> list=newsService.page(page,query);
        return ResultUtils.success("查询成功",list);
    }
    //小程序推荐列表查询
    @GetMapping("/recommend")
    public ResultVo recommend(WxIndexParm parm) {
        //构造查询条件
        QueryWrapper<News> query=new QueryWrapper<>();
        query.lambda().like(StringUtils.isNotEmpty(parm.getKeyword()),News::getContent,parm.getKeyword())
                .eq(News::getStatus,"0")
                .orderByDesc(News::getTime);
        //构造分页对象
        IPage<News> page=new Page<>(parm.getCurrentPage(),parm.getPageSize());
        IPage<News> list=newsService.page(page,query);
        return ResultUtils.success("查询成功",list);
    }
}
