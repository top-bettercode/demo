package com.cdwintech.app.config;

import top.bettercode.simpleframework.web.resolver.JsonStringConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import javax.servlet.MultipartConfigElement;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Rest MVC 配置
 *
 * @author Peter Wu
 */
@EnableConfigurationProperties({MultipartProperties.class})
@Configuration(proxyBeanMethods = false)
@ConditionalOnWebApplication
public class CoreWebMvcConfiguration {

  @Bean
  public MultipartConfigElement multipartConfigElement(MultipartProperties multipartProperties) {
    String tmpDir = "tmp";
    File file = new File(tmpDir).getAbsoluteFile();
    multipartProperties.setLocation(file.getAbsolutePath());
    file.mkdirs();
    return multipartProperties.createMultipartConfig();
  }

  @Bean
  public JsonStringConverter jsonStringConverter(ObjectMapper objectMapper) {
    return new JsonStringConverter(objectMapper);
  }

}
