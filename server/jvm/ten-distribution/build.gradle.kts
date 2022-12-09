plugins {
    distribution
}

dependencies {
    implementation(project(":ten-config"))
    implementation(project(":ten-dictionary-cache"))
    implementation(project(":ten-eventhandler"))
    implementation(project(":ten-messages"))
    implementation(project(":ten-script-config"))
    implementation(project(":ten-camel-libs"))
}

description = "ten-distribution"

distributions {
    main {
        contents {
            // Octal conversion for file permissions
            val libPermissions = "600".toInt(8)
            val scriptPermissions = "700".toInt(8)
            into("ten/bin") {
                from(configurations.runtimeClasspath)
                exclude("ten-config*.jar")
                exclude("ten-script-config*.jar")
                exclude("ten-distribution*.jar")
                include("ten-*.jar")
            }
            into("ten/lib") {
                from("${project.rootProject.buildDir}/dependencies")
                duplicatesStrategy = DuplicatesStrategy.EXCLUDE

                exclude("genesis-*.jar")
                exclude("ten-*.jar")
                exclude("genesis-*.zip")
                exclude("genesisproduct-*.zip")
                exclude("auth-*.zip")

                fileMode = libPermissions
            }
            into("ten/cfg") {
                from("${project.rootProject.projectDir}/ten-config/src/main/resources/cfg")
                from(project.layout.buildDirectory.dir("generated/product-details"))
                filter(
                    org.apache.tools.ant.filters.FixCrLfFilter::class,
                    "eol" to org.apache.tools.ant.filters.FixCrLfFilter.CrLf.newInstance("lf")
                )
            }
            into("ten/scripts") {
                from("${project.rootProject.projectDir}/ten-config/src/main/resources/scripts")
                from("${project.rootProject.projectDir}/ten-script-config/src/main/resources/scripts")
                filter(
                    org.apache.tools.ant.filters.FixCrLfFilter::class,
                    "eol" to org.apache.tools.ant.filters.FixCrLfFilter.CrLf.newInstance("lf")
                )
                fileMode = scriptPermissions
            }
            // Removes intermediate folder called with the same name as the zip archive.
            into("/")
        }
    }
}

val distribution by configurations.creating {
    isCanBeConsumed = true
    isCanBeResolved = false
}

// To give custom name to the distribution package
tasks {
    distZip {
        archiveBaseName.set("genesisproduct-ten")
        archiveClassifier.set("bin")
        archiveExtension.set("zip")
    }
    copyDependencies {
        enabled = false
    }
}

artifacts {
    val distzip = tasks.distZip.get()
    add("distribution", distzip.archiveFile) {
        builtBy(distzip)
    }
}

publishing {
    publications {
        create<MavenPublication>("tenServerDistribution") {
            artifact(tasks.distZip.get())
        }
    }
}

description = "ten-distribution"
