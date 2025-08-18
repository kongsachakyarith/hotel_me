import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.3" apply false
    id("io.spring.dependency-management") version "1.0.8.RELEASE" apply false
    kotlin("jvm") version "1.7.0" apply false
    kotlin("plugin.spring") version "1.7.0" apply false
}


allprojects {
    group = "org.kshrd.cloud"
    version = "1.0.0"

    tasks.withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
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
//
dependencies {
//    testImplementation(kotlin("test"))

}

//tasks.test {
//    useJUnitPlatform()
//}
//
//tasks.withType<KotlinCompile> {
//    kotlinOptions.jvmTarget = "1.8"
//}