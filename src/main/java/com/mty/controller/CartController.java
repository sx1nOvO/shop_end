package com.mty.controller;

import com.mty.config.PassToken;
import com.mty.entity.Goods;
import com.mty.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import com.mty.entity.Cart;
import com.mty.service.CartService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Date;
import com.mty.util.Result;

/**
 * 购物车接口
 **/
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private GoodsService goodsService;
    /**
     * 分页查询
     */
    @PostMapping("/selectPage")
    public Result selectPage(@RequestBody Map<String,String> mp) {
        PageHelper.startPage(Integer.parseInt(mp.get("currentPage").toString()), Integer.parseInt(mp.get("pagesize").toString()));
        List<Cart> list = cartService.queryAllByLimit(mp);
        PageInfo<Cart> pageInfo = new PageInfo<Cart>(list);
        return Result.success(pageInfo);
    }

    /**
     * 查询所有
     */
    @PostMapping("/queryAll")
    public Result queryAll(@RequestBody Cart cart) {
        List<Cart> list = cartService.queryCondition(cart);
        return Result.success(list);
    }

    /**
     * 通过主键查询单条数据
     */
    @GetMapping("/selectOne")
    public Result selectOne(Integer id) {
        Cart cart = cartService.queryById(id);
        return Result.success(cart);
    }

    /**
     * 新增
     */
    @RequestMapping("/add")
    public Result add(@RequestBody Cart cart) {
        Goods goods = goodsService.queryById(cart.getGid());
        if(goods.getNum()<cart.getNum()){
            return Result.error("库存不足");
        }
        Map mp = new HashMap();
        mp.put("uid",cart.getUid());
        mp.put("gid",cart.getGid());
        //判端购物车是否已经有同一个商品，有，只加数量，没有，添加
        List<Cart> carts = cartService.queryAllByLimit(mp);
        if(carts.size()>0){
            Cart cart1 = carts.get(0);
            cart1.setNum(cart1.getNum()+cart.getNum());
            cartService.update(cart1);
        }else{
            Date date = new Date();
            cart.setCreateTime(date);
            cartService.insert(cart);
        }
        return Result.success("操作成功");
    }

    /**
     * 修改
     */
    @RequestMapping("/edit")
    public Result edit(@RequestBody Cart cart) {
        cartService.update(cart);
        return Result.success("操作成功");
    }

    /**
     * 删除
     */
    @GetMapping("/deleteById")
    public Result deleteById(Integer id) {
        cartService.deleteById(id);
        return Result.success("操作成功");
    }

    /**
     * 前端分页查询
     */
    @PostMapping("/frontPage")
    @PassToken
    public Result frontPage(@RequestBody Map<String,String> mp) {
        PageHelper.startPage(Integer.parseInt(mp.get("currentPage").toString()), Integer.parseInt(mp.get("pagesize").toString()));
        List<Cart> list = cartService.queryAllByLimit(mp);
        PageInfo<Cart> pageInfo = new PageInfo<Cart>(list);
        return Result.success(pageInfo);
    }

    /**
     * 前端通过主键查询单条数据
     */
    @GetMapping("/frontOne")
    @PassToken
    public Result frontOne(Integer id) {
        Cart cart = cartService.queryById(id);
        return Result.success(cart);
    }

    /**
     * 前端查询所有
     */
    @PostMapping("/frontAll")
    @PassToken
    public Result frontAll(@RequestBody Cart cart) {
        List<Cart> list = cartService.queryCondition(cart);
        return Result.success(list);
    }

}