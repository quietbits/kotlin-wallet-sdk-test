import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm") version "1.7.10"
  application
  id("com.ncorti.ktfmt.gradle") version "0.8.0"
}

group = "org.stellar"

version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
  maven("https://jitpack.io")
}

dependencies {
  testImplementation(kotlin("test"))
  implementation("com.github.stellar:java-stellar-sdk:0.34.2")
  implementation("com.google.code.gson:gson:2.9.0")
  implementation("com.squareup.okhttp3:okhttp:4.10.0")
//  implementation("com.squareup.okhttp3:okhttp:3.14.9")
}

tasks.test { useJUnitPlatform() }

tasks.withType<KotlinCompile> { kotlinOptions.jvmTarget = "11" }

ktfmt.googleStyle()

application { mainClass.set("MainKt") }
