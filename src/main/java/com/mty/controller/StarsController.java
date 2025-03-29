package com.mty.controller;

import com.mty.config.PassToken;
import org.springframework.beans.factory.annotation.Autowired;
import com.mty.entity.Stars;
import com.mty.service.StarsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.List;
import java.util.Date;
import com.mty.util.Result;

/**
 * 收藏表接口
 **/
@RestController
@RequestMapping("/stars")
public class StarsController {

    @Autowired
    private StarsService starsService;

    /**
     * 分页查询
     */
    @PostMapping("/selectPage")
    public Result selectPage(@RequestBody Map<String,String> mp) {
        PageHelper.startPage(Integer.parseInt(mp.get("currentPage").toString()), Integer.parseInt(mp.get("pagesize").toString()));
        List<Stars> list = starsService.queryAllByLimit(mp);
        PageInfo<Stars> pageInfo = new PageInfo<Stars>(list);
        return Result.success(pageInfo);
    }

    /**
     * 查询所有
     */
    @PostMapping("/queryAll")
    public Result queryAll(@RequestBody Stars stars) {
        List<Stars> list = starsService.queryCondition(stars);
        return Result.success(list);
    }

    /**
     * 通过主键查询单条数据
     */
    @GetMapping("/selectOne")
    public Result selectOne(Integer id) {
        Stars stars = starsService.queryById(id);
        return Result.success(stars);
    }

    /**
     * 新增
     */
    @RequestMapping("/add")
    public Result add(@RequestBody Stars stars) {
        List<Stars> starsList = starsService.queryCondition(stars);
        if(starsList.size()>0){
            return Result.error("请勿重复收藏");
        }
        Date date = new Date();
        stars.setCreateTime(date);
        starsService.insert(stars);
        return Result.success("操作成功");
    }

    /**
     * 修改
     */
    @RequestMapping("/edit")
    public Result edit(@RequestBody Stars stars) {
        starsService.update(stars);
        return Result.success("操作成功");
    }

    /**
     * 删除
     */
    @GetMapping("/deleteById")
    public Result deleteById(Integer id) {
        starsService.deleteById(id);
        return Result.success("操作成功");
    }

    /**
     * 前端分页查询
     */
    @PostMapping("/frontPage")
    @PassToken
    public Result frontPage(@RequestBody Map<String,String> mp) {
        PageHelper.startPage(Integer.parseInt(mp.get("currentPage").toString()), Integer.parseInt(mp.get("pagesize").toString()));
        List<Stars> list = starsService.queryAllByLimit(mp);
        PageInfo<Stars> pageInfo = new PageInfo<Stars>(list);
        return Result.success(pageInfo);
    }

    /**
     * 前端通过主键查询单条数据
     */
    @GetMapping("/frontOne")
    @PassToken
    public Result frontOne(Integer id) {
        Stars stars = starsService.queryById(id);
        return Result.success(stars);
    }

    /**
     * 前端查询所有
     */
    @PostMapping("/frontAll")
    @PassToken
    public Result frontAll(@RequestBody Stars stars) {
        List<Stars> list = starsService.queryCondition(stars);
        return Result.success(list);
    }

}