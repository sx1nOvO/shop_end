package com.mty.controller;

import com.mty.config.PassToken;
import org.springframework.beans.factory.annotation.Autowired;
import com.mty.entity.User;
import com.mty.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.List;
import java.util.Date;
import com.mty.util.Result;

/**
 * 用户表接口
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 分页查询
     */
    @PostMapping("/selectPage")
    public Result selectPage(@RequestBody Map<String,String> mp) {
        PageHelper.startPage(Integer.parseInt(mp.get("currentPage").toString()), Integer.parseInt(mp.get("pagesize").toString()));
        List<User> list = userService.queryAllByLimit(mp);
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        return Result.success(pageInfo);
    }

    /**
     * 查询所有
     */
    @PostMapping("/queryAll")
    public Result queryAll(@RequestBody User user) {
        List<User> list = userService.queryCondition(user);
        return Result.success(list);
    }

    /**
     * 通过主键查询单条数据
     */
    @GetMapping("/selectOne")
    public Result selectOne(Integer id) {
        User user = userService.queryById(id);
        return Result.success(user);
    }

    /**
     * 新增
     */
    @RequestMapping("/add")
    public Result add(@RequestBody User user) {
        List<User> users = userService.queryCondition(new User());
        for(int i=0;i<users.size();i++){
            if(users.get(i).getPhone().equals(user.getPhone())){
                return Result.error("手机号重复");
            }
        }
        Date date = new Date();
        user.setCreateTime(date);
        userService.insert(user);
        return Result.success("操作成功");
    }

    /**
     * 修改
     */
    @RequestMapping("/edit")
    public Result edit(@RequestBody User user) {
        List<User> users = userService.queryCondition(new User());
        for(int i=0;i<users.size();i++){
            if(users.get(i).getPhone().equals(user.getPhone()) && !users.get(i).getId().equals(user.getId())){
                return Result.error("手机号重复");
            }
        }
        userService.update(user);
        return Result.success("操作成功");
    }

    /**
     * 删除
     */
    @GetMapping("/deleteById")
    public Result deleteById(Integer id) {
        userService.deleteById(id);
        return Result.success("操作成功");
    }

    /**
     * 前端分页查询
     */
    @PostMapping("/frontPage")
    @PassToken
    public Result frontPage(@RequestBody Map<String,String> mp) {
        PageHelper.startPage(Integer.parseInt(mp.get("currentPage").toString()), Integer.parseInt(mp.get("pagesize").toString()));
        List<User> list = userService.queryAllByLimit(mp);
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        return Result.success(pageInfo);
    }

    /**
     * 前端通过主键查询单条数据
     */
    @GetMapping("/frontOne")
    @PassToken
    public Result frontOne(Integer id) {
        User user = userService.queryById(id);
        return Result.success(user);
    }

    /**
     * 前端查询所有
     */
    @PostMapping("/frontAll")
    @PassToken
    public Result frontAll(@RequestBody User user) {
        List<User> list = userService.queryCondition(user);
        return Result.success(list);
    }

}