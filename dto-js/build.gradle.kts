import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.accessors.*
import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile

apply {
    plugin("kotlin-platform-js")
    plugin("kotlinx-serialization")
}

dependencies {
    "expectedBy"(project(":dto"))
    "compile"(project(":engine-js"))
    "compile"("org.jetbrains.kotlinx:kotlinx-serialization-runtime-js:${Versions.kotlinSerializer}")
}

(tasks.getByPath("compileKotlin2Js") as Kotlin2JsCompile).apply {
    kotlinOptions {
        metaInfo = true
        sourceMap = true
        moduleKind = "commonjs"
    }
}
