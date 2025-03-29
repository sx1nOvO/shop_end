package com.mty.mapper;

import com.mty.entity.Discuss;
import com.mty.util.MyMapper;
import java.util.Map;
import java.util.List;

/**
 * 操作discussDAO层接口
*/
public interface DiscussMapper extends MyMapper<Discuss> {

    /**
      * 分页查询数据
    */
    List<Discuss> queryAllByLimit(Map mp);

    /**
      * 通过实体作为筛选条件查询
    */
    List<Discuss> queryCondition(Discuss discuss);

    /**
      * 通过ID查询单条数据
    */
    Discuss queryById(Integer id);

    /**
      * 新增
    */
    int insert(Discuss discuss);

    /**
      * 修改
    */
    boolean update(Discuss discuss);

    /**
      * 删除
    */
    int deleteById(Integer id);

}