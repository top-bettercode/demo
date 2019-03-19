apply {
    plugin("summer.project")
}

allprojects {
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
