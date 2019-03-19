plugins {
    `java-library`
}

dependencies {
    api("top.bettercode.summer:config")
    api("top.bettercode.summer:environment")
    api("top.bettercode.summer:data-jpa")
    api("top.bettercode.summer:security-server")
    api("top.bettercode.summer:security-resource")
    api("org.springframework.security:spring-security-rsa")

    api("com.fasterxml.jackson.dataformat:jackson-dataformat-xml")
    api("org.codehaus.woodstox:woodstox-core-asl")

    api("org.springframework.boot:spring-boot-starter-cache")
    api("org.ehcache:ehcache")
    api("org.bouncycastle:bcpkix-jdk15on")
    api("com.github.ulisesbocchio:jasypt-spring-boot-starter")

//    api("com.oracle.database.jdbc:ojdbc8")
    api("mysql:mysql-connector-java")
    if (profilesActive != "release")
        api("org.springframework.boot:spring-boot-starter-websocket")
//    api(fileTree(mapOf("dir" to "../buildSrc/lib")))

//    compileOnly("com.github.axet:kaptcha")

//    testImplementation("com.h2database:h2")
    testImplementation(project(":util:test"))
}

generator {
    generators = arrayOf(ModulePackageInfo(), PackageInfo(), Entity(), Properties(), MethodInfo(), Msg())
//    generators = arrayOf(Entity(), QueryDsl(), MethodInfo(), Msg())
//    generators = arrayOf(Form(), MixIn(), Repository(), MapperXml(), Service())
//    generators = arrayOf(Form(), MixIn(), Repository(), MapperXml(), Service(), Controller(), ControllerTest())

//    generators = arrayOf(Repository(), MapperXml(), Service())

}