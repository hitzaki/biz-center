package com.git.hitzaki.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * spring安全 配置类
 * @author hitzaki
 */
 @Configuration
 public class SecurityConfig {


  //安全拦截配置
  @Bean
  public SecurityWebFilterChain webFluxSecurityFilterChain(ServerHttpSecurity http) {

   return http.authorizeExchange()
           .pathMatchers("/**").permitAll()
           .anyExchange().authenticated()
           .and().csrf().disable().build();
  }


 }
