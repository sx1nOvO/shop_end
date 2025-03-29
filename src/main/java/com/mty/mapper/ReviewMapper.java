package com.mty.mapper;

import com.mty.entity.Review;
import com.mty.util.MyMapper;
import java.util.Map;
import java.util.List;

/**
 * 操作reviewDAO层接口
*/
public interface ReviewMapper extends MyMapper<Review> {

    /**
      * 分页查询数据
    */
    List<Review> queryAllByLimit(Map mp);

    /**
      * 通过实体作为筛选条件查询
    */
    List<Review> queryCondition(Review review);

    /**
      * 通过ID查询单条数据
    */
    Review queryById(Integer id);

    /**
      * 新增
    */
    int insert(Review review);

    /**
      * 修改
    */
    boolean update(Review review);

    /**
      * 删除
    */
    int deleteById(Integer id);

}