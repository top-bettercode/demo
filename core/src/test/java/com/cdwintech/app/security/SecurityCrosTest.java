package com.cdwintech.app.security;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.cdwintech.app.TestApplication;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Peter Wu
 * @since 1.0.0
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplication.class, webEnvironment = RANDOM_PORT)
@Transactional
public class SecurityCrosTest {


  @Autowired
  TestRestTemplate clientRestTemplate;


  @Test
  public void options() {
    Set<HttpMethod> httpMethods = clientRestTemplate.optionsForAllow("/oauth/token");
    System.out.println(httpMethods);
  }

}
