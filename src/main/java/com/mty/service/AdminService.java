package com.mty.service;

import com.mty.entity.Admin;
import com.mty.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
/**
 * 管理员表业务处理
 **/
@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;

    /**
     * 分页查询
     */
    public List<Admin> queryAllByLimit(Map mp) {
        return adminMapper.queryAllByLimit(mp);
    }

    /**
     * 查询所有
     */
    public List<Admin> queryCondition(Admin admin) {
        return adminMapper.queryCondition(admin);
    }

    /**
     * 通过ID查询单条数据
     */
    public Admin queryById(Integer id) {
        return adminMapper.queryById(id);
    }

    /**
     * 新增
     */
    public int insert(Admin admin) {
        return adminMapper.insert(admin);
    }

    /**
     * 修改
     */
    public boolean update(Admin admin) {
        return adminMapper.update(admin);
    }

    /**
     * 删除
     */
    public boolean deleteById(Integer id) {
        return adminMapper.deleteById(id) > 0;
    }

}