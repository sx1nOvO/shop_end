package com.mty.mapper;

import com.mty.entity.Outfit;
import com.mty.util.MyMapper;
import java.util.Map;
import java.util.List;

/**
 * 操作outfitDAO层接口
*/
public interface OutfitMapper extends MyMapper<Outfit> {

    /**
      * 分页查询数据
    */
    List<Outfit> queryAllByLimit(Map mp);

    /**
      * 通过实体作为筛选条件查询
    */
    List<Outfit> queryCondition(Outfit outfit);

    List<Outfit> recommend();

    /**
      * 通过ID查询单条数据
    */
    Outfit queryById(Integer id);

    /**
      * 新增
    */
    int insert(Outfit outfit);

    /**
      * 修改
    */
    boolean update(Outfit outfit);

    /**
      * 删除
    */
    int deleteById(Integer id);

}