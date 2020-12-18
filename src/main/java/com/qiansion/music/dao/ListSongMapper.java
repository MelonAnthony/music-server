package com.qiansion.music.dao;

import com.qiansion.music.domain.ListSong;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListSongMapper {
    /**
     * 增加
     */
    int insert(ListSong songList);
    /**
     * 删除
     */
    int delete(Integer songListId,Integer singerId);
    /**
     * 根据歌单查询歌曲
     */
    List<ListSong> selectByPrimaryKey(Integer songListId);
    /**
     * 查询所有歌曲
     */
    List<ListSong> allSongList();
}
