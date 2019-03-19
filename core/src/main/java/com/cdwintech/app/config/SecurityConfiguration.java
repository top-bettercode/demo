package com.cdwintech.app.config;

import top.bettercode.simpleframework.security.resource.IResourceService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.stereotype.Service;

@Deprecated
@Configuration(proxyBeanMethods = false)
@ConditionalOnWebApplication
@EnableAuthorizationServer
public class SecurityConfiguration {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }


  @Service
  public static class ResourceServiceImpl implements IResourceService {

  }

}