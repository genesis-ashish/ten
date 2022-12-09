dependencies {
    implementation("global.genesis:genesis-pal-execution")
    implementation("global.genesis:genesis-eventhandler")
    implementation(project(":ten-messages"))
    api("global.genesis:genesis-db")
    compileOnly(project(":ten-config"))
    compileOnly(project(path = ":ten-dictionary-cache", configuration = "codeGen"))
    testImplementation("global.genesis:genesis-dbtest")
    testImplementation("global.genesis:genesis-testsupport")
    testImplementation(project(path = ":ten-dictionary-cache", configuration = "codeGen"))
}

description = "ten-eventhandler"