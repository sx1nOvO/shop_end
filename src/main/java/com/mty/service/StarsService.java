package com.mty.service;

import com.mty.entity.Stars;
import com.mty.mapper.StarsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
/**
 * 收藏表业务处理
 **/
@Service
public class StarsService {

    @Autowired
    private StarsMapper starsMapper;

    /**
     * 分页查询
     */
    public List<Stars> queryAllByLimit(Map mp) {
        return starsMapper.queryAllByLimit(mp);
    }

    /**
     * 查询所有
     */
    public List<Stars> queryCondition(Stars stars) {
        return starsMapper.queryCondition(stars);
    }

    /**
     * 通过ID查询单条数据
     */
    public Stars queryById(Integer id) {
        return starsMapper.queryById(id);
    }

    /**
     * 新增
     */
    public int insert(Stars stars) {
        return starsMapper.insert(stars);
    }

    /**
     * 修改
     */
    public boolean update(Stars stars) {
        return starsMapper.update(stars);
    }

    /**
     * 删除
     */
    public boolean deleteById(Integer id) {
        return starsMapper.deleteById(id) > 0;
    }

}