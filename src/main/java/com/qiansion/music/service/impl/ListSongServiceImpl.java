package com.qiansion.music.service.impl;

import com.qiansion.music.dao.ListSongMapper;
import com.qiansion.music.domain.ListSong;
import com.qiansion.music.service.ListSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListSongServiceImpl implements ListSongService {
    @Autowired
    private ListSongMapper listSongMapper;
    /**
     * 增加
     *
     * @param songList
     */
    @Override
    public boolean insert(ListSong songList) {
        return listSongMapper.insert(songList)>0;
    }

    /**
     * 删除
     *
     * @param songListId
     * @param singerId
     */
    @Override
    public boolean delete(Integer songListId, Integer singerId) {
        return listSongMapper.delete(songListId,singerId)>0;
    }

    /**
     * 根据歌单查询歌曲
     *
     * @param songListId
     */
    @Override
    public List<ListSong> selectByPrimaryKey(Integer songListId) {
        return listSongMapper.selectByPrimaryKey(songListId);
    }

    /**
     * 查询所有歌曲
     */
    @Override
    public List<ListSong> allSongList() {
        return listSongMapper.allSongList();
    }
}
