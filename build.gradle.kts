plugins {
    id("java")
    `java-library`
    `maven-publish`
}

group = "net.techcable.utils"
// TODO: Automatic git versioning??
version = "0.1.0"
description = "A single-file utility to bypass checked exceptions"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    manifest {
        attributes["Automatic-Module-Name"] = "net.techcable.utils.sneakythrows"
    }
}

java {
    // Use recent toolchain (Java 17)
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
    withSourcesJar()
    withJavadocJar()
}

tasks.compileJava {
    // Should support java 8
    options.release.set(8)
}

tasks.compileTestJava {
    options.release.set(17)
}

publishing.publications {
    create<MavenPublication>("maven") {
        groupId = project.group.toString()
        artifactId = "sneakythrows"
        version = project.version.toString()

        from(components["java"])

        pom {
            name = "SneakyThrows"
            description = project.description
            url = "https://github.com/Techcable/sneakythrows.java"

            // dual-license
            licenses {
                license {
                    name = "The Apache License, Version 2.0"
                    url = "https://www.apache.org/licenses/LICENSE-2.0.txt"
                }

                license {
                    name = "Creative Commons Zero (CC0)"
                    url = "https://creativecommons.org/public-domain/cc0/"
                }
            }

            developers {
                developer {
                    id = "Techcable"
                    name = "Techcable"
                    email = "git+sneakythrows.java@techcable.net"
                }
            }

            scm {
                url = "https://github.com/Techcable/sneakythrows.java"
                connection = "scm:git:https://github.com/Techcable/sneakythrows.java.git"
                developerConnection = "scm:git:ssh://git@github.com/Techcable/sneakythrows.java.git"
            }
        }
    }
}