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

    Integer getPortraitByUserIdCount( @Param("userId") String userId,@Param("type") Integer type,@Param("answer") Integer answer);

    List getPortraitByUserId(@Param("userId") String userId, @Param("ascOrDesc") String ascOrDesc, @Param("pageStart") Integer pageStart, @Param("pageSize") Integer pageSize,@Param("type") Integer type,@Param("answer") Integer answer);
}
