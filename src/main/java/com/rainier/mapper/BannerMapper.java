package com.rainier.mapper;

import com.rainier.model.Banner;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BannerMapper {
    List<Banner> getBanner();

    List getAdvertisement();

    List<Map> getAdvertisementImg();
}
