package com.rainier.service;

import com.rainier.model.Problem;
import com.rainier.model.Reply;
import com.rainier.util.Result;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Map;

public interface ProblemService {
    Result addProblem(Problem problem, HttpServletRequest request);

    Result addReply(Reply reply, HttpServletRequest request);

    Result getReplyByProblemId(Map map) throws ParseException;

    Result updateReplyAdopt(Reply reply);

    Result getProblemById(Map map);

    Result updateProblem(Problem problem, HttpServletRequest request);

    Result deleteProblemByIds(Map map);

    Result getProblemHot();

    Result getProblemList(Map map);
}
