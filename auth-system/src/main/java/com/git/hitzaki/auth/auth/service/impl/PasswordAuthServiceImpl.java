package com.git.hitzaki.auth.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.git.hitzaki.auth.auth.mapper.AuthUserMapper;
import com.git.hitzaki.auth.auth.model.dto.AuthParamsDto;
import com.git.hitzaki.auth.auth.model.dto.AuthUserExt;
import com.git.hitzaki.auth.auth.model.po.AuthUser;
import com.git.hitzaki.auth.auth.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 账号密码认证
 * @author hitzaki
 */
@Slf4j
@Service("password_authservice")
public class PasswordAuthServiceImpl implements AuthService {

    @Autowired
    AuthUserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;



    //实现账号和密码认证
    @Override
    public AuthUserExt execute(AuthParamsDto authParamsDto) {

//        //得到验证码
//        String checkcode = authParamsDto.getCheckcode();
//        String checkcodekey = authParamsDto.getCheckcodekey();
//        if(StringUtils.isBlank(checkcodekey) || StringUtils.isBlank(checkcode)){
//            throw new RuntimeException("验证码为空");
//
//        }
//
//        //校验验证码,请求验证码服务进行校验
//        Boolean result = checkCodeClient.verify(checkcodekey, checkcode);
//        if(result==null || !result){
//            throw new RuntimeException("验证码错误");
//        }

        //账号
        String username = authParamsDto.getUsername();
        //从数据库查询用户信息
        AuthUser user = userMapper.selectOne(new LambdaQueryWrapper<AuthUser>().eq(AuthUser::getUsername, username));
        if (user == null) {
            //账号不存在
            throw new RuntimeException("账号不存在");
        }
        //比对密码
        String passwordDB = user.getPassword();//正确的密码(加密后)
        String passwordInput = authParamsDto.getPassword();//输入的密码
        boolean matches = passwordEncoder.matches(passwordInput, passwordDB);
        if(!matches){
            throw new RuntimeException("账号或密码错误");
        }
        AuthUserExt xcUserExt = new AuthUserExt();
        BeanUtils.copyProperties(user,xcUserExt);
        return xcUserExt;
    }
}
