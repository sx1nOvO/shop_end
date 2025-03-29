package com.mty.mapper;

import com.mty.entity.Forum;
import com.mty.util.MyMapper;
import java.util.Map;
import java.util.List;

/**
 * 操作forumDAO层接口
*/
public interface ForumMapper extends MyMapper<Forum> {

    /**
      * 分页查询数据
    */
    List<Forum> queryAllByLimit(Map mp);

    List<Forum> recommend();

    /**
      * 通过实体作为筛选条件查询
    */
    List<Forum> queryCondition(Forum forum);

    /**
      * 通过ID查询单条数据
    */
    Forum queryById(Integer id);

    /**
      * 新增
    */
    int insert(Forum forum);

    /**
      * 修改
    */
    boolean update(Forum forum);

    /**
      * 删除
    */
    int deleteById(Integer id);

}