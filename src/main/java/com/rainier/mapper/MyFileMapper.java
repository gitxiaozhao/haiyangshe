package com.rainier.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MyFileMapper {
    int insertMyFile(@Param("name")String name, @Param("bendiUrl")String bendiUrl, @Param("fuwuqiUrl")String fuwuqiUrl);

    void quartzDeleteMyFile(@Param("urlList")List<String> urlList);

    List<Map> getMyFileLujing();
}
