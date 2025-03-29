package com.mty.service;

import com.mty.entity.Review;
import com.mty.mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
/**
 * 商品评论表业务处理
 **/
@Service
public class ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    /**
     * 分页查询
     */
    public List<Review> queryAllByLimit(Map mp) {
        return reviewMapper.queryAllByLimit(mp);
    }

    /**
     * 查询所有
     */
    public List<Review> queryCondition(Review review) {
        return reviewMapper.queryCondition(review);
    }

    /**
     * 通过ID查询单条数据
     */
    public Review queryById(Integer id) {
        return reviewMapper.queryById(id);
    }

    /**
     * 新增
     */
    public int insert(Review review) {
        return reviewMapper.insert(review);
    }

    /**
     * 修改
     */
    public boolean update(Review review) {
        return reviewMapper.update(review);
    }

    /**
     * 删除
     */
    public boolean deleteById(Integer id) {
        return reviewMapper.deleteById(id) > 0;
    }

}