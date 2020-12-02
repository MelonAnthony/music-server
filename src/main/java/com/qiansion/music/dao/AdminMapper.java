package com.qiansion.music.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 管理员DAO
 */
@Repository
public interface AdminMapper {
    /**验证密码*/
    int verifyPassword(@Param("username") String username,@Param("password") String password);
}
