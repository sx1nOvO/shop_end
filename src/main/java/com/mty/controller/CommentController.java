package com.mty.controller;

import com.mty.config.PassToken;
import org.springframework.beans.factory.annotation.Autowired;
import com.mty.entity.Comment;
import com.mty.service.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.List;
import java.util.Date;
import com.mty.util.Result;

/**
 * 帖子评论表接口
 **/
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 分页查询
     */
    @PostMapping("/selectPage")
    public Result selectPage(@RequestBody Map<String,String> mp) {
        PageHelper.startPage(Integer.parseInt(mp.get("currentPage").toString()), Integer.parseInt(mp.get("pagesize").toString()));
        List<Comment> list = commentService.queryAllByLimit(mp);
        PageInfo<Comment> pageInfo = new PageInfo<Comment>(list);
        return Result.success(pageInfo);
    }

    /**
     * 查询所有
     */
    @PostMapping("/queryAll")
    public Result queryAll(@RequestBody Comment comment) {
        List<Comment> list = commentService.queryCondition(comment);
        return Result.success(list);
    }

    /**
     * 通过主键查询单条数据
     */
    @GetMapping("/selectOne")
    public Result selectOne(Integer id) {
        Comment comment = commentService.queryById(id);
        return Result.success(comment);
    }

    /**
     * 新增
     */
    @RequestMapping("/add")
    public Result add(@RequestBody Comment comment) {
        Date date = new Date();
        comment.setCreateTime(date);
        commentService.insert(comment);
        return Result.success("操作成功");
    }

    /**
     * 修改
     */
    @RequestMapping("/edit")
    public Result edit(@RequestBody Comment comment) {
        commentService.update(comment);
        return Result.success("操作成功");
    }

    /**
     * 删除
     */
    @GetMapping("/deleteById")
    public Result deleteById(Integer id) {
        commentService.deleteById(id);
        return Result.success("操作成功");
    }

    /**
     * 前端分页查询
     */
    @PostMapping("/frontPage")
    @PassToken
    public Result frontPage(@RequestBody Map<String,String> mp) {
        PageHelper.startPage(Integer.parseInt(mp.get("currentPage").toString()), Integer.parseInt(mp.get("pagesize").toString()));
        List<Comment> list = commentService.queryAllByLimit(mp);
        PageInfo<Comment> pageInfo = new PageInfo<Comment>(list);
        return Result.success(pageInfo);
    }

    /**
     * 前端通过主键查询单条数据
     */
    @GetMapping("/frontOne")
    @PassToken
    public Result frontOne(Integer id) {
        Comment comment = commentService.queryById(id);
        return Result.success(comment);
    }

    /**
     * 前端查询所有
     */
    @PostMapping("/frontAll")
    @PassToken
    public Result frontAll(@RequestBody Comment comment) {
        List<Comment> list = commentService.queryCondition(comment);
        return Result.success(list);
    }

}