dependencies {
    implementation("global.genesis:genesis-messages")
    compileOnly(project(path = ":ten-dictionary-cache", configuration = "codeGen"))
}

description = "ten-messages"