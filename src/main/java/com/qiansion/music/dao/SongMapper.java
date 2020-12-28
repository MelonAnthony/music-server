package com.qiansion.music.dao;

import com.qiansion.music.domain.Song;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 歌曲DAO
 */
@Repository
public interface SongMapper {
    /**
     * 增加
     */
    int insert(Song song);
    /**
     * 修改
     */
    int update(Song song);
    /**
     * 删除
     */
    int delete(Integer id);
    /**
     * 根据主键查询歌曲
     */
    Song selectByPrimaryKey(Integer id);
    /**
     * 查询所有歌曲
     */
    List<Song> allSong();
    /**
     * 根据歌名字精确查询列表
     */
    List<Song> songOfName(String name);
    /**
     * 根据歌名字模糊查询列表
     */
    List<Song> likeSongOfName(String name);
    /**
     * 根据歌手id查询
     */
    List<Song> songOfSingerId(Integer singerId);
    /**
     * 根据歌曲id查询歌曲url
     */
    String getUrlofSongById(Integer id);
}
