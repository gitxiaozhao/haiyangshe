package com.rainier.service;

import com.rainier.model.Pcuser;
import com.rainier.util.Result;

import java.util.Map;

public interface UserService {
    Result updateUser(Pcuser pcuser);

    Result updateUserPortrait(Pcuser pcuser);

    Result getPortraitByUserId(Map map);
}
