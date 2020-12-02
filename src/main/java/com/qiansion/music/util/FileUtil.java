package com.qiansion.music.util;

import java.io.File;

public class FileUtil {
    public static boolean deleteSongFile(String fileName){
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "song";
        String temp[] = fileName.replaceAll("\\\\","/").split("/");
        String destFileName = "";
        if(temp.length > 1){
            destFileName = temp[temp.length - 1];
        }
        destFileName = filePath + System.getProperty("file.separator") + destFileName;
        System.out.println("待删除的路径为:"+destFileName);
        File file = new File(destFileName);
        if(file.isFile() && file.exists()){
            file.delete();
            System.out.println("删除文件："+fileName+"成功");
            return true;
        }else {
            System.out.println("删除文件失败");
            return false;
        }
    }
}
