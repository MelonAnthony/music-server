package com.qiansion.music.dao;

import org.springframework.stereotype.Repository;

/**
 * 管理员DAO
 */
@Repository
public interface AdminMapper {
    /**验证密码*/
    int verifyPassword(String username,String password);
}
