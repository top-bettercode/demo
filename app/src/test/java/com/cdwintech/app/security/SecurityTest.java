package com.cdwintech.app.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import top.bettercode.autodoc.gen.Autodoc;
import top.bettercode.simpleframework.web.RespEntity;
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
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
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
@SpringBootTest(webEnvironment = RANDOM_PORT)
@Transactional
public class SecurityTest {

  @Deprecated
  @Autowired
  ClientDetails clientDetails;
  @Autowired
  TestRestTemplate restTemplate;
  TestRestTemplate clientRestTemplate;
  final ObjectMapper objectMapper = new ObjectMapper();
  final String username = "root";
  final String password = DigestUtils.md5DigestAsHex("123456".getBytes());

  @BeforeEach
  public void setUp() {
    Autodoc.setCollectionName("登录授权");
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
//    params.add("openId", "");

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
    Autodoc.setDescription("");
    Autodoc.setName("获取accessToken");
    Autodoc.requiredParameters("grant_type", "scope", "username", "password");
    assertNotNull(getAccessToken());
  }

  /**
   * 刷新token
   *
   * @throws Exception Exception
   */
  @Test
  public void refreshToken() throws Exception {
    Autodoc.disable();
    MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
    params.add("grant_type", "refresh_token");
    params.add("scope", "trust");
//    params.add("openId", "");
    params.add("refresh_token", getAccessToken().getRefreshToken().getValue());
    Autodoc.enable();
    Autodoc.setName("刷新accessToken");
    Autodoc.requiredParameters("grant_type", "scope", "refresh_token");
    ResponseEntity<String> entity2 = clientRestTemplate
        .postForEntity("/oauth/token", new HttpEntity<>(params), String.class);
    assertEquals(HttpStatus.OK, entity2.getStatusCode());

  }


  @Test
  public void revokeToken() throws Exception {
    Autodoc.disable();
    String accessToken = getAccessToken().getValue();
    Autodoc.enable();
    Autodoc.setName("撤销accessToken");
    Autodoc.setDescription("access_token必须以参数的方式传递");
    Autodoc.requiredParameters("access_token");
    ResponseEntity<String> entity2 = clientRestTemplate
        .exchange("/oauth/token?access_token=" + accessToken,
            HttpMethod.DELETE, null,
            String.class);
    assertEquals(HttpStatus.OK, entity2.getStatusCode());
  }


}
