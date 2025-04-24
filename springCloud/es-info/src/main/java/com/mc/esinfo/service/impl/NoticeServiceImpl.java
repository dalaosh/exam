package com.mc.esinfo.service.impl;


import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.info.Notice;
import com.mc.escommon.entity.info.Params;
import com.mc.esinfo.mapper.NoticeMapper;
import com.mc.esinfo.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;

    /**
     * 新增
     */
    @Override
    public void add(Notice notice) {
        String time= DateUtil.now();
        notice.setTime(time);
        noticeMapper.insert(notice);
    }

    /**
     * 删除
     */
    @Override
    public void deleteById(Integer id) {
        noticeMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    @Override
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids){
            noticeMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    @Override
    public void updateById(Notice notice) {
        noticeMapper.updateById(notice);
    }

    /**
     * 根据ID查询
     */
    @Override
    public Notice selectById(Integer id) {
        return noticeMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    @Override
    public List<Notice> selectAll(Notice notice) {
        return noticeMapper.selectAll(notice);
    }

    /**
     * 分页查询
     */
    @Override
    public PageInfo<Notice> selectPage(Params params) {
        //进行数据转移
        Notice notice=new Notice();
        if(params.getUserId()!=null){
            notice.setUserId(params.getUserId());//发布者
        }
        if(!Objects.equals(params.getName(), "") && params.getName()!=null){
            notice.setName(params.getName());//公告名称
        }
        if(!Objects.equals(params.getContent(), "") && params.getContent()!=null){
            notice.setContent(params.getContent());//公告内容
        }
        //开启分页查询
        if(params.getPageNum()==null) {
            params.setPageNum(1);
            params.setPageSize(5);
        }
        PageHelper.startPage(params.getPageNum(), params.getPageSize());

        // 接下来的查询会自动按照当前开启的分页设置来查询
        List<Notice> list= noticeMapper.selectAll(notice);
        return PageInfo.of(list);
    }
}
