import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.repositories
import org.jetbrains.kotlin.gradle.frontend.KotlinFrontendExtension
import org.jetbrains.kotlin.gradle.frontend.config.BundleConfig
import org.jetbrains.kotlin.gradle.frontend.npm.NpmExtension
import org.jetbrains.kotlin.gradle.frontend.util.nodePath
import org.jetbrains.kotlin.gradle.frontend.util.startWithRedirectOnFail
import org.jetbrains.kotlin.gradle.frontend.webpack.WebPackExtension
import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile

plugins {
    id("kotlin2js")
}

buildscript {
    repositories {
        maven("https://dl.bintray.com/kotlin/kotlin-eap")

    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-frontend-plugin:${Versions.kotlinFrontend}")
    }
}

apply {
    plugin("org.jetbrains.kotlin.frontend")
}

dependencies {
    compile(kotlin("stdlib-js"))
    compile(project(":engine-js"))
    compile(project(":dto-js"))

    compile("org.jetbrains.kotlinx:kotlinx-html-js:${Versions.kotlinHtml}")
    compile("org.jetbrains:kotlin-react:${Versions.reactWrapper}")
    compile("org.jetbrains:kotlin-react-dom:${Versions.reactWrapper}")
    compile("org.jetbrains:kotlin-react-router-dom:${Versions.reactRouterDomWrapper}")
    compile("org.jetbrains:kotlin-styled:${Versions.kotlinStyled}")
    compile("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:${Versions.coroutines}")
    compile(kotlin("test-js"))
}

extensions.configure<KotlinFrontendExtension>("kotlinFrontend") {
    downloadNodeJsVersion = Versions.node

    bundle("webpack", delegateClosureOf<WebPackExtension> {
        bundleName = "main"
        contentPath = file("src/main/resources/static")
        publicPath = "/"
        port = 8080
        host = "0.0.0.0"
        proxyUrl = "http://localhost:9090"
    })

    define("PRODUCTION", false)

//    bundle<WebPackExtension>("webpack") {
//        if (this !is WebPackExtension)  throw RuntimeException("Config is not webpack extension")
//        bundleName = "main"
//        contentPath = file("src/main/web")
//    }

}

extensions.configure<NpmExtension>("npm") {
    dependency("kotlin", Versions.kotlin)
    dependency("react", Versions.reactJs)
    dependency("react-dom", Versions.reactJs)
    dependency("react-router", Versions.reactRouter)
    dependency("react-router-dom", Versions.reactRouterDom)
    dependency("styled-components", Versions.styledComponents)
    dependency("inline-style-prefixer", Versions.inlineStylePrefixer)

    dependency("webpack", Versions.webpack)
    dependency("qunit", Versions.qunit)
    devDependency("karma", Versions.karma)
}

(tasks.getByPath("compileKotlin2Js") as Kotlin2JsCompile).apply {
    kotlinOptions {
        metaInfo = true
        outputFile = "${project.buildDir.path}/js/${project.name}.js"
        sourceMap = true
        moduleKind = "commonjs"
        main = "call"
    }
}

(tasks.getByPath("compileTestKotlin2Js") as Kotlin2JsCompile).apply {
    kotlinOptions {
        metaInfo = true
        outputFile = "${project.buildDir.path}/js-tests/${project.name}-tests.js"
        sourceMap = true
        moduleKind = "commonjs"
        main = "call"
    }
}

repositories {
    maven("https://kotlin.bintray.com/kotlin-js-wrappers")
}

tasks.create("npm-outdated") {
    dependsOn("npm")
    description = "Show outdated packages"
    group = "npm"
    doFirst {
        val npm = nodePath(project, "npm").first()
        val npmPath = npm.absolutePath

        try {
            ProcessBuilder(npmPath, "outdated")
                .directory(project.buildDir)
                .redirectErrorStream(true)
                .startWithRedirectOnFail(project, "npm outdated")
        } catch (error: GradleException) {
            if (error.message?.contains("exit code = 1") != true) {
                throw error
            }
        }
    }
}
