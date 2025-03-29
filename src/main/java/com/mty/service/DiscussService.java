package com.mty.service;

import com.mty.entity.Discuss;
import com.mty.mapper.DiscussMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
/**
 * 穿搭评论表业务处理
 **/
@Service
public class DiscussService {

    @Autowired
    private DiscussMapper discussMapper;

    /**
     * 分页查询
     */
    public List<Discuss> queryAllByLimit(Map mp) {
        return discussMapper.queryAllByLimit(mp);
    }

    /**
     * 查询所有
     */
    public List<Discuss> queryCondition(Discuss discuss) {
        return discussMapper.queryCondition(discuss);
    }

    /**
     * 通过ID查询单条数据
     */
    public Discuss queryById(Integer id) {
        return discussMapper.queryById(id);
    }

    /**
     * 新增
     */
    public int insert(Discuss discuss) {
        return discussMapper.insert(discuss);
    }

    /**
     * 修改
     */
    public boolean update(Discuss discuss) {
        return discussMapper.update(discuss);
    }

    /**
     * 删除
     */
    public boolean deleteById(Integer id) {
        return discussMapper.deleteById(id) > 0;
    }

}