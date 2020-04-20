package com.rainier.service.impl;

import com.rainier.mapper.LoginMapper;
import com.rainier.model.Pcuser;
import com.rainier.service.LoginService;
import com.rainier.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;


    /**
    * @描述 用户注册
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2020/3/2
    */
    @Override
    public Result register(Pcuser pcuser) {
        /*先查看用户名或手机号是否被占用*/
        Pcuser user = loginMapper.getPcuserByAccount(pcuser);
        if (user == null){
            pcuser.setNewdate(new Date());
            int state = loginMapper.register(pcuser);
            if (state > 0){
                return Result.success("注册成功！");
            }else {
                return Result.error("注册失败！");
            }
        }else {
            return Result.error("用户名或手机号已存在！");
        }


    }

    /**
    * @描述 用户登录——用户名/手机号&密码
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2020/3/2
    */
    @Override
    public Result accountLogin(Pcuser pcuser, HttpServletRequest hsr) {
        //根据用户名&密码或者手机号&密码查询用户基本信息
        Pcuser user = loginMapper.getPcuserByAccountOrPhone(pcuser);
        if(user==null){
            return Result.error("账号或密码不正确！");
        }else{
            hsr.getSession().setAttribute("user", user);
            return Result.success(user);
        }
    }
}
