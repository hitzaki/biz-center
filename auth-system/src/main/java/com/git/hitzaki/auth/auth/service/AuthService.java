package com.git.hitzaki.auth.auth.service;

import com.git.hitzaki.auth.auth.model.dto.AuthParamsDto;
import com.git.hitzaki.auth.auth.model.dto.AuthUserExt;

/**
 * 认证接口
 * @author hitzaki
 */
public interface AuthService {

  /**
   * @description 认证方法
   * @param authParamsDto 认证参数
   * @return com..ucenter.model.po.XcUser 用户信息
   * @author hitzaki
   */
  AuthUserExt execute(AuthParamsDto authParamsDto);

}
