package com.mc.esinfo.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.info.Log;
import com.mc.escommon.entity.info.Params;
import com.mc.esinfo.mapper.LogMapper;
import com.mc.esinfo.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogMapper logMapper;

    /**
     * 新增
     */
    @Override
    public void add(Log log) {
        logMapper.insert(log);
    }

    /**
     * 删除
     */
    @Override
    public void deleteById(Integer id) {
        logMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    @Override
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids){
            logMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    @Override
    public void updateById(Log log) {
        logMapper.updateById(log);
    }

    /**
     * 根据ID查询
     */
    @Override
    public Log selectById(Integer id) {
        return logMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    @Override
    public List<Log> selectAll(Log log) {
        return logMapper.selectAll(log);
    }

    /**
     * 分页查询
     */
    @Override
    public PageInfo<Log> selectPage(Params params) {
        //进行数据转移
        Log log=new Log();
        if(!Objects.equals(params.getName(), "") && params.getName()!=null){
            log.setName(params.getName());//操作名称
        }
        if(!Objects.equals(params.getUsername(), "") &&params.getUsername()!=null){
            log.setUsername(params.getUsername());//操作人
        }
        if(!Objects.equals(params.getIp(), "") &&params.getIp()!=null){
            log.setIp(params.getIp());//ip
        }
        //开启分页查询
        if(params.getPageNum()==null) {
            params.setPageNum(1);
            params.setPageSize(5);
        }
        PageHelper.startPage(params.getPageNum(), params.getPageSize());

        // 接下来的查询会自动按照当前开启的分页设置来查询
        List<Log> list= logMapper.selectAll(log);
        return PageInfo.of(list);
    }
}
