package com.git.hitzaki.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * token配置类
 * @author hitzaki
 */
@Configuration
public class TokenConfig { // TODO 将笔记整理一下, 然后复习一下这个作用

    // TODO 变成配置中心获取, 密钥放在naocos安全吗?
    String SIGNING_KEY = "mq123";


//    @Bean
//    public TokenStore tokenStore() {
//        //使用内存存储令牌（普通令牌）
//        return new InMemoryTokenStore();
//    }

    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(SIGNING_KEY);
        return converter;
    }


}
