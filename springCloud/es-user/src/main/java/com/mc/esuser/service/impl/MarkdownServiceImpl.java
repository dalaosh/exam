package com.mc.esuser.service.impl;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.markdown.Markdown;
import com.mc.escommon.entity.markdown.Params;
import com.mc.escommon.mdFile.MdFile;
import com.mc.escommon.result.RoleEnum;
import com.mc.esuser.mapper.MarkdownMapper;
import com.mc.esuser.service.MarkdownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author machao
 * * @datetime 2025/1/14 22:07
 * * inform
 */
@Service
public class MarkdownServiceImpl implements MarkdownService {
    @Autowired
    private MarkdownMapper markdownMapper;

    /**
     * 新增
     */
    @Override
    public void add(Params params) {
        Markdown markdown=new Markdown();
        if(!Objects.equals(params.getRole(), "") && params.getRole()!=null){
            markdown.setRole(params.getRole());//角色
        }
        if(!Objects.equals(params.getUserId(), "") && params.getUserId()!=null){
            markdown.setUserId(params.getUserId());//用户id
        }
        if(!Objects.equals(params.getName(), "") && params.getName()!=null){
            markdown.setName(params.getName());//标题
        }
        String content=params.getContent();
        String address= MdFile.setMdFile(content);
        markdown.setAddress(address);
        //1.添加时间
        String time= DateUtil.now();
        markdown.setTime(time);
        //文件

        markdownMapper.insert(markdown);
    }

    /**
     * 删除
     */
    @Override
    public void deleteById(Integer id,String role) {
        Markdown markdown=null;
        // 接下来的查询会自动按照当前开启的分页设置来查询
        if(role.equals(RoleEnum.admin.name())){
            markdown = markdownMapper.selectAdminById(id);
        }
        else if(role.equals(RoleEnum.teacher.name())){
            markdown= markdownMapper.selectTeacherById(id);
        }
        else if(role.equals(RoleEnum.student.name())){
            markdown= markdownMapper.selectStudentById(id);
        }
        markdownMapper.deleteById(id);
        //删除文件
        if (markdown != null) {
            String filepath =markdown.getAddress();
            MdFile.deleteFile(filepath);
        }
    }

    /**
     * 批量删除
     */
    @Override
    public void deleteBatch(List<Integer> ids,String role) {
        for (Integer id : ids){
            Markdown markdown=null;
            // 接下来的查询会自动按照当前开启的分页设置来查询
            if(role.equals(RoleEnum.admin.name())){
                markdown = markdownMapper.selectAdminById(id);
            }
            else if(role.equals(RoleEnum.teacher.name())){
                markdown= markdownMapper.selectTeacherById(id);
            }
            else if(role.equals(RoleEnum.student.name())){
                markdown= markdownMapper.selectStudentById(id);
            }
            markdownMapper.deleteById(id);
            //删除文件
            if (markdown != null) {
                String filepath =markdown.getAddress();
                MdFile.deleteFile(filepath);
            }
        }
    }


    /**
     * 修改
     */
    @Override
    public void updateById(Params params) {
        Markdown markdown=new Markdown();
        if(!Objects.equals(params.getName(), "") && params.getName()!=null){
            markdown.setName(params.getName());//标题
            markdown.setId(params.getId());//id
            markdownMapper.updateById(markdown);
        }
        if(!Objects.equals(params.getContent(), "") && params.getContent()!=null){
            String content=params.getContent();//标题
            String address= params.getAddress();
            MdFile.rewriteFile(address,content);
        }
    }
    /**
     * 查询所有
     */
    @Override
    public List<Markdown> selectAll(Markdown markdown) {
        if(markdown.getRole().equals(RoleEnum.admin.name())){
            return markdownMapper.selectAdminAll(markdown);
        }
        else if(markdown.getRole().equals(RoleEnum.teacher.name())){
            return markdownMapper.selectTeacherAll(markdown);
        }
        else if(markdown.getRole().equals(RoleEnum.student.name())){
            return markdownMapper.selectStudentAll(markdown);
        }
        return null;
    }
    public String selectContent(String address){
        return MdFile.getMdFile(address);
    }

    /**
     * 分页查询
     */
    @Override
    public PageInfo<Markdown> selectPage(Params params) {
        //进行数据转移
        Markdown markdown=new Markdown();
        markdown.setRole(params.getRole());
        markdown.setUserId(params.getUserId());
        if(!Objects.equals(params.getName(), "") &&params.getName()!=null){
            markdown.setName(params.getName());//标题
        }
        //开启分页查询
        if(params.getPageNum()==null) {
            params.setPageNum(1);
            params.setPageSize(5);
        }
        PageHelper.startPage(params.getPageNum(), params.getPageSize());

        List<Markdown> list=null;
        // 接下来的查询会自动按照当前开启的分页设置来查询
        if(markdown.getRole().equals(RoleEnum.admin.name())){
            list= markdownMapper.selectAdminAll(markdown);
        }
        else if(markdown.getRole().equals(RoleEnum.teacher.name())){
            list= markdownMapper.selectTeacherAll(markdown);
        }
        else if(markdown.getRole().equals(RoleEnum.student.name())){
            list= markdownMapper.selectStudentAll(markdown);
        }
        if (list != null) {
            return PageInfo.of(list);
        }
        return null;
    }
}
