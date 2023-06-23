plugins {
    kotlin("jvm") version "1.8.21"
    application
    kotlin("plugin.serialization") version "1.8.21"
    id("io.qameta.allure") version "2.11.2"
}

group = "org.example"
version = "1.0-SNAPSHOT"
val allureVersion = "2.13.6"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")

    implementation("org.apache.logging.log4j:log4j-api:2.20.0")
    implementation("org.apache.logging.log4j:log4j-core:2.20.0")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.3")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.3")

    testImplementation("io.qameta.allure:allure-junit5:2.22.2")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed", "standardOut", "standardError")
        showStandardStreams = true
    }
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}

allure {
    adapter {
        allureJavaVersion.set("2.22.2")
        aspectjVersion.set("1.9.5")
        autoconfigure.set(true)
        autoconfigureListeners.set(true)
        aspectjWeaver.set(true)
    }
}