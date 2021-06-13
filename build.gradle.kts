import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.10"
    maven
}

group = "jp.aoichaan0513"
version = "1.1.8"

repositories {
    mavenCentral()
    maven("https://repository.aoichaan0513.jp")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("jp.aoichaan0513", "Kotlin_Utils", "1.2.1")
}

java {
    withSourcesJar()
    withJavadocJar()
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()

val repo = File(rootDir, "repository")
val uploadArchives: Upload by tasks
uploadArchives.repositories.withConvention(MavenRepositoryHandlerConvention::class) {
    mavenDeployer {
        withGroovyBuilder {
            "repository"("url" to uri("file://${repo.absolutePath}"))
        }

        pom.project {
            withGroovyBuilder {
                "parent" {
                    "groupId"(group)
                    "artifactId"(rootProject.name)
                    "version"(version)
                }
            }
        }
    }
}

val sourcesJar: Jar by tasks
val javadocJar: Jar by tasks
artifacts {
    archives(sourcesJar)
    archives(javadocJar)
}