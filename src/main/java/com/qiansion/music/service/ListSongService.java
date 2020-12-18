package com.qiansion.music.service;

import com.qiansion.music.domain.ListSong;

import java.util.List;

public interface ListSongService {
    /**
     * 增加
     */
    boolean insert(ListSong listSong);
    /**
     * 删除
     */
    boolean delete(Integer songListId,Integer singerId);
    /**
     * 根据歌单查询歌曲
     */
    List<ListSong> selectByPrimaryKey(Integer songListId);
    /**
     * 查询所有歌曲
     */
    List<ListSong> allSongList();
}
