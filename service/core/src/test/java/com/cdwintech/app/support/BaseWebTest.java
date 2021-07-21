package com.cdwintech.app.support;

import top.bettercode.summer.util.test.BaseWebNoAuthTest;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * mockMvc 基础测试类
 *
 * @author Peter Wu
 */
public abstract class BaseWebTest extends BaseWebNoAuthTest {

  @Autowired
  private UserDetailsService userDetailsService;

  @Override
  @BeforeEach
  public void setup() throws Exception {
    super.setup();

    UserDetails userDetails = userDetailsService.loadUserByUsername("root");
    SecurityContextHolder.getContext()
        .setAuthentication(
            new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(), null,
                userDetails.getAuthorities()));
  }


}
