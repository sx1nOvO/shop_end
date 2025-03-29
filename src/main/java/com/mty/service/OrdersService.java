package com.mty.service;

import com.mty.entity.Orders;
import com.mty.mapper.OrdersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
/**
 * 订单表业务处理
 **/
@Service
public class OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    /**
     * 分页查询
     */
    public List<Orders> queryAllByLimit(Map mp) {
        return ordersMapper.queryAllByLimit(mp);
    }

    /**
     * 查询所有
     */
    public List<Orders> queryCondition(Orders orders) {
        return ordersMapper.queryCondition(orders);
    }

    /**
     * 通过ID查询单条数据
     */
    public Orders queryById(Integer id) {
        return ordersMapper.queryById(id);
    }

    /**
     * 新增
     */
    public int insert(Orders orders) {
        return ordersMapper.insert(orders);
    }

    /**
     * 修改
     */
    public boolean update(Orders orders) {
        return ordersMapper.update(orders);
    }

    /**
     * 删除
     */
    public boolean deleteById(Integer id) {
        return ordersMapper.deleteById(id) > 0;
    }

}