import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.spring") version "1.9.22"
}

group = "com.github.michalperlak"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
    maven {
        url = uri("${System.getProperty("user.home")}/.m2/repository")
    }
    mavenCentral()
}

extra["springModulithVersion"] = "1.1.2"
extra["fsrsVersion"] = "1.0-SNAPSHOT"
extra["vavrVersion"] = "0.10.2"
extra["jjwtVersion"] = "0.12.5"
extra["eclipseStoreVersion"] = "1.3.1"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.modulith:spring-modulith-starter-core")
    implementation("com.github.mpps:fsrs-kt:${property("fsrsVersion")}")
    implementation("io.vavr:vavr-kotlin:${property("vavrVersion")}")
    implementation("io.jsonwebtoken:jjwt-api:${property("jjwtVersion")}")
    implementation("io.jsonwebtoken:jjwt-impl:${property("jjwtVersion")}")
    implementation("io.jsonwebtoken:jjwt-jackson:${property("jjwtVersion")}")
    implementation("org.eclipse.store:integrations-spring-boot3:${property("eclipseStoreVersion")}")
    implementation("org.eclipse.store:integrations-spring-boot3-console:${property("eclipseStoreVersion")}")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.modulith:spring-modulith-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.modulith:spring-modulith-bom:${property("springModulithVersion")}")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "21"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
