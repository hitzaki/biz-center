package com.git.hitzaki.auth.auth.service.impl;

import com.alibaba.fastjson.JSON;
import com.git.hitzaki.auth.auth.mapper.AuthMenuMapper;
import com.git.hitzaki.auth.auth.mapper.AuthUserMapper;
import com.git.hitzaki.auth.auth.model.dto.AuthParamsDto;
import com.git.hitzaki.auth.auth.model.dto.AuthUserExt;
import com.git.hitzaki.auth.auth.model.po.AuthMenu;
import com.git.hitzaki.auth.auth.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 重写自带接口 获取用户信息
 * @author hitzaki
 */
@Slf4j
@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    AuthUserMapper userMapper;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    AuthMenuMapper menuMapper;

    //传入的是AuthParamsDto的json串
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AuthParamsDto authParamsDto = null;
        try {
            //将认证参数转为AuthParamsDto类型
            authParamsDto = JSON.parseObject(s, AuthParamsDto.class);
        } catch (Exception e) {
            log.info("认证请求不符合项目要求:{}",s);
            throw new RuntimeException("认证请求数据格式不对");
        }
        //认证方式,
        String authType = authParamsDto.getAuthType();
        //从spring容器中拿具体的认证bean实例
        AuthService authService = applicationContext.getBean(authType + "_authservice", AuthService.class);
        //开始认证,认证成功拿到用户信息
        AuthUserExt xcUserExt = authService.execute(authParamsDto);

        return getUserPrincipal(xcUserExt);
    }
    //根据XcUserExt对象构造一个UserDetails对象
    /**
     * @description 查询用户信息
     * @param user  用户id，主键
     * @return com.ucenter.model.po.XcUser 用户信息
     * @author hitzaki
     */
    public UserDetails getUserPrincipal(AuthUserExt user){

        //权限列表，存放的用户权限
        List<String> permissionList = new ArrayList<>();

        //根据用户id查询数据库中他的权限
        List<AuthMenu> xcMenus = menuMapper.selectPermissionByUserId(user.getId());
        xcMenus.forEach(menu->{
            permissionList.add(menu.getCode());
        });
        if(permissionList.size()==0){
            //用户权限,如果不加报Cannot pass a null GrantedAuthority collection
            permissionList.add("test");
        }

        String[] authorities= permissionList.toArray(new String[0]);
        //原来存的是账号，现在扩展为用户的全部信息(密码不要放)
        user.setPassword(null);
        String jsonString = JSON.toJSONString(user);
        UserDetails userDetails = User.withUsername(jsonString).password("").authorities(authorities).build();

        return userDetails;
    }

}
