package com.rainier.service.impl;

import com.rainier.mapper.UserMapper;
import com.rainier.model.Pcuser;
import com.rainier.model.Problem;
import com.rainier.service.UserService;
import com.rainier.util.Page;
import com.rainier.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Result updateUser(Pcuser pcuser) {
        int i = userMapper.updateUser(pcuser);
        if (i>0){
            return Result.success("修改成功！");
        }else {
            return Result.error("修改失败！");
        }

    }

    @Override
    public Result updateUserPortrait(Pcuser pcuser) {
        int i = userMapper.updateUserPortrait(pcuser);
        if (i>0){
            return Result.success("修改成功！");
        }else {
            return Result.error("修改失败！");
        }
    }

    @Override
    public Result getPortraitByUserId(Map map) {
        Integer pageIndex = Integer.parseInt(map.get("pageIndex").toString());//当前页数
        Integer pageSize = Integer.parseInt(map.get("pageSize").toString());//每页条数
        Integer type = Integer.parseInt(map.get("type").toString());//类型
        Integer answer = Integer.parseInt(map.get("answer").toString());//类型
        Page<Problem> page = new Page<Problem>();
        page.setPageIndex(pageIndex);
        page.setPageSize(pageSize);
        page.setTotalRecords(userMapper.getPortraitByUserIdCount(map.get("userId").toString(),type,answer));
        page.setList(userMapper.getPortraitByUserId(map.get("userId").toString(),map.get("ascOrDesc").toString(), ((page.getPageIndex() - 1) * page.getPageSize()), page.getPageSize(),type,answer));
        return Result.success(page);
    }
}
