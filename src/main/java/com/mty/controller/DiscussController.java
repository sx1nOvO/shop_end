package com.mty.controller;

import com.mty.config.PassToken;
import org.springframework.beans.factory.annotation.Autowired;
import com.mty.entity.Discuss;
import com.mty.service.DiscussService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.List;
import java.util.Date;
import com.mty.util.Result;

/**
 * 穿搭评论表接口
 **/
@RestController
@RequestMapping("/discuss")
public class DiscussController {

    @Autowired
    private DiscussService discussService;

    /**
     * 分页查询
     */
    @PostMapping("/selectPage")
    public Result selectPage(@RequestBody Map<String,String> mp) {
        PageHelper.startPage(Integer.parseInt(mp.get("currentPage").toString()), Integer.parseInt(mp.get("pagesize").toString()));
        List<Discuss> list = discussService.queryAllByLimit(mp);
        PageInfo<Discuss> pageInfo = new PageInfo<Discuss>(list);
        return Result.success(pageInfo);
    }

    /**
     * 查询所有
     */
    @PostMapping("/queryAll")
    public Result queryAll(@RequestBody Discuss discuss) {
        List<Discuss> list = discussService.queryCondition(discuss);
        return Result.success(list);
    }

    /**
     * 通过主键查询单条数据
     */
    @GetMapping("/selectOne")
    public Result selectOne(Integer id) {
        Discuss discuss = discussService.queryById(id);
        return Result.success(discuss);
    }

    /**
     * 新增
     */
    @RequestMapping("/add")
    public Result add(@RequestBody Discuss discuss) {
        Date date = new Date();
        discuss.setCreateTime(date);
        discussService.insert(discuss);
        return Result.success("操作成功");
    }

    /**
     * 修改
     */
    @RequestMapping("/edit")
    public Result edit(@RequestBody Discuss discuss) {
        discussService.update(discuss);
        return Result.success("操作成功");
    }

    /**
     * 删除
     */
    @GetMapping("/deleteById")
    public Result deleteById(Integer id) {
        discussService.deleteById(id);
        return Result.success("操作成功");
    }

    /**
     * 前端分页查询
     */
    @PostMapping("/frontPage")
    @PassToken
    public Result frontPage(@RequestBody Map<String,String> mp) {
        PageHelper.startPage(Integer.parseInt(mp.get("currentPage").toString()), Integer.parseInt(mp.get("pagesize").toString()));
        List<Discuss> list = discussService.queryAllByLimit(mp);
        PageInfo<Discuss> pageInfo = new PageInfo<Discuss>(list);
        return Result.success(pageInfo);
    }

    /**
     * 前端通过主键查询单条数据
     */
    @GetMapping("/frontOne")
    @PassToken
    public Result frontOne(Integer id) {
        Discuss discuss = discussService.queryById(id);
        return Result.success(discuss);
    }

    /**
     * 前端查询所有
     */
    @PostMapping("/frontAll")
    @PassToken
    public Result frontAll(@RequestBody Discuss discuss) {
        List<Discuss> list = discussService.queryCondition(discuss);
        return Result.success(list);
    }

}