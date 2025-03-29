package com.mty.controller;

import com.mty.config.PassToken;
import com.mty.entity.Stars;
import com.mty.entity.User;
import com.mty.service.StarsService;
import com.mty.service.UserService;
import com.mty.util.UserBasedCollaborativeFiltering;
import org.springframework.beans.factory.annotation.Autowired;
import com.mty.entity.Forum;
import com.mty.service.ForumService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import com.mty.util.Result;

import javax.servlet.http.HttpSession;

/**
 * 帖子表接口
 **/
@RestController
@RequestMapping("/forum")
public class ForumController {

    @Autowired
    private ForumService forumService;
    @Autowired
    private StarsService starsService;
    @Autowired
    private UserService userService;

    /**
     * 分页查询
     */
    @PostMapping("/selectPage")
    public Result selectPage(@RequestBody Map<String,String> mp) {
        PageHelper.startPage(Integer.parseInt(mp.get("currentPage").toString()), Integer.parseInt(mp.get("pagesize").toString()));
        List<Forum> list = forumService.queryAllByLimit(mp);
        for(int i=0;i<list.size();i++){
            if(list.get(i)!=null){
                Stars stars = new Stars();
                stars.setFid(list.get(i).getId());
                List<Stars> starsList = starsService.queryCondition(stars);
                list.get(i).setLikes(starsList.size());
            }
        }
        PageInfo<Forum> pageInfo = new PageInfo<Forum>(list);
        return Result.success(pageInfo);
    }

    /**
     * 查询所有
     */
    @PostMapping("/queryAll")
    public Result queryAll(@RequestBody Forum forum) {
        List<Forum> list = forumService.queryCondition(forum);
        return Result.success(list);
    }

    /**
     * 通过主键查询单条数据
     */
    @GetMapping("/selectOne")
    public Result selectOne(Integer id) {
        Forum forum = forumService.queryById(id);
        return Result.success(forum);
    }

    /**
     * 新增
     */
    @RequestMapping("/add")
    public Result add(@RequestBody Forum forum) {
        Date date = new Date();
        forum.setCreateTime(date);
        forumService.insert(forum);
        return Result.success("操作成功");
    }

    /**
     * 修改
     */
    @RequestMapping("/edit")
    public Result edit(@RequestBody Forum forum) {
        forumService.update(forum);
        return Result.success("操作成功");
    }

    /**
     * 删除
     */
    @GetMapping("/deleteById")
    public Result deleteById(Integer id) {
        forumService.deleteById(id);
        return Result.success("操作成功");
    }

    /**
     * 前端分页查询
     */
    @PostMapping("/frontPage")
    @PassToken
    public Result frontPage(@RequestBody Map<String,String> mp) {
        PageHelper.startPage(Integer.parseInt(mp.get("currentPage").toString()), Integer.parseInt(mp.get("pagesize").toString()));
        List<Forum> list = forumService.queryAllByLimit(mp);
        for(int i=0;i<list.size();i++){
            if(list.get(i)!=null){
                Stars stars = new Stars();
                stars.setFid(list.get(i).getId());
                List<Stars> starsList = starsService.queryCondition(stars);
                list.get(i).setLikes(starsList.size());
            }
        }
        PageInfo<Forum> pageInfo = new PageInfo<Forum>(list);
        return Result.success(pageInfo);
    }


    /**
     * 根据协同过滤算法推荐
     */
    @PostMapping("recommend")
    @PassToken
    public Result recommend(@RequestBody Map<String,Object> mp) {
        if(mp.get("uid")!=null && !mp.get("uid").toString().equals("")){
            User user = userService.queryById(Integer.parseInt(mp.get("uid").toString()));
            if(user==null){
                return Result.success(forumService.recommend());
            }
            List<Stars> starsList = starsService.queryAllByLimit(new HashMap());
            Map<String, Map<String, Double>> ratings = new HashMap<>();
            if(starsList!=null && starsList.size()>0) {
                for(Stars m : starsList) {
                    Map<String, Double> userRatings = null;
                    if(m.getUid()==null || m.getUid()==0){
                        continue;
                    }
                    if(m.getFid()==null || m.getFid()==0){
                        continue;
                    }
                    if(ratings.containsKey(m.getUid().toString())) {
                        userRatings = ratings.get(m.getUid().toString());
                    } else {
                        userRatings = new HashMap<>();
                        ratings.put(m.getUid().toString(), userRatings);
                    }
                    if(userRatings.containsKey(m.getFid().toString())) {
                        userRatings.put(m.getFid().toString(), userRatings.get(m.getFid().toString())+1.0);
                    } else {
                        userRatings.put(m.getFid().toString(), 1.0);
                    }
                    // 用户A:  论坛帖子1：1 论坛帖子2:2 论坛帖子3:1
                    // 用户B:  论坛帖子1：2 论坛帖子2:5 论坛帖子3:1
                    // 用户C:  论坛帖子1：1 论坛帖子2:2
                }
            }
            // 创建协同过滤对象
            UserBasedCollaborativeFiltering filter = new UserBasedCollaborativeFiltering(ratings);
            // 为指定用户推荐物品
            String targetUser = user.getId().toString();
            int numRecommendations = 10;
            List<Forum> result = new ArrayList<>();
            List<String> recommendations = filter.recommendItems(targetUser, numRecommendations);
            if(recommendations.size()>0){
                for (String item : recommendations) {
                    Forum forum = forumService.queryById(Integer.parseInt(item));
                    result.add(forum);
                }
                for(int i=0;i<result.size();i++){
                    if(result.get(i)!=null){
                        Stars stars = new Stars();
                        stars.setFid(result.get(i).getId());
                        List<Stars> starts = starsService.queryCondition(stars);
                        result.get(i).setLikes(starts.size());
                    }
                }
                return Result.success(result);
            }

        }
        List<Forum> list = forumService.recommend();
        for(int i=0;i<list.size();i++){
            if(list.get(i)!=null){
                Stars stars = new Stars();
                stars.setFid(list.get(i).getId());
                List<Stars> starsList = starsService.queryCondition(stars);
                list.get(i).setLikes(starsList.size());
            }
        }
        return Result.success(list);
    }


    /**
     * 前端通过主键查询单条数据
     */
    @GetMapping("/frontOne")
    @PassToken
    public Result frontOne(Integer id,Integer uid) {
        Forum forum = forumService.queryById(id);
        if(forum!=null){
            Stars stars = new Stars();
            stars.setFid(forum.getId());
            List<Stars> starsList = starsService.queryCondition(stars);
            forum.setLikes(starsList.size());
            if(uid!=null){
                Stars stars2 = new Stars();
                stars2.setFid(forum.getId());
                stars2.setUid(uid);
                List<Stars> starsList2 = starsService.queryCondition(stars2);
                if(starsList2.size()>0){
                    forum.setLiked(true);
                }else{
                    forum.setLiked(false);
                }
            }else{
                forum.setLiked(false);
            }
        }
        return Result.success(forum);
    }

    /**
     * 前端查询所有
     */
    @PostMapping("/frontAll")
    @PassToken
    public Result frontAll(@RequestBody Forum forum) {
        List<Forum> list = forumService.queryCondition(forum);
        return Result.success(list);
    }

}