package com.qiansion.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.qiansion.music.dao.SongMapper;
import com.qiansion.music.domain.Singer;
import com.qiansion.music.domain.Song;
import com.qiansion.music.service.SongService;
import com.qiansion.music.util.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/song")
public class SongController {

    @Autowired
    private SongService songService;
    /**
     * 增加
     */
    @PostMapping("/add")
    public Object insert(@RequestBody Song song, @RequestParam("file")MultipartFile mpFile){
        JSONObject jsonObject =new JSONObject();
        System.out.println(song.toString());
        if(mpFile.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"歌曲上传失败");
            return jsonObject;
        }
        // 防止文件名重复:文件名 = 当前时间毫秒+原来的文件名
        String fileName = System.currentTimeMillis()+mpFile.getOriginalFilename();
        // 如果文件路径不存在则新增
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"song";
        File file1 = new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        // 实际的文件地址
        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
        // 存储到数据库的相对文件地址
        String storeSongPath = "/song/"+fileName;
        try {
            mpFile.transferTo(dest);
            song.setUrl(storeSongPath);
            boolean flag = songService.insert(song);
            if(flag){
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"添加成功");
                jsonObject.put("pic",storeSongPath);
            }else{
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"添加失败");
            }
        } catch (IOException e) {
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"歌曲存储失败"+e.getMessage());
        }finally {
            return jsonObject;
        }
    }
//    @PostMapping("/insert")
//    public Object insert(@RequestBody Song song){
//        JSONObject jsonObject =new JSONObject();
//        if(songService.insert(song)){
//            jsonObject.put(Consts.CODE,1);
//            jsonObject.put(Consts.MSG,"添加成功");
//        }else{
//            jsonObject.put(Consts.CODE,0);
//            jsonObject.put(Consts.MSG,"添加失败");
//        }
//        return jsonObject;
//    }
    /**
     * 修改
     */
    @PostMapping("/update")
    public Object update(@RequestBody Song song){
        JSONObject jsonObject =new JSONObject();
        if(songService.update(song)){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"更新成功");
        }else{
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"更新失败");
        }
        return jsonObject;
    }
    /**
     * 删除
     */
    @GetMapping("/delete/{id}")
    public Object delete(@PathVariable("id") Integer id){
        JSONObject jsonObject =new JSONObject();
        if(songService.delete(id)){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"添加成功");
        }else{
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"添加失败");
        }
        return jsonObject;
    }
    /**
     * 根据主键查询歌曲
     */
    @GetMapping("/selectByPrimaryKey/{id}")
    public Object selectByPrimaryKey(@PathVariable("id") Integer id){
        JSONObject jsonObject =new JSONObject();
        Song song;
        try {
            song = songService.selectByPrimaryKey(id);
        } catch (Exception e) {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"查询失败");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,1);
        jsonObject.put(Consts.MSG,"查询成功");
        jsonObject.put(Consts.DATA,song);
        return jsonObject;
    }
    /**
     * 查询所有歌曲
     */
    @GetMapping("/allSong")
    public Object allSong(){
        JSONObject jsonObject =new JSONObject();
        List<Song> songs;
        try {
            songs = songService.allSong();
        } catch (Exception e) {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"查询失败");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,1);
        jsonObject.put(Consts.MSG,"查询成功");
        jsonObject.put(Consts.DATA,songs);
        return jsonObject;
    }
    /**
     * 根据歌名字模糊查询列表
     */
    @GetMapping("/songOfName")
    public Object songOfName(String name){
        JSONObject jsonObject =new JSONObject();
        List<Song> songs;
        try {
            songs = songService.songOfName(name);
        } catch (Exception e) {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"查询失败");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,1);
        jsonObject.put(Consts.MSG,"查询成功");
        jsonObject.put(Consts.DATA,songs);
        return jsonObject;
    }
    /**
     * 根据歌手id查询
     */
    @GetMapping("/songOfSingerId")
    public Object songOfSingerId(Integer singerId){
        JSONObject jsonObject =new JSONObject();
        List<Song> songs;
        try {
            songs = songService.songOfSingerId(singerId);
        } catch (Exception e) {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"查询失败");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,1);
        jsonObject.put(Consts.MSG,"查询成功");
        jsonObject.put(Consts.DATA,songs);
        return jsonObject;
    }
}
