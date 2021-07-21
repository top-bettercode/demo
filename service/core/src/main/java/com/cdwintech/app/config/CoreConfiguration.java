package com.cdwintech.app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Core 配置
 *
 * @author Peter Wu
 */
@Configuration(proxyBeanMethods = false)
@ComponentScan(basePackages = "com.cdwintech.app")
public class CoreConfiguration {

}
