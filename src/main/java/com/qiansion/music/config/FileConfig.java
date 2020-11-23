package com.qiansion.music.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 解决跨域问题
 */
@Configuration
public class FileConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowCredentials(true);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 歌手头像定位
        registry.addResourceHandler("/img/SingerPic/**").addResourceLocations(
                "file:"+System.getProperty("user.dir")+System.getProperty("file.separator")+"img"+
                        System.getProperty("file.separator")+"SingerPic"+ System.getProperty("file.separator")
        );
        // 歌曲图像定位
        registry.addResourceHandler("/img/SongPic/**").addResourceLocations(
                "file:"+System.getProperty("user.dir")+System.getProperty("file.separator")+"img"+
                        System.getProperty("file.separator")+"SongPic"+ System.getProperty("file.separator")
        );
        // 歌单图像定位
        registry.addResourceHandler("/img/SongListPic/**").addResourceLocations(
                "file:"+System.getProperty("user.dir")+System.getProperty("file.separator")+"img"+
                        System.getProperty("file.separator")+"SongListPic"+ System.getProperty("file.separator")
        );
        // 歌曲资源定位
        registry.addResourceHandler("/song/**").addResourceLocations(
                "file:"+System.getProperty("user.dir")+System.getProperty("file.separator")+"song"+System.getProperty("file.separator")
        );
    }
}
