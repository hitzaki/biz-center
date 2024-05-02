package com.git.hitzaki.auth.auth.model.dto;

import com.git.hitzaki.auth.auth.model.po.AuthUser;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户扩展信息
 * @author hitzaki
 */
@Data
public class AuthUserExt extends AuthUser {
    //用户权限
    List<String> permissions = new ArrayList<>();
}
