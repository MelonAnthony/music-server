package com.qiansion.music.dao;

import com.qiansion.music.domain.SongList;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 歌曲DAO
 */
@Repository
public interface SongListMapper {
    /**
     * 增加
     */
    int insert(SongList songList);
    /**
     * 修改
     */
    int update(SongList songList);
    /**
     * 删除
     */
    int delete(Integer id);
    /**
     * 根据主键查询歌单
     */
    SongList selectByPrimaryKey(Integer id);
    /**
     * 查询所有歌单
     */
    List<SongList> allSongList();
    /**
     * 根据标题精确查询歌单
     */
    List<SongList> songListOfTitle(String title);
    /**
     * 根据标题模糊查询歌单
     */
    List<SongList> likeTitle(String title);
    /**
     * 根据风格模糊查询歌单
     */
    List<SongList> likeStyle(String style);
}
