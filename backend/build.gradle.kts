import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.2.1.RELEASE"
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
    kotlin("jvm") version "1.3.50"
    kotlin("plugin.spring") version "1.3.50"
    kotlin("plugin.jpa") version "1.3.50"
    id("com.google.cloud.tools.jib") version "1.8.0"
    id("org.owasp.dependencycheck") version "5.2.4"
    id("jacoco")
}

jib.from.image = "openjdk:8-jdk-alpine"
jib.to.image = "localhost:5000/trace"

group = "com.hw.tools.route"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
    maven {
        url = uri("http://maven.aliyun.com/nexus/content/groups/public/")
    }
    maven {
        url = uri("https://repo.opennms.org/maven2/")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-websocket")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.flywaydb:flyway-core")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.session:spring-session-core")
    /*TODO coroutines kotlin, 协程支持, 大概是不需要使用到依赖包*/
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.2")
    runtimeOnly("com.h2database:h2")

    implementation("io.springfox:springfox-swagger-ui:2.9.2")
    implementation("io.springfox:springfox-swagger2:2.9.2")

    implementation("com.maxmind.geoip2:geoip2:2.12.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}
