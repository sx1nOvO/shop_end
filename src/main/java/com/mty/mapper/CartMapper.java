package com.mty.mapper;

import com.mty.entity.Cart;
import com.mty.util.MyMapper;
import java.util.Map;
import java.util.List;

/**
 * 操作cartDAO层接口
*/
public interface CartMapper extends MyMapper<Cart> {

    /**
      * 分页查询数据
    */
    List<Cart> queryAllByLimit(Map mp);

    /**
      * 通过实体作为筛选条件查询
    */
    List<Cart> queryCondition(Cart cart);

    /**
      * 通过ID查询单条数据
    */
    Cart queryById(Integer id);

    /**
      * 新增
    */
    int insert(Cart cart);

    /**
      * 修改
    */
    boolean update(Cart cart);

    /**
      * 删除
    */
    int deleteById(Integer id);

}