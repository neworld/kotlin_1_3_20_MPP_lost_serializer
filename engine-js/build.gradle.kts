import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile

apply {
    plugin("kotlin-platform-js")
    plugin("kotlinx-serialization")
}

dependencies {
    "compile"(kotlin("stdlib-js"))
    "compile"(kotlin("reflect"))
    "compile"("org.jetbrains.kotlinx:kotlinx-serialization-runtime-js:${Versions.kotlinSerializer}")

    "expectedBy"(project(":engine"))
}

(tasks.getByPath("compileKotlin2Js") as Kotlin2JsCompile).apply {
    kotlinOptions {
        metaInfo = true
        sourceMap = true
        moduleKind = "commonjs"
    }
}
