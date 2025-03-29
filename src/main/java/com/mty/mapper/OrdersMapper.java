package com.mty.mapper;

import com.mty.entity.Orders;
import com.mty.util.MyMapper;
import java.util.Map;
import java.util.List;

/**
 * 操作ordersDAO层接口
*/
public interface OrdersMapper extends MyMapper<Orders> {

    /**
      * 分页查询数据
    */
    List<Orders> queryAllByLimit(Map mp);

    /**
      * 通过实体作为筛选条件查询
    */
    List<Orders> queryCondition(Orders orders);

    /**
      * 通过ID查询单条数据
    */
    Orders queryById(Integer id);

    /**
      * 新增
    */
    int insert(Orders orders);

    /**
      * 修改
    */
    boolean update(Orders orders);

    /**
      * 删除
    */
    int deleteById(Integer id);

}