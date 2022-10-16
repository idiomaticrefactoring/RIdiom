plugins {
    id("java")
    id("org.jetbrains.intellij") version "1.8.0"
}

group = "com.example"
version = "3.0-SNAPSHOT"

repositories {
    mavenCentral()
}
dependencies {
    implementation("black.ninia:jep:4.0.3")
    implementation("com.alibaba:fastjson:2.0.15")
}
// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    version.set("2021.3.3")
//    version.set("2021.3.3")
    type.set("PY") // Target IDE Platform
    downloadSources.set(false)
    plugins.set(listOf(/* Plugin Dependencies */))
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "11"
        targetCompatibility = "11"
    }


    patchPluginXml {
//        sinceBuild.set("213")
        sinceBuild.set("212")
        untilBuild.set("223.*")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}
