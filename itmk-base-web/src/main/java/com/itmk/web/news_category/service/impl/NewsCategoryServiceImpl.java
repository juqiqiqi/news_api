package com.itmk.web.news_category.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itmk.web.news_category.entity.NewsCategory;
import com.itmk.web.news_category.mapper.NewsCategoryMapper;
import com.itmk.web.news_category.service.NewsCategoryService;
import org.springframework.stereotype.Service;

@Service
public class NewsCategoryServiceImpl extends ServiceImpl<NewsCategoryMapper, NewsCategory> implements NewsCategoryService {

}
