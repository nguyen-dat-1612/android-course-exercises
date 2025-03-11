import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

plugins {
  kotlin("jvm") version "2.0.0"
}

group = "com.rxmobileteam"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
}

dependencies {
  testImplementation(kotlin("test"))
  implementation("org.jetbrains:annotations:24.0.1")
  implementation("io.reactivex.rxjava3:rxjava:3.1.8")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0-RC")
}

kotlin {
  jvmToolchain {
    languageVersion.set(JavaLanguageVersion.of(21))
    vendor.set(JvmVendorSpec.AZUL)
  }
}

tasks.test {
  useJUnitPlatform()
}

tasks.withType<KotlinJvmCompile> {
  compilerOptions {
    jvmTarget = JvmTarget.JVM_21
  }
}

java {
  sourceCompatibility = JavaVersion.VERSION_21
  targetCompatibility = JavaVersion.VERSION_21
}
