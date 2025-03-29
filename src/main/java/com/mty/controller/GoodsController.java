package com.mty.controller;

import com.mty.config.PassToken;
import com.mty.entity.Discuss;
import com.mty.entity.Review;
import com.mty.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import com.mty.entity.Goods;
import com.mty.service.GoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import com.mty.util.Result;

/**
 * 商品表接口
 **/
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private ReviewService reviewService;
    /**
     * 分页查询
     */
    @PostMapping("/selectPage")
    public Result selectPage(@RequestBody Map<String,String> mp) {
        PageHelper.startPage(Integer.parseInt(mp.get("currentPage").toString()), Integer.parseInt(mp.get("pagesize").toString()));
        List<Goods> list = goodsService.queryAllByLimit(mp);
        PageInfo<Goods> pageInfo = new PageInfo<Goods>(list);
        return Result.success(pageInfo);
    }

    /**
     * 查询所有
     */
    @PostMapping("/queryAll")
    public Result queryAll(@RequestBody Goods goods) {
        List<Goods> list = goodsService.queryCondition(goods);
        return Result.success(list);
    }

    /**
     * 通过主键查询单条数据
     */
    @GetMapping("/selectOne")
    public Result selectOne(Integer id) {
        Goods goods = goodsService.queryById(id);
        return Result.success(goods);
    }

    /**
     * 新增
     */
    @RequestMapping("/add")
    public Result add(@RequestBody Goods goods) {
        Date date = new Date();
        goods.setCreateTime(date);
        goodsService.insert(goods);
        return Result.success("操作成功");
    }

    /**
     * 修改
     */
    @RequestMapping("/edit")
    public Result edit(@RequestBody Goods goods) {
        goodsService.update(goods);
        return Result.success("操作成功");
    }

    /**
     * 删除
     */
    @GetMapping("/deleteById")
    public Result deleteById(Integer id) {
        goodsService.deleteById(id);
        return Result.success("操作成功");
    }

    /**
     * 前端分页查询
     */
    @PostMapping("/frontPage")
    @PassToken
    public Result frontPage(@RequestBody Map<String,String> mp) {
        PageHelper.startPage(Integer.parseInt(mp.get("currentPage").toString()), Integer.parseInt(mp.get("pagesize").toString()));
        List<Goods> list = goodsService.queryAllByLimit(mp);
        PageInfo<Goods> pageInfo = new PageInfo<Goods>(list);
        return Result.success(pageInfo);
    }

    /**
     * 前端通过主键查询单条数据
     */
    @GetMapping("/frontOne")
    @PassToken
    public Result frontOne(Integer id) {
        Goods goods = goodsService.queryById(id);
        if(goods!=null){
            Review review = new Review();
            review.setGid(goods.getId());
            List<Review> reviewList = reviewService.queryCondition(review);
            goods.setReviews(reviewList);
        }
        return Result.success(goods);
    }

    /**
     * 前端查询所有
     */
    @PostMapping("/frontAll")
    @PassToken
    public Result frontAll(@RequestBody Goods goods) {
        List<Goods> list = goodsService.queryCondition(goods);
        return Result.success(list);
    }

    @PostMapping("/frontBySales")
    @PassToken
    public Result frontBySales() {
        Map<String,String> mp = new HashMap<>();
        mp.put("sortBy","4");
        List<Goods> list = goodsService.queryAllByLimit(mp);
        if(list.size()>5){
            list = list.subList(0,5);
        }
        return Result.success(list);
    }

}