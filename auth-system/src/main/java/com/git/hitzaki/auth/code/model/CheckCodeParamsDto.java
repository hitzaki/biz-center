package com.git.hitzaki.auth.code.model;

import lombok.Data;

/**
 * 验证码生成参数类
 * @author hitzaki
 */
@Data
public class CheckCodeParamsDto {

    /**
     * 验证码类型:pic、sms、email等
     */
    private String checkCodeType;

    /**
     * 业务携带参数
     */
    private String param1;
    private String param2;
    private String param3;
}
