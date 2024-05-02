package com.git.hitzaki.auth.auth.model.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author hitzaki
 */
@Data
@TableName("auth_teacher")
public class AuthTeacher implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 称呼
     */
    private String name;

    /**
     * 个人简介
     */
    private String intro;

    /**
     * 个人简历
     */
    private String resume;

    /**
     * 老师照片
     */
    private String pic;


}
