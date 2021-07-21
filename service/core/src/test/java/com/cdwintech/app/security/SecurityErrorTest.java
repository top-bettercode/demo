package com.cdwintech.app.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import top.bettercode.autodoc.gen.Autodoc;
import com.cdwintech.app.TestApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * @author Peter Wu
 * @since 1.0.0
 */
@SuppressWarnings("deprecation")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplication.class, webEnvironment = RANDOM_PORT)
@Transactional
public class SecurityErrorTest {

  @Autowired
  ClientDetails clientDetails;
  @Autowired
  TestRestTemplate restTemplate;
  TestRestTemplate clientRestTemplate;
  final String username = "root";

  @BeforeEach
  public void setUp() {
    clientRestTemplate = restTemplate.withBasicAuth(clientDetails.getClientId(),
        clientDetails.getClientSecret());
  }

  @Test
  public void unAuth() {
    Autodoc.disable();
    HttpHeaders httpHeaders = new HttpHeaders();
    ResponseEntity<String> entity = clientRestTemplate
        .exchange("/test", HttpMethod.GET, new HttpEntity<>(httpHeaders),
            String.class);
    System.err.println(entity.getBody());
    assertEquals(HttpStatus.OK, entity.getStatusCode());
  }

  @Test
  public void unAuthInvalidToken() {
    Autodoc.disable();
    HttpHeaders httpHeaders = new HttpHeaders();
    ResponseEntity<String> entity = clientRestTemplate
        .exchange("/test?access_token=xxx", HttpMethod.GET, new HttpEntity<>(httpHeaders),
            String.class);
    System.err.println(entity.getBody());
    assertEquals(HttpStatus.OK, entity.getStatusCode());
  }

  @Test
  public void expiredToken() {
    Autodoc.disable();
    HttpHeaders httpHeaders = new HttpHeaders();
    ResponseEntity<String> entity = clientRestTemplate
        .exchange(
            "/test?access_token=eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NDE1MzcwODUsInVzZXJfbmFtZSI6InJvb3QiLCJhdXRob3JpdGllcyI6WyJhdXRoZW50aWNhdGVkIl0sImp0aSI6IjM3OGM4ZDllLTM2OWQtNGVkMC05OTBjLWNiODJlZTcxZDdjZiIsImNsaWVudF9pZCI6IkYxM0I0dUNWNWRRYVRTY2ljT3kxZkc0WEZQemxpcXQ1Iiwic2NvcGUiOlsidHJ1c3QiXX0.Fd1BatiIOw3WZ0xAMmLiy9fysn_MktW_5Ae5BR-9_KaIArGFj92jCPe6WIAZKlNo0ZbzwaQnIVRI_ad1zZsyNC9K8ijt_jb4jsrLvzIvcSgD7xEPGV_GWRggPA-3FQrUvVZma7b5QemuucmEWxalnxhdRBFawabmFHrJSA4K4iI",
            HttpMethod.GET, new HttpEntity<>(httpHeaders),
            String.class);
    System.err.println(entity.getBody());
    assertEquals(HttpStatus.OK, entity.getStatusCode());
  }

  @Test
  public void accessTokenError() {
    Autodoc.disable();
    MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
    params.add("grant_type", "password");
    params.add("scope", "trust");
    params.add("username", username);
    params.add("password", "wrong password");

    ResponseEntity<String> entity = clientRestTemplate
        .postForEntity("/oauth/token", new HttpEntity<>(params),
            String.class);
    System.err.println(entity.getBody());
    assertEquals(HttpStatus.OK, entity.getStatusCode());
  }
}
