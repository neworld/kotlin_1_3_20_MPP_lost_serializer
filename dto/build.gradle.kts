import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

apply {
    plugin("kotlin-platform-common")
}

dependencies {
    "compile"(project(":engine"))

    "compile"(kotlin("stdlib-common"))
    "compile"("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:${Versions.kotlinSerializer}")}
