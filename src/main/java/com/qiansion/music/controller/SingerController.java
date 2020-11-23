package com.qiansion.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.qiansion.music.domain.Singer;
import com.qiansion.music.service.SingerService;
import com.qiansion.music.util.Consts;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 歌手控制类
 */
@RestController
@RequestMapping("/singer")
public class SingerController {

    @Autowired
    private SingerService singerService;

    @PostMapping("/insert")
    public Object insertSinger(@RequestBody Singer singer){
        JSONObject jsonObject =new JSONObject();
        //设置默认头像路径
        singer.setPic("/img/SingerPic/user.jpg");
        if(singerService.insert(singer)){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"添加成功");
        }else{
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"添加失败");
        }
        return jsonObject;
    }

    @PostMapping("/update")
    public Object updateSinger(@RequestBody Singer singer){
        JSONObject jsonObject =new JSONObject();
        if(singerService.update(singer)){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"修改成功");
        }else{
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"修改失败");
        }
        return jsonObject;
    }

    @GetMapping("/delete/{id}")
    public Object deleteSinger(@PathVariable("id") Integer id){
        JSONObject jsonObject =new JSONObject();
        if(singerService.delete(id)){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"删除成功");
        }else{
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"删除失败");
        }
        return jsonObject;
    }

    /**
     * 根据主键查询歌手
     */
    @GetMapping("/selectByPrimaryKey/{id}")
    public Object selectByPrimaryKey(@PathVariable("id") Integer id){
        JSONObject jsonObject =new JSONObject();
        Singer singer;
        try {
            singer = singerService.selectByPrimaryKey(id);
        } catch (Exception e) {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"查询失败");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,1);
        jsonObject.put(Consts.MSG,"查询成功");
        jsonObject.put(Consts.DATA,singer);
        return jsonObject;
    }

    @GetMapping("/allSinger")
    public Object allSinger(){
        JSONObject jsonObject =new JSONObject();
        List<Singer> singers;
        try {
            singers = singerService.allSinger();
        } catch (Exception e) {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"查询失败");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,1);
        jsonObject.put(Consts.MSG,"查询成功");
        jsonObject.put(Consts.DATA,singers);
        return jsonObject;
    }

    /**
     * 根据歌手名字模糊查询列表
     */
    @GetMapping("/singerOfName/{name}")
    public Object singerOfName(@PathVariable("name") String name){
        JSONObject jsonObject =new JSONObject();
        List<Singer> singers;
        try {
            singers = singerService.singerOfName("%"+name+"%");
        } catch (Exception e) {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"查询失败");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,1);
        jsonObject.put(Consts.MSG,"查询成功");
        jsonObject.put(Consts.DATA,singers);
        return jsonObject;
    }

    /**
     * 根据性别查询
     */
    @GetMapping("/singerOfSex/{sex}")
    public Object singerOfSex(@PathVariable("sex") Integer sex){
        JSONObject jsonObject =new JSONObject();
        List<Singer> singers;
        try {
            singers = singerService.singerOfSex(sex);
        } catch (Exception e) {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"查询失败");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,1);
        jsonObject.put(Consts.MSG,"查询成功");
        jsonObject.put(Consts.DATA,singers);
        return jsonObject;
    }

    /**
     * 更新歌手图片
     */
    @PostMapping("/updateSingerPic")
    public Object updateSingerPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") int id){
        JSONObject jsonObject = new JSONObject();
        if(avatorFile.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"头像上传失败");
            return jsonObject;
        }
        // 防止文件名重复:文件名 = 当前时间毫秒+原来的文件名
        String fileName = System.currentTimeMillis()+avatorFile.getOriginalFilename();
        // 如果文件路径不存在则新增
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"img"+ System.getProperty("file.separator")+"SingerPic";
        File file1 = new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        // 实际的文件地址
        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
        // 存储到数据库的相对文件地址
        String storeAvatorPath = "/img/SingerPic/"+fileName;
        try {
            avatorFile.transferTo(dest);
            Singer singer = new Singer();
            singer.setId(id);
            singer.setPic(storeAvatorPath);
            boolean flag = singerService.update(singer);
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
