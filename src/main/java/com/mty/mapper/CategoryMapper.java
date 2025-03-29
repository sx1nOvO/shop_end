package com.mty.mapper;

import com.mty.entity.Category;
import com.mty.util.MyMapper;
import java.util.Map;
import java.util.List;

/**
 * 操作categoryDAO层接口
*/
public interface CategoryMapper extends MyMapper<Category> {

    /**
      * 分页查询数据
    */
    List<Category> queryAllByLimit(Map mp);

    /**
      * 通过实体作为筛选条件查询
    */
    List<Category> queryCondition(Category category);

    /**
      * 通过ID查询单条数据
    */
    Category queryById(Integer id);

    /**
      * 新增
    */
    int insert(Category category);

    /**
      * 修改
    */
    boolean update(Category category);

    /**
      * 删除
    */
    int deleteById(Integer id);

}