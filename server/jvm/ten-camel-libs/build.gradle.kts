dependencies {
    implementation("global.genesis:genesis-pal-execution")
    implementation("global.genesis:genesis-pal-camel")
    implementation("global.genesis:genesis-eventhandler")
    implementation("org.apache.camel:camel-stream:3.19.0")
    implementation("org.apache.camel:camel-http:3.19.0")
    implementation("org.apache.camel:camel-management:3.19.0")
    implementation("org.apache.camel:camel-console:3.19.0")
    implementation("org.apache.camel:camel-cli-connector:3.19.0")
    implementation(project(":ten-messages"))
    api("global.genesis:genesis-db")
    compileOnly(project(":ten-config"))
    compileOnly(project(path = ":ten-dictionary-cache", configuration = "codeGen"))
    testImplementation("global.genesis:genesis-dbtest")
    testImplementation("global.genesis:genesis-testsupport")
    testImplementation(project(path = ":ten-dictionary-cache", configuration = "codeGen"))
}

description = "ten-camel-libs"
