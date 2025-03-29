package com.mty.service;

import com.mty.entity.Cart;
import com.mty.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
/**
 * 购物车业务处理
 **/
@Service
public class CartService {

    @Autowired
    private CartMapper cartMapper;

    /**
     * 分页查询
     */
    public List<Cart> queryAllByLimit(Map mp) {
        return cartMapper.queryAllByLimit(mp);
    }

    /**
     * 查询所有
     */
    public List<Cart> queryCondition(Cart cart) {
        return cartMapper.queryCondition(cart);
    }

    /**
     * 通过ID查询单条数据
     */
    public Cart queryById(Integer id) {
        return cartMapper.queryById(id);
    }

    /**
     * 新增
     */
    public int insert(Cart cart) {
        return cartMapper.insert(cart);
    }

    /**
     * 修改
     */
    public boolean update(Cart cart) {
        return cartMapper.update(cart);
    }

    /**
     * 删除
     */
    public boolean deleteById(Integer id) {
        return cartMapper.deleteById(id) > 0;
    }

}