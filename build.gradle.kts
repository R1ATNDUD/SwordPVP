import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "2.0.20"
}

group = property("group")!!
version = property("version")!!

val paper_version: String by project

repositories {
    mavenCentral()
    mavenLocal()
    maven { url = uri("https://repo.papermc.io/repository/maven-public/") }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    compileOnly("io.papermc.paper:paper-api:${paper_version}-R0.1-SNAPSHOT")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21

    toolchain.languageVersion.set(JavaLanguageVersion.of(21))

}

kotlin {
    jvmToolchain(21)
}

tasks {
    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    withType<KotlinCompile> {
        compilerOptions {
            noJdk = false
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }

    processResources {
        filesMatching("plugin.yml") {
            expand(project.properties)
        }
    }

    create<Jar>("paperJar") {
        archiveClassifier.set(rootProject.name)
        archiveClassifier.set("")

        from(sourceSets["main"].output)

        doLast {
            copy {
                from(archiveFile)
                into(File("C:/Users/qma60/Desktop/serpl/plugins"))
            }
        }
    }

}