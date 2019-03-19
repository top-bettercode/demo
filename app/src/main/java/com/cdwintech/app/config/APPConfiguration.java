package com.cdwintech.app.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Peter Wu
 */
@EnableConfigurationProperties({AppProperties.class})
@Configuration(proxyBeanMethods = false)
public class APPConfiguration {

}
