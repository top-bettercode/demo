dependencies {
    implementation(project(":service:core"))
//    implementation("top.bettercode.summer:excel")
//    implementation("top.bettercode.summer:api-sign")

    //    testImplementation("com.h2database:h2")
    testImplementation(project(":util:test"))
}

tasks {
    "startScripts"(CreateStartScripts::class) {
        mainClassName = "${project.group}.Application"
    }
}

generator {
    generators = arrayOf(ModulePackageInfo(), PackageInfo(), MixIn(), Form(), Repository(), MapperXml(), Service(), Controller(), ControllerTest())
//    generators = arrayOf(MixIn(), Form(), Repository(), MapperXml(), Service(), Controller(), ControllerTest())
//    generators = arrayOf(Repository(), MixIn(), Service())

}