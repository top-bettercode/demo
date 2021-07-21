pluginManagement {
    repositories {
        mavenLocal()
        maven("https://maven.aliyun.com/repository/gradle-plugin/")
        gradlePluginPortal()
        mavenCentral()
    }
}

include(":service:core")
include(":service:app")
include(":util:test")
