package com.git.hitzaki.auth.auth.controller;

import com.git.hitzaki.auth.auth.mapper.AuthUserMapper;
import com.git.hitzaki.auth.auth.model.po.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.git.hitzaki.base.model.RestResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试controller
 * @author hitzaki
 */
@Slf4j
@RestController
public class LoginController {

    @Autowired
    AuthUserMapper userMapper;


    @RequestMapping("/login-success")
    public String loginSuccess() {

        return "登录成功";
    }

    @PostMapping("/v1/login")
    public RestResponse handlerLogin(){
        System.out.println("登录");
        Map<String, Object> map = new HashMap<>();
        map.put("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsib3RoZXJfYXV0aCJdLCJ1c2VyX25hbWUiOiJ7XCJpZFwiOlwiMVwiLFwibmFtZVwiOlwi5biF5q-U6L6wXCIsXCJuaWNrbmFtZVwiOlwi5biF5q-UXCIsXCJwZXJtaXNzaW9uc1wiOltdLFwidXNlcm5hbWVcIjpcImFkbWluXCJ9Iiwic2NvcGUiOlsiYWxsIl0sImV4cCI6MTcxNjk4MjgxNiwiYXV0aG9yaXRpZXMiOlsieGNfc3lzbWFuYWdlcl9kb2MiLCJ4Y19zeXNtYW5hZ2VyX3JvbGUiLCJ4Y19zeXNtYW5hZ2VyX3VzZXJfdmlldyIsInhjX3N5c21hbmFnZXJfcm9sZV9lZGl0IiwieGNfc3lzbWFuYWdlcl91c2VyX2FkZCIsInhjX3N5c21hbmFnZXJfbWVudSIsInhjX3N5c21hbmFnZXJfY29tcGFueSIsInhjX3N5c21hbmFnZXJfdXNlcl9kZWxldGUiLCJ4Y19zeXNtYW5hZ2VyX3JvbGVfYWRkIiwieGNfc3lzbWFuYWdlcl9yb2xlX3Blcm1pc3Npb24iLCJ4Y19zeXNtYW5hZ2VyX3VzZXIiLCJ4Y19zeXNtYW5hZ2VyIiwieGNfc3lzbWFuYWdlcl9sb2ciLCJ4Y19zeXNtYW5hZ2VyX3VzZXJfZWRpdCIsInhjX3N5c21hbmFnZXJfbWVudV9lZGl0IiwieGNfc3lzbWFuYWdlcl9tZW51X2FkZCIsInhjX3N5c21hbmFnZXJfbWVudV9kZWxldGUiLCJ4Y19zeXNtYW5hZ2VyX3JvbGVfZGVsZXRlIl0sImp0aSI6ImIwMTM0ODMzLTFmZWQtNGM3MS04ODAyLTc0ODIyZGNjZjE4MSIsImNsaWVudF9pZCI6IkJpekNlbnRlciJ9.X3oLvLjQVm-0qK2caaDU1XJfJCNWIANNmX20xATnGLs");
        return RestResponse.success(map);
    }


    @GetMapping("/v1/info")
    public RestResponse handlerInfo(){
        Map<String, Object> map = new HashMap<>();
        map.put("roles", "admin");
        map.put("introduction", "我是管理员");
        map.put("avatar","http://192.168.101.65:9000/mediafiles/test/code-wallpaper-18.png");
        map.put("name", "帅比辰");
        return RestResponse.success(map);
    }

    @RequestMapping("/user/{id}")

    public AuthUser getuser(@PathVariable("id") String id) {
        AuthUser xcUser = userMapper.selectById(id);
        return xcUser;
    }

    @RequestMapping("/r/r1")
    @PreAuthorize("hasAuthority('p1')")
    public String r1() {
        return "访问r1资源";
    }

    @RequestMapping("/r/r2")
    @PreAuthorize("hasAuthority('p2')")
    public String r2() {
        return "访问r2资源";
    }



}
