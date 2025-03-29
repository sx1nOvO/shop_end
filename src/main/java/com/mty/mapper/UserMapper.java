package com.mty.mapper;

import com.mty.entity.User;
import com.mty.util.MyMapper;
import java.util.Map;
import java.util.List;

/**
 * 操作userDAO层接口
*/
public interface UserMapper extends MyMapper<User> {

    /**
      * 分页查询数据
    */
    List<User> queryAllByLimit(Map mp);

    /**
      * 通过实体作为筛选条件查询
    */
    List<User> queryCondition(User user);

    /**
      * 通过ID查询单条数据
    */
    User queryById(Integer id);

    /**
      * 新增
    */
    int insert(User user);

    /**
      * 修改
    */
    boolean update(User user);

    /**
      * 删除
    */
    int deleteById(Integer id);

}