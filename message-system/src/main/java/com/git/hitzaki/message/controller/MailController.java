package com.git.hitzaki.message.controller;

import com.git.hitzaki.base.model.RestResponse;
import com.git.hitzaki.message.model.MailMessageParam;
import com.git.hitzaki.message.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 * @author hitzaki
 */
@RestController
public class MailController {
    @Autowired
    private MailService mailService;

    @PostMapping("/mail")
    public RestResponse<Void> sendMail(@RequestBody MailMessageParam param){
        mailService.sendMail(param);
        return RestResponse.success();
    }
}
