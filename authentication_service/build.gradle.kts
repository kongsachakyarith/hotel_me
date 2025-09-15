plugins {
    id("org.springframework.boot")
    kotlin("jvm")
    kotlin("plugin.spring")
}


dependencies {
    testImplementation(kotlin("test"))
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    runtimeOnly("org.postgresql:r2dbc-postgresql")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:2.3.0")
}
