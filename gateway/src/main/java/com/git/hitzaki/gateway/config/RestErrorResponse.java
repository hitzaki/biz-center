package com.git.hitzaki.gateway.config;

import java.io.Serializable;

/**
 * 错误响应参数
 * @author hitzaki
 */
public class RestErrorResponse implements Serializable {

    private String errMessage;

    public RestErrorResponse(String errMessage){
        this.errMessage= errMessage;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
}
