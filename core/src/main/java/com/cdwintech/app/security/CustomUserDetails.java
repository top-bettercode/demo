package com.cdwintech.app.security;

import top.bettercode.simpleframework.security.server.DefaultAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * 自定义 UserDetails
 *
 * @author Peter Wu
 */
public class CustomUserDetails extends User {

  private static final long serialVersionUID = 1L;

  private final Long userId;

  public CustomUserDetails(Long userId, String password,
      GrantedAuthority... authorities) {
    super(SecurityUserHelper.getUsername(userId), password, DefaultAuthority.addDefaultAuthority(authorities));
    this.userId = userId;
  }

  public Long getUserId() {
    return userId;
  }
}