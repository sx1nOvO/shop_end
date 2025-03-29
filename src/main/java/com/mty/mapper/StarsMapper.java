package com.mty.mapper;

import com.mty.entity.Stars;
import com.mty.util.MyMapper;
import java.util.Map;
import java.util.List;

/**
 * 操作starsDAO层接口
*/
public interface StarsMapper extends MyMapper<Stars> {

    /**
      * 分页查询数据
    */
    List<Stars> queryAllByLimit(Map mp);

    /**
      * 通过实体作为筛选条件查询
    */
    List<Stars> queryCondition(Stars stars);

    /**
      * 通过ID查询单条数据
    */
    Stars queryById(Integer id);

    /**
      * 新增
    */
    int insert(Stars stars);

    /**
      * 修改
    */
    boolean update(Stars stars);

    /**
      * 删除
    */
    int deleteById(Integer id);

}