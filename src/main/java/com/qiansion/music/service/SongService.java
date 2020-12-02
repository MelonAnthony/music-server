package com.qiansion.music.service;

import com.qiansion.music.domain.Song;

import java.util.List;

public interface SongService {
    /**
     * 增加
     */
    boolean insert(Song song);
    /**
     * 修改
     */
    boolean update(Song song);
    /**
     * 删除
     */
    boolean delete(Integer id);
    /**
     * 根据主键查询歌曲
     */
    Song selectByPrimaryKey(Integer id);
    /**
     * 查询所有歌曲
     */
    List<Song> allSong();
    /**
     * 根据歌名字模糊查询列表
     */
    List<Song> songOfName(String name);
    /**
     * 根据歌手id查询
     */
    List<Song> songOfSingerId(Integer singerId);
    /**
     * 根据歌曲id查询歌曲url
     */
    String getUrlofSongById(Integer id);
}
