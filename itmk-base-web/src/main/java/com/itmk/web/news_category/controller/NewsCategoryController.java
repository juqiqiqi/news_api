package com.itmk.web.news_category.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itmk.status.ResultUtils;
import com.itmk.utils.ResultVo;
import com.itmk.web.news_category.entity.ListParm;
import com.itmk.web.news_category.entity.NewsCategory;
import com.itmk.web.news_category.entity.SelectType;
import com.itmk.web.news_category.service.NewsCategoryService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hc/category")
public class NewsCategoryController {
    @Autowired
    private NewsCategoryService newsCategoryService;


    //新增
    @PostMapping
    public ResultVo add(@RequestBody NewsCategory newsCategory){
        if (newsCategoryService.save(newsCategory)){
            return ResultUtils.success("新增成功！");
        }
        return ResultUtils.error("新增失败！");
    }

    //编辑
    @PutMapping
    public ResultVo edit(@RequestBody NewsCategory newsCategory){
        if (newsCategoryService.updateById(newsCategory)){
            return ResultUtils.success("编辑成功！");
        }
        return ResultUtils.error("编辑失败！");
    }

    //删除
    @DeleteMapping("/{id}")
    public ResultVo delete(@PathVariable("id") Long id){
        if (newsCategoryService.removeById(id)){
            return ResultUtils.success("删除成功！");
        }
        return ResultUtils.error("删除失败！");
    }

    //列表
    @GetMapping("/list")
    public ResultVo list(ListParm parm){
        //构造分页对象
        IPage<NewsCategory> page=new Page<>(parm.getCurrentPage(),parm.getPageSize());
        //构造查询条件
        QueryWrapper<NewsCategory> query=new QueryWrapper<>();
        query.lambda().like(StringUtils.isNotEmpty(parm.getName()),NewsCategory::getName,parm.getName())
                .orderByAsc(NewsCategory::getId);
        //查询数据
        IPage<NewsCategory> list=newsCategoryService.page(page,query);
        return ResultUtils.success("查询成功",list);
    }

    //小程序分类
    @GetMapping("/getSelectList")
    public ResultVo getSelectList(){
        //查询分类列表
        QueryWrapper<NewsCategory> query=new QueryWrapper<>();
        query.lambda().orderByAsc(NewsCategory::getNumber);
        List<NewsCategory> list=newsCategoryService.list(query);
        //构造小程序需要的类型
        List<SelectType> selectTypes=new ArrayList<>();
        Optional.ofNullable(list).orElse(new ArrayList<>())
                .stream()
                .forEach(item->{
                    SelectType type=new SelectType();
                    type.setLabel(item.getName());
                    type.setValue(item.getId());
                    selectTypes.add(type);
                });
        return ResultUtils.success("查询成功",selectTypes);
    }
}
