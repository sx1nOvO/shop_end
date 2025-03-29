package com.mty.mapper;

import com.mty.entity.Comment;
import com.mty.util.MyMapper;
import java.util.Map;
import java.util.List;

/**
 * 操作commentDAO层接口
*/
public interface CommentMapper extends MyMapper<Comment> {

    /**
      * 分页查询数据
    */
    List<Comment> queryAllByLimit(Map mp);

    /**
      * 通过实体作为筛选条件查询
    */
    List<Comment> queryCondition(Comment comment);

    /**
      * 通过ID查询单条数据
    */
    Comment queryById(Integer id);

    /**
      * 新增
    */
    int insert(Comment comment);

    /**
      * 修改
    */
    boolean update(Comment comment);

    /**
      * 删除
    */
    int deleteById(Integer id);

}