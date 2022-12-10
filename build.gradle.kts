plugins {
    kotlin("js") version "1.7.20"
}

group = "net.sergeych"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
    maven("https://maven.universablockchain.com")
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.3")
    api("net.sergeych:boss-serialization-mp:0.2.4-SNAPSHOT")

    testImplementation(kotlin("test"))
}

kotlin {
    js {
        binaries.executable()
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
    }
}