package com.qiansion.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.qiansion.music.domain.SongList;
import com.qiansion.music.service.SongListService;
import com.qiansion.music.util.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 歌单控制类
 */
@RestController
@RequestMapping("/songList")
public class SongListController {

    @Autowired
    private SongListService songListService;

    @PostMapping("/insert")
    public Object insertSongList(@RequestBody SongList songList){
        JSONObject jsonObject =new JSONObject();
        //设置默认头像路径
        songList.setPic("/img/SongListPic/user.jpg");
        if(songListService.insert(songList)){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"添加成功");
        }else{
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"添加失败");
        }
        return jsonObject;
    }

    @PostMapping("/update")
    public Object updateSongList(@RequestBody SongList songList){
        JSONObject jsonObject =new JSONObject();
        if(songListService.update(songList)){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"修改成功");
        }else{
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"修改失败");
        }
        return jsonObject;
    }

    @GetMapping("/delete/{id}")
    public Object deleteSongList(@PathVariable("id") Integer id){
        JSONObject jsonObject =new JSONObject();
        if(songListService.delete(id)){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"删除成功");
        }else{
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"删除失败");
        }
        return jsonObject;
    }

    /**
     * 根据主键查询歌单
     */
    @GetMapping("/selectByPrimaryKey/{id}")
    public Object selectByPrimaryKey(@PathVariable("id") Integer id){
        JSONObject jsonObject =new JSONObject();
        SongList songList;
        try {
            songList = songListService.selectByPrimaryKey(id);
        } catch (Exception e) {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"查询失败");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,1);
        jsonObject.put(Consts.MSG,"查询成功");
        jsonObject.put(Consts.DATA,songList);
        return jsonObject;
    }

    @GetMapping("/allSongList")
    public Object allSongList(){
        JSONObject jsonObject =new JSONObject();
        List<SongList> songLists;
        try {
            songLists = songListService.allSongList();
        } catch (Exception e) {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"查询失败");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,1);
        jsonObject.put(Consts.MSG,"查询成功");
        jsonObject.put(Consts.DATA,songLists);
        return jsonObject;
    }


    /**
     * 更新歌单图片
     */
    @PostMapping("/updateSongListPic")
    public Object updateSongListPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") int id){
        JSONObject jsonObject = new JSONObject();
        if(avatorFile.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"头像上传失败");
            return jsonObject;
        }
        // 防止文件名重复:文件名 = 当前时间毫秒+原来的文件名
        String fileName = System.currentTimeMillis()+avatorFile.getOriginalFilename();
        // 如果文件路径不存在则新增
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"img"+ System.getProperty("file.separator")+"SongListPic";
        File file1 = new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        // 实际的文件地址
        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
        // 存储到数据库的相对文件地址
        String storeAvatorPath = "/img/SongListPic/"+fileName;
        try {
            avatorFile.transferTo(dest);
            SongList songList = new SongList();
            songList.setId(id);
            songList.setPic(storeAvatorPath);
            boolean flag = songListService.update(songList);
            if(flag){
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"上传成功");
                jsonObject.put("pic",storeAvatorPath);
            }else{
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"上传失败");
            }
        } catch (IOException e) {
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"上传失败"+e.getMessage());
        }finally {
            return jsonObject;
        }
    }
}
