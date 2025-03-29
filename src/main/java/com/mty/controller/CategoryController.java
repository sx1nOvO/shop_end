package com.mty.controller;

import com.mty.config.PassToken;
import org.springframework.beans.factory.annotation.Autowired;
import com.mty.entity.Category;
import com.mty.service.CategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.List;
import java.util.Date;
import com.mty.util.Result;

/**
 * 分类表接口
 **/
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 分页查询
     */
    @PostMapping("/selectPage")
    public Result selectPage(@RequestBody Map<String,String> mp) {
        PageHelper.startPage(Integer.parseInt(mp.get("currentPage").toString()), Integer.parseInt(mp.get("pagesize").toString()));
        List<Category> list = categoryService.queryAllByLimit(mp);
        PageInfo<Category> pageInfo = new PageInfo<Category>(list);
        return Result.success(pageInfo);
    }

    /**
     * 查询所有
     */
    @PostMapping("/queryAll")
    public Result queryAll(@RequestBody Category category) {
        List<Category> list = categoryService.queryCondition(category);
        return Result.success(list);
    }

    /**
     * 通过主键查询单条数据
     */
    @GetMapping("/selectOne")
    public Result selectOne(Integer id) {
        Category category = categoryService.queryById(id);
        return Result.success(category);
    }

    /**
     * 新增
     */
    @RequestMapping("/add")
    public Result add(@RequestBody Category category) {
        Date date = new Date();
        category.setCreateTime(date);
        categoryService.insert(category);
        return Result.success("操作成功");
    }

    /**
     * 修改
     */
    @RequestMapping("/edit")
    public Result edit(@RequestBody Category category) {
        categoryService.update(category);
        return Result.success("操作成功");
    }

    /**
     * 删除
     */
    @GetMapping("/deleteById")
    public Result deleteById(Integer id) {
        categoryService.deleteById(id);
        return Result.success("操作成功");
    }

    /**
     * 前端分页查询
     */
    @PostMapping("/frontPage")
    @PassToken
    public Result frontPage(@RequestBody Map<String,String> mp) {
        PageHelper.startPage(Integer.parseInt(mp.get("currentPage").toString()), Integer.parseInt(mp.get("pagesize").toString()));
        List<Category> list = categoryService.queryAllByLimit(mp);
        PageInfo<Category> pageInfo = new PageInfo<Category>(list);
        return Result.success(pageInfo);
    }

    /**
     * 前端通过主键查询单条数据
     */
    @GetMapping("/frontOne")
    @PassToken
    public Result frontOne(Integer id) {
        Category category = categoryService.queryById(id);
        return Result.success(category);
    }

    /**
     * 前端查询所有
     */
    @PostMapping("/frontAll")
    @PassToken
    public Result frontAll(@RequestBody Category category) {
        List<Category> list = categoryService.queryCondition(category);
        return Result.success(list);
    }

}