package com.mty.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 穿搭信息表
*/
public class Outfit implements Serializable {
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;
    /** 图片 */
    private String image;
    /** 类型 */
    private String type;
    /** 季节 */
    private String season;
    /** 名称 */
    private String name;
    /** 内容 */
    private String content;
    /** 发布人 */
    private Integer uid;
    /** 浏览量 */
    private Integer num;
    /** 状态 */
    private String status;
    /** 发布时间 */
    private Date createTime;
    /** 关联表：user */
    private String realname;
    private String userImage;
    private List<Discuss> discusses;
    private Integer comments;


    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public List<Discuss> getDiscusses() {
        return discusses;
    }

    public void setDiscusses(List<Discuss> discusses) {
        this.discusses = discusses;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }
}