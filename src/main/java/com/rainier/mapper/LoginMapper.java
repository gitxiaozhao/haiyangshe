package com.rainier.mapper;

import com.rainier.model.Pcuser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LoginMapper {

    Pcuser getPcuserByAccount(@Param("pcuser") Pcuser pcuser);

    int register(@Param("pcuser") Pcuser pcuser);

    Pcuser getPcuserByAccountOrPhone(@Param("pcuser")Pcuser pcuser);


}
