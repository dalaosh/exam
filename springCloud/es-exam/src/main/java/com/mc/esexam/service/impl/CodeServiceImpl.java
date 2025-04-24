package com.mc.esexam.service.impl;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.exam.Code;
import com.mc.escommon.entity.exam.Params;
import com.mc.esexam.mapper.CodeMapper;
import com.mc.esexam.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author machao
 * @datetime 2025/2/24 15:43
 * @inform 代码相关业务逻辑实现
 */
@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    private CodeMapper codeMapper;

    /**
     * 新增代码记录
     */
    @Override
    public void add(Code code) {
        // 可以在这里添加一些默认设置，比如状态等
        code.setTime(DateUtil.now());
        codeMapper.insert(code);
    }

    /**
     * 根据 ID 删除代码记录
     */
    @Override
    public void deleteById(Integer id) {
        codeMapper.deleteById(id);
    }

    /**
     * 批量删除代码记录
     */
    @Override
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            codeMapper.deleteById(id);
        }
    }

    /**
     * 修改代码记录
     */
    @Override
    public void updateById(Code code) {
        codeMapper.updateById(code);
    }

    /**
     * 根据 ID 查询代码记录
     */
    @Override
    public Code selectById(Integer id) {
        return codeMapper.selectById(id);
    }

    /**
     * 查询所有代码记录
     */
    @Override
    public List<Code> selectAll(Code code) {
        return codeMapper.selectAll(code);
    }

    /**
     * 分页查询代码记录
     */
    @Override
    public PageInfo<Code> selectPage(Params params) {
        // 进行数据转移
        Code code = new Code();
        if (params.getId() != null) {
            code.setId(params.getId()); // ID
        }
        if (params.getStudentId() != null) {
            code.setStudentId(params.getStudentId()); // 学生 ID
        }
        if (!Objects.equals(params.getTitle(), "") && params.getTitle() != null) {
            code.setTitle(params.getTitle()); // 问题
        }
        if (!Objects.equals(params.getType(), "") && params.getType() != null) {
            code.setType(params.getType()); // 代码状态
        }

        // 开启分页查询
        if (params.getPageNum() == null) {
            params.setPageNum(1);
            params.setPageSize(5);
        }
        PageHelper.startPage(params.getPageNum(), params.getPageSize());

        // 接下来的查询会自动按照当前开启的分页设置来查询
        List<Code> list = codeMapper.selectAll(code);
        return PageInfo.of(list);
    }
}