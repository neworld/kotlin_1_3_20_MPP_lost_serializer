import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.accessors.*

apply {
    plugin("kotlin-platform-jvm")
    plugin("kotlinx-serialization")
}

dependencies {
    "compile"(kotlin("stdlib-jdk8"))
    "compile"(kotlin("reflect"))
    "compile"("org.mongodb:bson:${Versions.mongoDriver}")
    "compile"("com.fasterxml.jackson.core:jackson-annotations:${Versions.jackson}")
    "compile"("org.jetbrains.kotlinx:kotlinx-serialization-runtime:${Versions.kotlinSerializer}")

    "expectedBy"(project(":engine"))

    "testImplementation"("org.junit.jupiter:junit-jupiter-api:${Versions.junit5Version}")
    "testRuntimeOnly"("org.junit.jupiter:junit-jupiter-engine:${Versions.junit5Version}")
    "testImplementation"("org.junit.jupiter:junit-jupiter-params:${Versions.junit5Version}")
    "testImplementation"("org.mockito:mockito-core:${Versions.mockito}")
    "testImplementation"("org.mockito:mockito-inline:${Versions.mockito}")
    "testImplementation"("com.nhaarman:mockito-kotlin:${Versions.mockitoKotlin}")
    "testImplementation"("org.hamcrest:hamcrest-junit:${Versions.hamrest}")
    "testImplementation"("com.fasterxml.jackson.core:jackson-databind:${Versions.jackson}")
    "testImplementation"("com.fasterxml.jackson.module:jackson-module-kotlin:${Versions.jackson}")
}

tasks {
    "test"(Test::class) {
        useJUnitPlatform()
        reports {
            html.isEnabled = true
        }
    }
}
