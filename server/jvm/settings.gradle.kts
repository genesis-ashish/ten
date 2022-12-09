rootProject.name = "genesisproduct-ten"

pluginManagement {
    pluginManagement {
        val genesisVersion: String by settings
        val deployPluginVersion: String by settings
        plugins {
            id("global.genesis.build") version genesisVersion
            id("global.genesis.deploy") version deployPluginVersion
        }
    }
    repositories {
        mavenLocal {
            // VERY IMPORTANT!!! EXCLUDE AGRONA AS IT IS A POM DEPENDENCY AND DOES NOT PLAY NICELY WITH MAVEN LOCAL!
            content {
                excludeGroup("org.agrona")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven {
            url = uri("https://genesisglobal.jfrog.io/genesisglobal/dev-repo")
            credentials {
                username = extra.properties["genesisArtifactoryUser"].toString()
                password = extra.properties["genesisArtifactoryPassword"].toString()
            }
        }
    }
}



include("ten-config")
include("ten-messages")
include("ten-eventhandler")
include("ten-camel-libs")
include("ten-script-config")
include("ten-distribution")
include("ten-dictionary-cache")
include("ten-dictionary-cache:ten-generated-sysdef")
include("ten-dictionary-cache:ten-generated-fields")
include("ten-dictionary-cache:ten-generated-dao")
include("ten-dictionary-cache:ten-generated-hft")
include("ten-dictionary-cache:ten-generated-view")
include("ten-deploy")
include("ten-site-specific")

includeBuild("../../client") {
    dependencySubstitution {
        substitute(module("client:web"))
            .using(project(":web"))
    }
}
