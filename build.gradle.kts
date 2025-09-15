import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("org.springframework.boot") version "3.5.4" apply false
    id("io.spring.dependency-management") version "1.1.6" apply false
    kotlin("jvm") version "2.2.0" apply false
    kotlin("plugin.spring") version "2.2.0" apply false
}

allprojects {
    group = "org.kshrd.cloud"
    version = "1.0.0"

    tasks.withType<JavaCompile> {
        sourceCompatibility = JavaVersion.VERSION_21.toString()
        targetCompatibility = JavaVersion.VERSION_21.toString()
    }

    tasks.withType<KotlinCompile> {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
            freeCompilerArgs.add("-Xjsr305=strict")
        }
    }
}

subprojects {
    repositories {
        mavenLocal()
        mavenCentral()
    }
    apply {
        plugin("io.spring.dependency-management")
    }
}

repositories {
    mavenCentral()
}
