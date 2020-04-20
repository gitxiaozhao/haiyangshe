package com.rainier.service.impl;

import com.rainier.mapper.BannerMapper;
import com.rainier.model.Advertisement;
import com.rainier.model.Banner;
import com.rainier.service.BannerService;
import com.rainier.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public Result getBanner() {
        List<Banner> bannerList = bannerMapper.getBanner();
        return Result.success(bannerList);
    }

    @Override
    public Result getAdvertisement() {
        List list = bannerMapper.getAdvertisement();

        return Result.success(list);
    }
}
