package com.qiansion.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.qiansion.music.domain.ListSong;
import com.qiansion.music.service.ListSongService;
import com.qiansion.music.util.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ListSong")
public class ListSongController {
    @Autowired
    private ListSongService listSongService;
    /**
     * 增加
     */
    @PostMapping("/insert")
    public Object insertSongList(@RequestBody ListSong listSong){
        JSONObject jsonObject =new JSONObject();
        if(listSongService.insert(listSong)){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"添加成功");
        }else{
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"添加失败");
        }
        return jsonObject;
    }
    /**
     * 删除
     */
    @DeleteMapping("/delete/{songListId}/{singerId}")
    public Object deleteSongList(@PathVariable("songListId") Integer id,@PathVariable("singerId") Integer singerId){
        JSONObject jsonObject =new JSONObject();
        //设置默认头像路径
        if(listSongService.delete(id,singerId)){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"添加成功");
        }else{
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"添加失败");
        }
        return jsonObject;
    }
    /**
     * 根据歌单查询歌曲
     */
    @GetMapping("/selectByPrimaryKey/{songListId}")
    public Object selectByPrimaryKey(@PathVariable("songListId") Integer songListId){
        JSONObject jsonObject =new JSONObject();
        List<ListSong> listSong;
        try {
            listSong = listSongService.selectByPrimaryKey(songListId);
        } catch (Exception e) {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"查询失败");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,1);
        jsonObject.put(Consts.MSG,"查询成功");
        jsonObject.put(Consts.DATA,listSong);
        return jsonObject;
    }
    /**
     * 查询所有歌曲
     */
    @GetMapping("/allSongList")
    public Object allSongList(){
        JSONObject jsonObject =new JSONObject();
        List<ListSong> listSongs;
        try {
            listSongs = listSongService.allSongList();
        } catch (Exception e) {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"查询失败");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,1);
        jsonObject.put(Consts.MSG,"查询成功");
        jsonObject.put(Consts.DATA,listSongs);
        return jsonObject;
    }

}
