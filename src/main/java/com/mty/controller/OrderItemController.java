package com.mty.controller;

import com.mty.config.PassToken;
import com.mty.entity.*;
import com.mty.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import com.mty.util.Result;

/**
 * 订单项表接口
 **/
@RestController
@RequestMapping("/orderItem")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrdersService ordersService;
    /**
     * 分页查询
     */
    @PostMapping("/selectPage")
    public Result selectPage(@RequestBody Map<String,String> mp) {
        PageHelper.startPage(Integer.parseInt(mp.get("currentPage").toString()), Integer.parseInt(mp.get("pagesize").toString()));
        List<OrderItem> list = orderItemService.queryAllByLimit(mp);
        PageInfo<OrderItem> pageInfo = new PageInfo<OrderItem>(list);
        return Result.success(pageInfo);
    }

    /**
     * 查询所有
     */
    @PostMapping("/queryAll")
    public Result queryAll(@RequestBody OrderItem orderItem) {
        List<OrderItem> list = orderItemService.queryCondition(orderItem);
        return Result.success(list);
    }

    /**
     * 通过主键查询单条数据
     */
    @GetMapping("/selectOne")
    public Result selectOne(Integer id) {
        OrderItem orderItem = orderItemService.queryById(id);
        return Result.success(orderItem);
    }

    /**
     * 新增
     */
    @RequestMapping("/add")
    public Result add(@RequestBody OrderItem orderItem) {
        try {
            //先创建订单
            List<String> cids = Arrays.asList(orderItem.getGids().split(","));
            //订单编号
            Date date = new Date();
            SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
            String no = sf.format(date);
            //购物车列表
            List<Cart> carts = new ArrayList<>();
            //总价和总量
            BigDecimal total = new BigDecimal("0.00");
            Integer num = 0;
            for(int i=0;i<cids.size();i++){
                Cart cart = cartService.queryById(Integer.parseInt(cids.get(i)));
                Goods goods = goodsService.queryById(cart.getGid());
                num=num+cart.getNum();
                BigDecimal money = new BigDecimal(goods.getMoney());
                BigDecimal nums = new BigDecimal(String.valueOf(cart.getNum()));
                total = total.add(money.multiply(nums)).setScale(2, BigDecimal.ROUND_HALF_UP);
                cart.setGoods(goods);
                carts.add(cart);
            }
            Orders orders = new Orders();
            orders.setNo(no);
            orders.setNum(num);
            orders.setTotal(total.toString());
            orders.setUid(orderItem.getUid());
            orders.setRemark(orderItem.getRemark());
            orders.setStatus("01");
            orders.setCreateTime(date);
            ordersService.insert(orders);
            //新增订单项
            for(int i=0;i<carts.size();i++){
                OrderItem o = new OrderItem();
                o.setGid(carts.get(i).getGid());
                BigDecimal money = new BigDecimal(carts.get(i).getGoods().getMoney());
                BigDecimal nums = new BigDecimal(carts.get(i).getNum().toString());
                o.setMoney(money.multiply(nums).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                o.setNum(carts.get(i).getNum());
                o.setOid(orders.getId());
                o.setCreateTime(date);
                orderItemService.insert(o);
                //商品库存减少
                Goods g = new Goods();
                g.setId(carts.get(i).getGid());
                g.setNum(carts.get(i).getGoods().getNum()-carts.get(i).getNum());
                goodsService.update(g);
            }
            //删除购物车
            for (int j = 0;j<carts.size();j++){
                cartService.deleteById(carts.get(j).getId());
            }
            return Result.success("下单成功");
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("系统错误");
        }
    }


    /**
     * 立即下单
     */
    @RequestMapping("xiadan")
    public Result xiadan(@RequestBody OrderItem orderItem) {
        try {
            //订单编号
            Date date = new Date();
            SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
            String no = sf.format(date);
            //总价
            BigDecimal total = new BigDecimal("0.00");
            Goods goods = goodsService.queryById(orderItem.getGid());
            BigDecimal money = new BigDecimal(goods.getMoney());
            BigDecimal nums = new BigDecimal(String.valueOf(orderItem.getNum()));
            total = total.add(money.multiply(nums)).setScale(2, BigDecimal.ROUND_HALF_UP);

            Orders orders = new Orders();
            orders.setNo(no);
            orders.setNum(orderItem.getNum());
            orders.setTotal(total.toString());
            orders.setUid(orderItem.getUid());
            orders.setRemark(orderItem.getRemark());
            orders.setStatus("01");
            orders.setCreateTime(date);
            int oid = ordersService.insert(orders);
            //新增订单项
            OrderItem o = new OrderItem();
            o.setGid(orderItem.getGid());
            o.setMoney(total.toString());
            o.setNum(orderItem.getNum());
            o.setOid(orders.getId());
            o.setCreateTime(date);
            orderItemService.insert(o);
            //商品库存减少
            Goods g = new Goods();
            g.setId(orderItem.getGid());
            g.setNum(goods.getNum()-orderItem.getNum());
            goodsService.update(g);
            return Result.success("下单成功");
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("系统错误");
        }
    }



    /**
     * 修改
     */
    @RequestMapping("/edit")
    public Result edit(@RequestBody OrderItem orderItem) {
        orderItemService.update(orderItem);
        return Result.success("操作成功");
    }

    /**
     * 删除
     */
    @GetMapping("/deleteById")
    public Result deleteById(Integer id) {
        orderItemService.deleteById(id);
        return Result.success("操作成功");
    }

    /**
     * 前端分页查询
     */
    @PostMapping("/frontPage")
    @PassToken
    public Result frontPage(@RequestBody Map<String,String> mp) {
        PageHelper.startPage(Integer.parseInt(mp.get("currentPage").toString()), Integer.parseInt(mp.get("pagesize").toString()));
        List<OrderItem> list = orderItemService.queryAllByLimit(mp);
        PageInfo<OrderItem> pageInfo = new PageInfo<OrderItem>(list);
        return Result.success(pageInfo);
    }

    /**
     * 前端通过主键查询单条数据
     */
    @GetMapping("/frontOne")
    @PassToken
    public Result frontOne(Integer id) {
        OrderItem orderItem = orderItemService.queryById(id);
        return Result.success(orderItem);
    }

    /**
     * 前端查询所有
     */
    @PostMapping("/frontAll")
    @PassToken
    public Result frontAll(@RequestBody OrderItem orderItem) {
        List<OrderItem> list = orderItemService.queryCondition(orderItem);
        return Result.success(list);
    }

}