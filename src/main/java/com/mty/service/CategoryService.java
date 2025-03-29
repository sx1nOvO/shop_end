package com.mty.service;

import com.mty.entity.Category;
import com.mty.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
/**
 * 分类表业务处理
 **/
@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 分页查询
     */
    public List<Category> queryAllByLimit(Map mp) {
        return categoryMapper.queryAllByLimit(mp);
    }

    /**
     * 查询所有
     */
    public List<Category> queryCondition(Category category) {
        return categoryMapper.queryCondition(category);
    }

    /**
     * 通过ID查询单条数据
     */
    public Category queryById(Integer id) {
        return categoryMapper.queryById(id);
    }

    /**
     * 新增
     */
    public int insert(Category category) {
        return categoryMapper.insert(category);
    }

    /**
     * 修改
     */
    public boolean update(Category category) {
        return categoryMapper.update(category);
    }

    /**
     * 删除
     */
    public boolean deleteById(Integer id) {
        return categoryMapper.deleteById(id) > 0;
    }

}