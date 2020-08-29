package com.neu.buybook.controller;

import com.github.pagehelper.PageInfo;
import com.neu.buybook.model.UserInfo;
import com.neu.buybook.service.UserService;
import com.neu.buybook.vo.ResultVO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 用户模块控制器
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 登录
     * @param userInfo
     * @return
     */
    @RequestMapping("login")
    public ResultVO login(@RequestBody UserInfo userInfo){
        //通过缓存根据用户名查找用户
        UserInfo qUserInfo = userService.selByUserName(userInfo.getUsername());
        Map<String,Object> hm = userService.login(userInfo,qUserInfo);
        int status = (Integer)hm.get("status");
        ResultVO<UserInfo> resultVO = new ResultVO("",status);
        if(status == 0){//登录成功
            resultVO.setObject((UserInfo)hm.get("user"));
        }
        return resultVO;
    }


    /**
     * 查询所有
     * @return
     */
    @RequestMapping("list")
    public List<UserInfo> list(){
       return userService.selAll();
    }

    /**
     * 分页查询
     * @param currPage
     * @return
     */
    @RequestMapping("query/{currPage}")
    public PageInfo<UserInfo> query(@PathVariable Integer currPage){
        return userService.query(currPage);
    }



    /**
     * 根据主键加载
     * @param id
     * @return
     */
    @RequestMapping("sel/{id}")
    public UserInfo selById(@PathVariable Integer id){
        return userService.selById(id);
    }

    /**
     * 新增
     * @param userInfo
     * @return
     */
    @RequestMapping("add")
    public String add(@RequestBody UserInfo userInfo){
        userService.add(userInfo);
        return "succ";
    }

    @RequestMapping("del/{id}")
    public String del(@PathVariable Integer id){
        userService.del(id);
        return "succ";
    }


}
