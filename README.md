# 后端接口

### 采用技术

spring boot 2.x, spring mvc,spring security

jpa,mybatis

### 数据库



### 构建工具

gradle


### 打包部署

配置文件路径：config

PP参数为环境变量，可选值：test,release

构建命令：./gradlew clean app:distZip -PP=test

jenkins构建Tasks参数：clean app:distZip -PP=test
打包结果路径：app/build/distributions/app.zip

