import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.accessors.*

apply {
    plugin("kotlin-platform-jvm")
    plugin("kotlinx-serialization")
}

dependencies {
    "expectedBy"(project(":dto"))
    "compile"(project(":engine-jvm"))
    "compile"("org.jetbrains.kotlinx:kotlinx-serialization-runtime:${Versions.kotlinSerializer}")
}
