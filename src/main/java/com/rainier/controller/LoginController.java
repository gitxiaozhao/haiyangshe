package com.rainier.controller;

import com.rainier.model.Pcuser;
import com.rainier.service.LoginService;
import com.rainier.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
* @描述 用户登录
* @参数注释：
* @创建人  wyz
* @创建时间  2020/3/2
*/
@Controller("loginController")
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    /**
    * @描述 用户注册
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2020/3/2
    */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public Result register(@RequestBody Pcuser pcuser){
        return loginService.register(pcuser);
    }

    /**
    * @描述 用户登录——用户名/手机号&密码
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2020/3/2
    */
    @RequestMapping(value = "/accountLogin",method = RequestMethod.POST)
    @ResponseBody
    public Result accountLogin(@RequestBody Pcuser pcuser, HttpServletRequest hsr){
        return loginService.accountLogin(pcuser,hsr);
    }

    /**
    * @描述 退出登录
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2020/3/2
    */
    @RequestMapping(value = "/logOut",method = RequestMethod.POST)
    @ResponseBody
    public Result quit(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return Result.success();
    }

}
