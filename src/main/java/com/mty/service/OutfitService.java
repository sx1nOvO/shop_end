package com.mty.service;

import com.mty.entity.Outfit;
import com.mty.mapper.OutfitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
/**
 * 穿搭信息表业务处理
 **/
@Service
public class OutfitService {

    @Autowired
    private OutfitMapper outfitMapper;

    /**
     * 分页查询
     */
    public List<Outfit> queryAllByLimit(Map mp) {
        return outfitMapper.queryAllByLimit(mp);
    }

    /**
     * 查询所有
     */
    public List<Outfit> queryCondition(Outfit outfit) {
        return outfitMapper.queryCondition(outfit);
    }

    public List<Outfit> recommend() {
        return outfitMapper.recommend();
    }

    /**
     * 通过ID查询单条数据
     */
    public Outfit queryById(Integer id) {
        return outfitMapper.queryById(id);
    }

    /**
     * 新增
     */
    public int insert(Outfit outfit) {
        return outfitMapper.insert(outfit);
    }

    /**
     * 修改
     */
    public boolean update(Outfit outfit) {
        return outfitMapper.update(outfit);
    }

    /**
     * 删除
     */
    public boolean deleteById(Integer id) {
        return outfitMapper.deleteById(id) > 0;
    }

}