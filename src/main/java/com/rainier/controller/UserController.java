package com.rainier.controller;

import com.rainier.model.Pcuser;
import com.rainier.service.UserService;
import com.rainier.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
* @描述 用户
* @参数注释：
* @创建人  wyz
* @创建时间  2020/3/10
*/
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
    * @描述 修改用户信息
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2020/3/10
    */
    @RequestMapping("updateUser")
    @ResponseBody
    public Result updateUser(@RequestBody Pcuser pcuser){
        return userService.updateUser(pcuser);
    }

    /**
    * @描述 修改头像
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2020/3/10
    */
    @RequestMapping("updateUserPortrait")
    @ResponseBody
    public Result updateUserPortrait(@RequestBody Pcuser pcuser){
        return userService.updateUserPortrait(pcuser);
    }

    /**
    * @描述 查看个人创建的问题列表
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2020/3/12
    */
    @RequestMapping("question")
    @ResponseBody
    public Result getProblemByUserIdAndType(@RequestBody Map map, HttpServletRequest request){
        return userService.getProblemByUserId(map,request);
    }

    /*
    * 删除个人未通过的问题
    * */
    @RequestMapping("deleteProblemByIds")
    @ResponseBody
    public Result deleteProblemByIds(@RequestBody Map map, HttpServletRequest request){
        return userService.deleteProblemById(map,request);
    }


    /*
    * 我的回答
    * */
    @RequestMapping("answers")
    @ResponseBody
    public Result getReplyByUserIdAndType(@RequestBody Map map, HttpServletRequest request){
        return userService.getReplyByUserIdAndType(map,request);
    }

    /*
    * 批量删除回答
    * */
    @RequestMapping("deleteReplyByIds")
    @ResponseBody
    public Result deleteReplyByIds(@RequestBody Map map){
        return userService.deleteReplyByIds(map);
    }

    /*
     * 查看被采纳的回答列表
     * */
    @RequestMapping("getReplyByAdopt")
    @ResponseBody
    public Result getReplyByAdopt(@RequestBody Map map,HttpServletRequest request){
        return userService.getReplyByAdopt(map,request);
    }
}