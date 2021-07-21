package com.cdwintech.app;

import top.bettercode.lang.util.StringUtil;
import top.bettercode.simpleframework.security.server.IRevokeTokenService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@ConditionalOnWebApplication
public class TestSecurityConfiguration {

  @Bean
  public IRevokeTokenService revokeTokenService() {
    return (securityUser, access_token) -> System.err.println(StringUtil.valueOf(securityUser, true));
  }

}