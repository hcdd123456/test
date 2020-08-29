package com.neu.buybook.service;

import com.github.pagehelper.PageInfo;
import com.neu.buybook.model.UserInfo;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<UserInfo> selAll();

    UserInfo selById(Integer id);

    void add(UserInfo userInfo);

    PageInfo<UserInfo> query(Integer currPage);

    void del(Integer id);

    Map<String,Object> login(UserInfo userInfo, UserInfo qUserInfo);

    UserInfo selByUserName(String username);
}
