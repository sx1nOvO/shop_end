package com.mty.mapper;

import com.mty.entity.OrderItem;
import com.mty.util.MyMapper;
import java.util.Map;
import java.util.List;

/**
 * 操作order_itemDAO层接口
*/
public interface OrderItemMapper extends MyMapper<OrderItem> {

    /**
      * 分页查询数据
    */
    List<OrderItem> queryAllByLimit(Map mp);

    /**
      * 通过实体作为筛选条件查询
    */
    List<OrderItem> queryCondition(OrderItem orderItem);

    /**
      * 通过ID查询单条数据
    */
    OrderItem queryById(Integer id);

    /**
      * 新增
    */
    int insert(OrderItem orderItem);

    /**
      * 修改
    */
    boolean update(OrderItem orderItem);

    /**
      * 删除
    */
    int deleteById(Integer id);

}