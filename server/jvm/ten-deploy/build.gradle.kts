plugins {
    id("global.genesis.deploy")
}

description = "ten-deploy"

dependencies {
    genesisServer(
        group = "global.genesis",
        name = "genesis-distribution",
        version = properties["genesisVersion"].toString(),
        classifier = "bin",
        ext = "zip"
    )
    genesisServer(
        group = "global.genesis",
        name = "auth-distribution",
        version = properties["authVersion"].toString(),
        classifier = "bin",
        ext = "zip"
    )

    genesisServer(project(":ten-distribution", "distribution"))
    genesisServer(project(":ten-site-specific", "distribution"))
    genesisWeb("client:web")

    api(project(":ten-eventhandler"))
    api(project(":ten-messages"))
    api(project(":ten-camel-libs"))
    // Add additional dependencies on other external distributions here
}
tasks {
    copyDependencies {
        from(configurations.getByName("genesisServer"))
    }
}
