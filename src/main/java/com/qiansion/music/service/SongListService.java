package com.qiansion.music.service;

import com.qiansion.music.domain.SongList;

import java.util.List;

public interface SongListService {

    /**
     * 增加
     */
    boolean insert(SongList songList);
    /**
     * 修改
     */
    boolean update(SongList songList);
    /**
     * 删除
     */
    boolean delete(Integer id);
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
