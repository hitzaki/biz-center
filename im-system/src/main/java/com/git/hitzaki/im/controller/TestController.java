package com.git.hitzaki.im.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 * @author hitzaki
 */
@RestController("/test")
public class TestController {
    @RequestMapping("/success")
    public String loginSuccess() {
        return "登录成功";
    }

}
