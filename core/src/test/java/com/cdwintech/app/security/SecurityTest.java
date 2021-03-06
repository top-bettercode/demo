package com.cdwintech.app.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import top.bettercode.autodoc.gen.Autodoc;
import top.bettercode.simpleframework.security.ClientDetailsProperties;
import top.bettercode.simpleframework.web.RespEntity;
import com.cdwintech.app.TestApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.IOException;
import org.jetbrains.annotations.NotNull;
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
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * @author Peter Wu
 * @since 1.0.0
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplication.class, webEnvironment = RANDOM_PORT)
@Transactional
public class SecurityTest {

  @Autowired
  ClientDetailsProperties clientDetails;
  @Autowired
  TestRestTemplate restTemplate;
  TestRestTemplate clientRestTemplate;
  final ObjectMapper objectMapper = new ObjectMapper();
  final String username = "root";
  final String password = DigestUtils.md5DigestAsHex("123456".getBytes());

  @BeforeEach
  public void setUp() {

    Autodoc.setCollectionName("????????????");
    Autodoc.requiredHeaders("Authorization");

    clientRestTemplate = restTemplate.withBasicAuth(clientDetails.getClientId(),
        clientDetails.getClientSecret());
  }

  @Deprecated
  @NotNull
  private DefaultOAuth2AccessToken getAccessToken() throws IOException {
    MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
    params.add("grant_type", "password");
    params.add("scope", "trust");
    params.add("username", username);
    params.add("password", password);

    ResponseEntity<String> entity = clientRestTemplate
        .postForEntity("/oauth/token", new HttpEntity<>(params),
            String.class);
    assertEquals(HttpStatus.OK, entity.getStatusCode());
    String body = entity.getBody();

    RespEntity<DefaultOAuth2AccessToken> resp = objectMapper
        .readValue(body, TypeFactory.defaultInstance().constructParametricType(
            RespEntity.class, DefaultOAuth2AccessToken.class));
    return resp.getData();
  }

  @Test
  public void accessToken() throws IOException {
    Autodoc.setDescription("???????????????????????????root:123456???APP??????????????????????????????18000000000:123456");
    Autodoc.setName("??????accessToken");
    Autodoc.requiredParameters("grant_type", "scope", "username", "password");
    assertNotNull(getAccessToken());
  }

  /**
   * ??????token
   *
   * @throws Exception Exception
   */
  @Test
  public void refreshToken() throws Exception {
    Autodoc.disable();
    MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
    params.add("grant_type", "refresh_token");
    params.add("scope", "trust");
    params.add("refresh_token", getAccessToken().getRefreshToken().getValue());
    Autodoc.enable();
    Autodoc.setName("??????accessToken");
    Autodoc.requiredParameters("grant_type", "scope", "refresh_token");
    ResponseEntity<String> entity2 = clientRestTemplate
        .postForEntity("/oauth/token", new HttpEntity<>(params), String.class);
    assertEquals(HttpStatus.OK, entity2.getStatusCode());

  }

  //  @Test
//  public void revokeToken() throws Exception {
//    Autodoc.disable();
//    String accessToken = getAccessToken().getValue();
//    Autodoc.enable();
//    Autodoc.setDescription("access_token??????????????????????????????");
//    ResponseEntity<String> entity2 = clientRestTemplate
//        .exchange("/oauth/token?access_token=" + accessToken,
//            HttpMethod.DELETE, null,
//            String.class);
//    Assert.assertEquals(HttpStatus.OK, entity2.getStatusCode());
//  }

  @Test
  public void auth() throws IOException {
    Autodoc.disable();
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.set("Authorization", "bearer " + getAccessToken().getValue());
    ResponseEntity<String> entity = clientRestTemplate
        .exchange("/test", HttpMethod.GET, new HttpEntity<>(httpHeaders), String.class);
    assertEquals(HttpStatus.OK, entity.getStatusCode());
  }
}
