package com.cdwintech.app.security;

import top.bettercode.simpleframework.security.resource.AuthenticationHelper;

/**
 * @author Peter Wu
 */
public final class SecurityUserHelper {

  public static final String ID_PREFIX = "#!";

  /**
   * @return 登录客户主键
   */
  public static Long getId() {
      Object principal = AuthenticationHelper.getPrincipal();
    if (principal != null) {
      return getId(principal.toString());
    }
    return null;
  }

  public static Long getId(String username) {
    if (username.startsWith(ID_PREFIX)) {
      try {
        return Long.parseLong(username.substring(ID_PREFIX.length()));
      } catch (NumberFormatException e) {
        return null;
      }
    } else {
      return null;
    }
  }

  public static String getUsername(Long userId) {
    return SecurityUserHelper.ID_PREFIX + userId;
  }

}
