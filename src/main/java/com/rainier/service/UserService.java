package com.rainier.service;

import com.rainier.model.Pcuser;
import com.rainier.util.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface UserService {
    Result updateUser(Pcuser pcuser);

    Result updateUserPortrait(Pcuser pcuser);

    Result getProblemByUserId(Map map, HttpServletRequest request);

    Result deleteProblemById(Map map, HttpServletRequest request);

    Result getReplyByUserIdAndType(Map map, HttpServletRequest request);

    Result deleteReplyByIds(Map map);

    Result getReplyByAdopt(Map map, HttpServletRequest request);
}
