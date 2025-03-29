package com.mty.controller;

import com.mty.config.PassToken;
import com.mty.entity.Discuss;
import com.mty.service.DiscussService;
import org.springframework.beans.factory.annotation.Autowired;
import com.mty.entity.Outfit;
import com.mty.service.OutfitService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.List;
import java.util.Date;
import com.mty.util.Result;

/**
 * 穿搭信息表接口
 **/
@RestController
@RequestMapping("/outfit")
public class OutfitController {

    @Autowired
    private OutfitService outfitService;
    @Autowired
    private DiscussService discussService;


    /**
     * 分页查询
     */
    @PostMapping("/selectPage")
    public Result selectPage(@RequestBody Map<String,String> mp) {
        PageHelper.startPage(Integer.parseInt(mp.get("currentPage").toString()), Integer.parseInt(mp.get("pagesize").toString()));
        List<Outfit> list = outfitService.queryAllByLimit(mp);
        PageInfo<Outfit> pageInfo = new PageInfo<Outfit>(list);
        return Result.success(pageInfo);
    }

    /**
     * 查询所有
     */
    @PostMapping("/queryAll")
    public Result queryAll(@RequestBody Outfit outfit) {
        List<Outfit> list = outfitService.queryCondition(outfit);
        return Result.success(list);
    }

    /**
     * 通过主键查询单条数据
     */
    @GetMapping("/selectOne")
    public Result selectOne(Integer id) {
        Outfit outfit = outfitService.queryById(id);
        return Result.success(outfit);
    }

    /**
     * 新增
     */
    @RequestMapping("/add")
    public Result add(@RequestBody Outfit outfit) {
        Date date = new Date();
        outfit.setCreateTime(date);
        outfitService.insert(outfit);
        return Result.success("操作成功");
    }

    /**
     * 修改
     */
    @RequestMapping("/edit")
    public Result edit(@RequestBody Outfit outfit) {
        outfitService.update(outfit);
        return Result.success("操作成功");
    }

    /**
     * 删除
     */
    @GetMapping("/deleteById")
    public Result deleteById(Integer id) {
        outfitService.deleteById(id);
        return Result.success("操作成功");
    }

    /**
     * 前端分页查询
     */
    @PostMapping("/frontPage")
    @PassToken
    public Result frontPage(@RequestBody Map<String,String> mp) {
        PageHelper.startPage(Integer.parseInt(mp.get("currentPage").toString()), Integer.parseInt(mp.get("pagesize").toString()));
        List<Outfit> list = outfitService.queryAllByLimit(mp);
        PageInfo<Outfit> pageInfo = new PageInfo<Outfit>(list);
        return Result.success(pageInfo);
    }

    /**
     * 前端通过主键查询单条数据
     */
    @GetMapping("/frontOne")
    @PassToken
    public Result frontOne(Integer id) {
        //更新浏览量
        Outfit query = outfitService.queryById(id);
        Outfit update = new Outfit();
        update.setNum(query.getNum()+1);
        update.setId(id);
        outfitService.update(update);
        //获取
        Outfit outfit = outfitService.queryById(id);
        if(outfit!=null){
            Discuss discuss = new Discuss();
            discuss.setOid(outfit.getId());
            List<Discuss> discusses = discussService.queryCondition(discuss);
            outfit.setDiscusses(discusses);
        }

        return Result.success(outfit);
    }

    /**
     * 前端查询所有
     */
    @PostMapping("/frontAll")
    @PassToken
    public Result frontAll(@RequestBody Outfit outfit) {
        List<Outfit> list = outfitService.queryCondition(outfit);
        return Result.success(list);
    }


    @PostMapping("/recommend")
    @PassToken
    public Result recommend() {
        List<Outfit> list = outfitService.recommend();
        return Result.success(list);
    }
}