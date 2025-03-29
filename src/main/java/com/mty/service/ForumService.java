package com.mty.service;

import com.mty.entity.Forum;
import com.mty.mapper.ForumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
/**
 * 帖子表业务处理
 **/
@Service
public class ForumService {

    @Autowired
    private ForumMapper forumMapper;

    /**
     * 分页查询
     */
    public List<Forum> queryAllByLimit(Map mp) {
        return forumMapper.queryAllByLimit(mp);
    }

    public List<Forum> recommend() {
        return forumMapper.recommend();
    }

    /**
     * 查询所有
     */
    public List<Forum> queryCondition(Forum forum) {
        return forumMapper.queryCondition(forum);
    }

    /**
     * 通过ID查询单条数据
     */
    public Forum queryById(Integer id) {
        return forumMapper.queryById(id);
    }

    /**
     * 新增
     */
    public int insert(Forum forum) {
        return forumMapper.insert(forum);
    }

    /**
     * 修改
     */
    public boolean update(Forum forum) {
        return forumMapper.update(forum);
    }

    /**
     * 删除
     */
    public boolean deleteById(Integer id) {
        return forumMapper.deleteById(id) > 0;
    }

}