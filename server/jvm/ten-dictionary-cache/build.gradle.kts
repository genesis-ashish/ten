
// Add your genesis config dependencies here
dependencies {
    implementation(project(":ten-dictionary-cache:ten-generated-dao"))
    implementation(project(":ten-dictionary-cache:ten-generated-fields"))
    implementation(project(":ten-dictionary-cache:ten-generated-hft"))
    implementation(project(":ten-dictionary-cache:ten-generated-sysdef"))
    implementation(project(":ten-dictionary-cache:ten-generated-view"))

    implementation("global.genesis:auth-config:${properties["authVersion"]}")
}

description = "ten-dictionary-cache"
