package com.rainier.service;

import com.rainier.model.Pcuser;
import com.rainier.util.Result;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {
    Result register(Pcuser pcuser);

    Result accountLogin(Pcuser pcuser, HttpServletRequest hsr);
}
