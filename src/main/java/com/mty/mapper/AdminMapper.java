package com.mty.mapper;

import com.mty.entity.Admin;
import com.mty.util.MyMapper;
import java.util.Map;
import java.util.List;

/**
 * 操作adminDAO层接口
*/
public interface AdminMapper extends MyMapper<Admin> {

    /**
      * 分页查询数据
    */
    List<Admin> queryAllByLimit(Map mp);

    /**
      * 通过实体作为筛选条件查询
    */
    List<Admin> queryCondition(Admin admin);

    /**
      * 通过ID查询单条数据
    */
    Admin queryById(Integer id);

    /**
      * 新增
    */
    int insert(Admin admin);

    /**
      * 修改
    */
    boolean update(Admin admin);

    /**
      * 删除
    */
    int deleteById(Integer id);

}