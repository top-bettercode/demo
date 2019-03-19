package com.cdwintech.app.support;

import top.bettercode.summer.util.test.BaseTest;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class JasyptApplicationTests extends BaseTest {

  @Autowired
  private StringEncryptor stringEncryptor;

  @Test
  public void contextLoads() {
  }

}
