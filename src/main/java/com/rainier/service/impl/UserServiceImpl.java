package com.rainier.service.impl;

import com.rainier.mapper.ProblemMapper;
import com.rainier.mapper.UserMapper;
import com.rainier.model.Pcuser;
import com.rainier.model.Problem;
import com.rainier.model.Reply;
import com.rainier.service.UserService;
import com.rainier.util.Page;
import com.rainier.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProblemMapper problemMapper;

    @Override
    public Result updateUser(Pcuser pcuser) {
        int i = userMapper.updateUser(pcuser);
        if (i>0){
            return Result.success("修改成功！");
        }else {
            return Result.error("修改失败！");
        }

    }

    @Override
    public Result updateUserPortrait(Pcuser pcuser) {
        int i = userMapper.updateUserPortrait(pcuser);
        if (i>0){
            return Result.success("修改成功！");
        }else {
            return Result.error("修改失败！");
        }
    }

    @Override
    public Result getProblemByUserId(Map map, HttpServletRequest request) {

        /*查询用户登录信息*/
        Pcuser pcuser =(Pcuser) request.getSession().getAttribute("user");
        if (pcuser == null){
            return Result.error("没有用户信息，请先登录再进行操作！");
        }

        Integer userId = pcuser.getId();//用户id
        Integer pageIndex = Integer.parseInt(map.get("pageIndex").toString());//当前页数
        Integer pageSize = Integer.parseInt(map.get("pageSize").toString());//每页条数
        Integer type = Integer.parseInt(map.get("type").toString());//类型
        Page<Problem> page = new Page<Problem>();
        page.setPageIndex(pageIndex);
        page.setPageSize(pageSize);
        page.setTotalRecords(userMapper.getProblemByUserIdCount(userId,type));
        if (type==0){
            page.setList(userMapper.getProblemByUserId0(userId, ((page.getPageIndex() - 1) * page.getPageSize()), page.getPageSize()));
        }else if (type==1){
            page.setList(userMapper.getProblemByUserId1(userId, ((page.getPageIndex() - 1) * page.getPageSize()), page.getPageSize()));
        }else if (type==2){
            page.setList(userMapper.getProblemByUserId2(userId, ((page.getPageIndex() - 1) * page.getPageSize()), page.getPageSize()));
        }else if (type==3){
            page.setList(userMapper.getProblemByUserId3(userId, ((page.getPageIndex() - 1) * page.getPageSize()), page.getPageSize()));
        }else if (type==4){
            page.setList(userMapper.getProblemByUserId4(userId, ((page.getPageIndex() - 1) * page.getPageSize()), page.getPageSize()));
        }

        return Result.success(page);
    }

    @Override
    public Result deleteProblemById(Map map, HttpServletRequest request) {
        List ids = (List) map.get("ids");
        /*根据id删除问题*/
        problemMapper.deleteProblemByIds(ids);
        /*同时删除问题答案表的答案*/
        problemMapper.deleteReplyByProblemIds(ids);
        return Result.success("删除成功！");
    }

    @Override
    public Result getReplyByUserIdAndType(Map map, HttpServletRequest request) {
        /*查询用户登录信息*/
        Pcuser pcuser =(Pcuser) request.getSession().getAttribute("user");
        if (pcuser == null){
            return Result.error("没有用户信息，请先登录再进行操作！");
        }

        Integer userId = pcuser.getId();//用户id
        Integer pageIndex = Integer.parseInt(map.get("pageIndex").toString());//当前页数
        Integer pageSize = Integer.parseInt(map.get("pageSize").toString());//每页条数
        Integer type = Integer.parseInt(map.get("type").toString());//类型
        Page<Reply> page = new Page<Reply>();
        page.setPageIndex(pageIndex);
        page.setPageSize(pageSize);
        page.setTotalRecords(userMapper.getReplyByUserIdCount(userId,type));
        if (type==0){
            page.setList(userMapper.getReplyByUserId0(userId, ((page.getPageIndex() - 1) * page.getPageSize()), page.getPageSize()));
        }else if (type==1){
            page.setList(userMapper.getReplyByUserId1(userId, ((page.getPageIndex() - 1) * page.getPageSize()), page.getPageSize()));
        }else if (type==2){
            page.setList(userMapper.getReplyByUserId2(userId, ((page.getPageIndex() - 1) * page.getPageSize()), page.getPageSize()));
        }

        return Result.success(page);
    }

    @Override
    public Result deleteReplyByIds(Map map) {
        List ids = (List) map.get("ids");
        int num = userMapper.deleteReplyByIds(ids);
        return Result.success("已删除"+num+"条！");
    }

    @Override
    public Result getReplyByAdopt(Map map, HttpServletRequest request) {
        /*查询用户登录信息*/
        Pcuser pcuser =(Pcuser) request.getSession().getAttribute("user");
        if (pcuser == null){
            return Result.error("没有用户信息，请先登录再进行操作！");
        }
        Integer userId = pcuser.getId();//用户id
        Integer pageIndex = Integer.parseInt(map.get("pageIndex").toString());//当前页数
        Integer pageSize = Integer.parseInt(map.get("pageSize").toString());//每页条数
        Page page = new Page();
        page.setPageIndex(pageIndex);
        page.setPageSize(pageSize);
        page.setTotalRecords(userMapper.getReplyByAdoptCount(userId));
        page.setList(userMapper.getReplyByAdopt(userId, ((page.getPageIndex() - 1) * page.getPageSize()), page.getPageSize()));
        return Result.success(page);
    }
}
