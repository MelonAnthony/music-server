package com.qiansion.music.service.impl;

import com.qiansion.music.dao.AdminMapper;
import com.qiansion.music.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 管理员Service实现类
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;


    /**
     * 验证密码
     *
     * @param username
     * @param password
//     */
    @Override
    public boolean verifyPassword(String username, String password) {
        return adminMapper.verifyPassword(username, password)>0?true:false;
    }
}
