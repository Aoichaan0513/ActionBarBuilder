import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.20"
    maven
}

val groupId = "jp.aoichaan0513"
group = groupId
version = "1.1.2"

repositories {
    mavenCentral()
    maven("https://repository.aoichaan0513.jp")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("jp.aoichaan0513", "Kotlin_Utils", "1.1.4")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

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
                    "groupId"(groupId)
                    "artifactId"(rootProject.name)
                    "version"(version)
                }
            }
        }
    }
}