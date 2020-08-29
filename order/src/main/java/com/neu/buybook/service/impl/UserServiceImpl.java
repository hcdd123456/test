package com.neu.buybook.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neu.buybook.mapper.UserInfoMapper;
import com.neu.buybook.model.UserInfo;
import com.neu.buybook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Service: 当前类是一个逻辑层的类,启动时自动创建
 * @Autowired: 把Dao对象注入
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<UserInfo> selAll() {
        return userInfoMapper.selectAll();
    }

    @Override
    public UserInfo selById(Integer id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(UserInfo userInfo) {
        userInfo.setAddDate(new Date());
        userInfoMapper.insert(userInfo);
    }

    /**
     * 分页查询
     * @param currPage
     * @return
     */
    @Override
    public PageInfo<UserInfo> query(Integer currPage) {
        if(currPage == null) currPage = 1;
        PageHelper.startPage(currPage,3);
        PageInfo<UserInfo> pageInfo = new PageInfo<>(userInfoMapper.selectAll());
        return pageInfo;
    }

    @Override
    public void del(Integer id) {
        userInfoMapper.deleteByPrimaryKey(id);
    }

    /**
     * 登录
     * @param userInfo
     * @return 0-成功 1-用户名错误 2-密码错误
     */
    @Override
    public Map<String,Object> login(UserInfo userInfo,UserInfo qUserInfo) {
        Map<String,Object> hm = new HashMap<>();
        int status = 0;
        if(qUserInfo != null){//有此用户
            if(!qUserInfo.getPassword().equals(userInfo.getPassword())){//密码错误
                status = 2;
            }else{//登录成功
                hm.put("user",qUserInfo);
            }
        }else{//用户名错误
            status = 1;
        }
        hm.put("status",status);
        return hm;
    }

    /** @Cacheable ：对方法结果进行缓存（主要用于GET方法）
     *  cacheNames/value:指定缓存主键
     *  key:缓存数据使用
     *  unless：否定缓存，当满足条件时，结果不被缓存。可以获取到结果（#result）进行判断。
     * @param username
     * @return
     */
    @Cacheable(value="user",key="#username",unless = "#result==null")
    public UserInfo selByUserName(String username){
        return userInfoMapper.selectByUsername(username);
    }


}
