package com.rainier.mapper;

import com.rainier.model.Pcuser;
import com.rainier.model.Problem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    int updateUser(@Param("pcuser") Pcuser pcuser);

    int updateUserPortrait(@Param("pcuser") Pcuser pcuser);

    List<Map> getUserPortrait();

    Integer getProblemByUserIdCount( @Param("userId") Integer userId,@Param("type") Integer type);

    List getProblemByUserId0(@Param("userId") Integer userId, @Param("pageStart") Integer pageStart, @Param("pageSize") Integer pageSize);

    List getProblemByUserId1(@Param("userId") Integer userId, @Param("pageStart") Integer pageStart, @Param("pageSize") Integer pageSize);

    List getProblemByUserId2(@Param("userId") Integer userId, @Param("pageStart") Integer pageStart, @Param("pageSize") Integer pageSize);

    List getProblemByUserId3(@Param("userId") Integer userId, @Param("pageStart") Integer pageStart, @Param("pageSize") Integer pageSize);

    List getProblemByUserId4(@Param("userId") Integer userId, @Param("pageStart") Integer pageStart, @Param("pageSize") Integer pageSize);

}
