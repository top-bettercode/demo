apply {
    plugin("summer.project")
}

allprojects {

    extensions.configure(io.spring.gradle.dependencymanagement.internal.dsl.StandardDependencyManagementExtension::class) {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:Hoxton.SR8")
            mavenBom("com.alibaba.cloud:spring-cloud-alibaba-dependencies:2.2.5.RELEASE")
        }
        dependencies {
        }
    }


    repositories {
        mavenLocal()
        maven("https://maven.aliyun.com/repository/public/")
        maven("https://s01.oss.sonatype.org/content/groups/public/")
        mavenCentral()
    }

    tasks {
        "compileJava"(JavaCompile::class) {
//            options.compilerArgs.add("-Xlint:deprecation")
        }
    }
}
