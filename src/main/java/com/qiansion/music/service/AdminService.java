package com.qiansion.music.service;


/**
 * 管理员Service接口
 */
public interface AdminService {
    /**验证密码*/
    boolean verifyPassword(String username,String password);
}
