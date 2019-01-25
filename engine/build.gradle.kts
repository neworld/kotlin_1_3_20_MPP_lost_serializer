import org.gradle.api.internal.tasks.DefaultSourceSet
import org.gradle.kotlin.dsl.maven
import org.gradle.kotlin.dsl.repositories
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

apply {
    plugin("kotlin-platform-common")
}

dependencies {
    "compile"(kotlin("stdlib-common"))
    "compile"(kotlin("reflect"))
    "compile"("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:${Versions.kotlinSerializer}")
}