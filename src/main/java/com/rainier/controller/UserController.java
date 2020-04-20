package com.rainier.controller;

import com.rainier.model.Pcuser;
import com.rainier.service.UserService;
import com.rainier.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @RequestMapping("getPortraitByUserIdAndType")
    @ResponseBody
    public Result getPortraitByUserIdAndType(@RequestBody Map map){
        return userService.getPortraitByUserId(map);
    }


}