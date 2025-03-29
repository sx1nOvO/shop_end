package com.mty.service;

import com.mty.entity.Goods;
import com.mty.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
/**
 * 商品表业务处理
 **/
@Service
public class GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * 分页查询
     */
    public List<Goods> queryAllByLimit(Map mp) {
        return goodsMapper.queryAllByLimit(mp);
    }

    /**
     * 查询所有
     */
    public List<Goods> queryCondition(Goods goods) {
        return goodsMapper.queryCondition(goods);
    }

    /**
     * 通过ID查询单条数据
     */
    public Goods queryById(Integer id) {
        return goodsMapper.queryById(id);
    }

    /**
     * 新增
     */
    public int insert(Goods goods) {
        return goodsMapper.insert(goods);
    }

    /**
     * 修改
     */
    public boolean update(Goods goods) {
        return goodsMapper.update(goods);
    }

    /**
     * 删除
     */
    public boolean deleteById(Integer id) {
        return goodsMapper.deleteById(id) > 0;
    }

}