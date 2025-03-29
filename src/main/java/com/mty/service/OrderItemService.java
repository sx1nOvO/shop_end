package com.mty.service;

import com.mty.entity.OrderItem;
import com.mty.mapper.OrderItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
/**
 * 订单项表业务处理
 **/
@Service
public class OrderItemService {

    @Autowired
    private OrderItemMapper orderItemMapper;

    /**
     * 分页查询
     */
    public List<OrderItem> queryAllByLimit(Map mp) {
        return orderItemMapper.queryAllByLimit(mp);
    }

    /**
     * 查询所有
     */
    public List<OrderItem> queryCondition(OrderItem orderItem) {
        return orderItemMapper.queryCondition(orderItem);
    }

    /**
     * 通过ID查询单条数据
     */
    public OrderItem queryById(Integer id) {
        return orderItemMapper.queryById(id);
    }

    /**
     * 新增
     */
    public int insert(OrderItem orderItem) {
        return orderItemMapper.insert(orderItem);
    }

    /**
     * 修改
     */
    public boolean update(OrderItem orderItem) {
        return orderItemMapper.update(orderItem);
    }

    /**
     * 删除
     */
    public boolean deleteById(Integer id) {
        return orderItemMapper.deleteById(id) > 0;
    }

}