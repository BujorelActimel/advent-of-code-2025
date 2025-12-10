plugins {
    kotlin("jvm") version "2.1.0"
    application
}

group = "aoc"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.google.ortools:ortools-java:9.8.3296") // lmao
}

kotlin {
    jvmToolchain(21)
}

application {
    mainClass.set("MainKt")
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}
