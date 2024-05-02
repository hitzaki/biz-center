package com.git.hitzaki.auth.code.service;

import com.git.hitzaki.auth.code.model.CheckCodeParamsDto;
import com.git.hitzaki.auth.code.model.CheckCodeResultDto;

/**
 * 验证码接口
 * @author hitzaki
 */
public interface CheckCodeService {


    /**
     * @description 生成验证码
     * @param checkCodeParamsDto 生成验证码参数
     * @return com.git.hitzaki.auth.code.model.CheckCodeResultDto 验证码结果
     * @author hitzaki
    */
     CheckCodeResultDto generate(CheckCodeParamsDto checkCodeParamsDto);

     /**
      * @description 校验验证码
      * @param key
      * @param code
      * @return boolean
      * @author hitzaki
     */
    public boolean verify(String key, String code);


    /**
     * @description 验证码生成器
     * @author hitzaki
    */
    public interface CheckCodeGenerator{
        /**
         * 验证码生成
         * @return 验证码
         */
        String generate(int length);
        

    }

    /**
     * @description key生成器
     * @author hitzaki
     */
    public interface KeyGenerator{

        /**
         * key生成
         * @return 验证码
         */
        String generate(String prefix);
    }


    /**
     * @description 验证码存储
     * @author hitzaki
     */
    public interface CheckCodeStore{

        /**
         * @description 向缓存设置key
         * @param key key
         * @param value value
         * @param expire 过期时间,单位秒
         * @return void
         * @author hitzaki
        */
        void set(String key, String value, Integer expire);

        String get(String key);

        void remove(String key);
    }
}
