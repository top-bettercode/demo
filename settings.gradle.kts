pluginManagement {
    repositories {
        mavenLocal()
        maven("https://maven.aliyun.com/repository/gradle-plugin/")
        gradlePluginPortal()
        mavenCentral()
    }
}

include("core")
include(":util:test")
include("app")
