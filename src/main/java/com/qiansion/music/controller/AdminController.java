package com.qiansion.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.qiansion.music.service.AdminService;
import com.qiansion.music.util.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/login/status")
    public String hello(){
        return "success";
    }

    @PostMapping("/login/status")
    public Object loginStatus(@RequestParam("name") String username,@RequestParam("password") String password, HttpSession session){
        System.out.println("登陆验证开始"+username+":"+password);
        JSONObject jsonObject = new JSONObject();
        if(adminService.verifyPassword(username,password)){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"登陆成功");
            session.setAttribute(Consts.NAME,username);
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"账号或密码错误");
        return jsonObject;
    }
}
