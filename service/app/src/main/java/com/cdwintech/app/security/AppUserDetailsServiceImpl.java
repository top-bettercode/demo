package com.cdwintech.app.security;

import java.util.Collections;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * @author Peter Wu
 * @since 0.0.1
 */
@Service
@ConditionalOnWebApplication
public class AppUserDetailsServiceImpl implements UserDetailsService {

  private final PasswordEncoder passwordEncoder;

  public AppUserDetailsServiceImpl(
      PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    return new User(username,
        passwordEncoder.encode(DigestUtils.md5DigestAsHex("123456".getBytes())),
        Collections.emptyList());
  }

}
