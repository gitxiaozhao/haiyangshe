package com.rainier.service.impl;

import com.rainier.mapper.ProblemMapper;
import com.rainier.model.Pcuser;
import com.rainier.model.Problem;
import com.rainier.model.Reply;
import com.rainier.service.ProblemService;
import com.rainier.util.Page;
import com.rainier.util.Result;
import jdk.nashorn.internal.runtime.options.LoggingOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class ProblemServiceImpl implements ProblemService {

    @Autowired
    private ProblemMapper problemMapper;

    @Override
    public Result addProblem(Problem problem, HttpServletRequest request) {
        //得到用户信息
        Pcuser user = (Pcuser) request.getSession().getAttribute("user");
        if (user == null){
            return Result.error("请重新登录！");
        }else{
            /*得到用户id*/
            problem.setUser_id(user.getId());
            /*得到提问时间*/
            problem.setSubmit_time(new Date());
            /*默认为0审核中*/
            problem.setProblem_state(0);
            /*默认点击量为0*/
            problem.setClick(0);

            /*添加问题*/
            int i = problemMapper.addProblem(problem);
            if (i>0){
                return Result.success("提问成功，等待审核！");
            }else {
                return Result.error("提问失败！");
            }

        }
    }

    @Override
    public Result addReply(Reply reply, HttpServletRequest request) {
        //得到用户信息
        Pcuser user = (Pcuser) request.getSession().getAttribute("user");
        if (user == null){
            return Result.error("请重新登录！");
        }else{
            /*得到用户id*/
            reply.setUser_id(user.getId());
            /*得到评论时间*/
            reply.setReply_time(new Date());
            /*默认为0审核中*/
            reply.setReply_state(0);
            /*默认为0未采纳*/
            reply.setAdopt(0);
            int num = problemMapper.addReply(reply);
            if (num>0){
                return Result.success("评论成功，等待审核！");
            }else {
                return Result.error("评论失败！");
            }
        }
    }

    @Override
    public Result getReplyByProblemId(Map map) throws ParseException {
        Integer pageIndex = Integer.parseInt(map.get("pageIndex").toString());//当前页数
        Integer pageSize = Integer.parseInt(map.get("pageSize").toString());//每页条数
        Page page = new Page();
        page.setPageIndex(pageIndex);
        page.setPageSize(pageSize);
        page.setTotalRecords(problemMapper.getReplyByProblemIdCount(map.get("problemId").toString()));
        page.setList(problemMapper.getReplyByProblemId(map.get("problemId").toString(),map.get("ascOrDesc").toString(), ((page.getPageIndex() - 1) * page.getPageSize()), page.getPageSize()));
        return Result.success(page);
    }

    @Override
    public Result updateReplyAdopt(Reply reply){
        /*查看是不是已经有采纳的答案*/
        List list = problemMapper.getReplyByAdopt(reply);
        if (list.size()>0){
            return Result.error("每个问题只能采纳一个答案！");
        }else {
            int i = problemMapper.updateReplyAdopt(reply);
            if (i>0){
                return Result.success("采纳成功！");
            }else {
                return Result.error("采纳失败！");
            }
        }
    }

    @Override
    public Result getProblemById(Map map) {
        problemMapper.addClick(map.get("id").toString());
        Map problem = problemMapper.getProblemById(map.get("id").toString());

        return Result.success(problem);
    }

    @Override
    public Result updateProblem(Problem problem, HttpServletRequest request) {
        /*修改完问题状态应该为0审核中*/
        problem.setProblem_state(0);
        problemMapper.updateProblem(problem);
        return Result.success();
    }

    @Override
    public Result deleteProblemByIds(Map map) {
        List ids = (List) map.get("ids");
        /*根据id删除问题*/
        problemMapper.deleteProblemByIds(ids);
        /*同时删除问题答案表的答案*/
        problemMapper.deleteReplyByProblemIds(ids);
        return Result.success("删除成功！");
    }

    @Override
    public Result getProblemHot() {
        List list = problemMapper.getProblemHot();
        return Result.success(list);
    }


}
