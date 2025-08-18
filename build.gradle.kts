import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.5.4" apply false
    id("io.spring.dependency-management") version "1.1.6" apply false
    kotlin("jvm") version "1.9.24" apply false
    kotlin("plugin.spring") version "1.9.24" apply false
}

allprojects {
    group = "org.kshrd.cloud"
    version = "1.0.0"

    tasks.withType<JavaCompile> {
        // JDK 21
        sourceCompatibility = JavaVersion.VERSION_21.toString()
        targetCompatibility = JavaVersion.VERSION_21.toString()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "21"
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
