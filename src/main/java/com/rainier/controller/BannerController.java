package com.rainier.controller;

import com.rainier.service.BannerService;
import com.rainier.service.LoginService;
import com.rainier.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
* @描述 轮播图
* @参数注释：
* @创建人  wyz
* @创建时间  2020/3/3
*/
@Controller
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    /**
    * @描述 查询轮播图
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2020/3/3
    */
    @ResponseBody
    @RequestMapping("getBanner")
    public Result getBanner(){
        return bannerService.getBanner();
    }

    /**
    * @描述 获取广告
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2020/3/16
    */
    @ResponseBody
    @RequestMapping("getAdvertisement")
    public Result getAdvertisement(){
        return bannerService.getAdvertisement();
    }

}
