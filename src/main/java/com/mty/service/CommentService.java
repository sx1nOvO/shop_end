package com.mty.service;

import com.mty.entity.Comment;
import com.mty.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
/**
 * 帖子评论表业务处理
 **/
@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    /**
     * 分页查询
     */
    public List<Comment> queryAllByLimit(Map mp) {
        return commentMapper.queryAllByLimit(mp);
    }

    /**
     * 查询所有
     */
    public List<Comment> queryCondition(Comment comment) {
        return commentMapper.queryCondition(comment);
    }

    /**
     * 通过ID查询单条数据
     */
    public Comment queryById(Integer id) {
        return commentMapper.queryById(id);
    }

    /**
     * 新增
     */
    public int insert(Comment comment) {
        return commentMapper.insert(comment);
    }

    /**
     * 修改
     */
    public boolean update(Comment comment) {
        return commentMapper.update(comment);
    }

    /**
     * 删除
     */
    public boolean deleteById(Integer id) {
        return commentMapper.deleteById(id) > 0;
    }

}