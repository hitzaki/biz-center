package com.git.hitzaki.auth.auth.controller;

import com.git.hitzaki.auth.auth.model.po.AuthUser;
import com.git.hitzaki.auth.auth.service.impl.WxAuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

/**
 * 微信登录
 * @author hitzaki
 */
 @Controller
public class WxLoginController {

  @Autowired
  WxAuthServiceImpl wxAuthService;


  @RequestMapping("/wxLogin")
  public String wxLogin(String code, String state) throws IOException {

     //拿授权码申请令牌，查询用户
   AuthUser xcUser = wxAuthService.wxAuth(code);
   if(xcUser == null){
     //重定向到一个错误页面
     return "redirect:http://www.51xuecheng.cn/error.html";
   }else{
    String username = xcUser.getUsername();
    //重定向到登录页面，自动登录
     return "redirect:http://www.51xuecheng.cn/sign.html?username="+username+"&authType=wx";
   }

  }



}
