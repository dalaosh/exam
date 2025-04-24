package com.mc.esinfo.service.impl;


import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.mc.escommon.entity.info.News;
import com.mc.escommon.entity.info.Params;
import com.mc.esinfo.mapper.NewsMapper;
import com.mc.esinfo.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsMapper newsMapper;

    /**
     * 新增
     */
    @Override
    public void add(News news) {
        String time= DateUtil.now();
        news.setTime(time);
        newsMapper.insert(news);
    }

    /**
     * 删除
     */
    @Override
    public void deleteById(Integer id) {
        newsMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    @Override
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids){
            newsMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    @Override
    public void updateById(News news) {
        newsMapper.updateById(news);
    }

    /**
     * 根据ID查询
     */
    @Override
    public News selectById(Integer id) {
        return newsMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    @Override
    public List<News> selectAll(News news) {
        return newsMapper.selectAll(news);
    }

    /**
     * 分页查询
     */
    @Override
    public PageInfo<News> selectPage(Params params) {
        //进行数据转移
        News news=new News();
        if(params.getUserId()!=null){
            news.setUserId(params.getUserId());//发布者
        }
        if(!Objects.equals(params.getName(), "") && params.getName()!=null){
            news.setName(params.getName());//新闻名称
        }
        if(!Objects.equals(params.getContent(), "") && params.getContent()!=null){
            news.setContent(params.getContent());//新闻内容
        }
        //开启分页查询
        if(params.getPageNum()==null) {
            params.setPageNum(1);
            params.setPageSize(5);
        }
        PageHelper.startPage(params.getPageNum(), params.getPageSize());

        // 接下来的查询会自动按照当前开启的分页设置来查询
        List<News> list= newsMapper.selectAll(news);
        return PageInfo.of(list);
    }
}
