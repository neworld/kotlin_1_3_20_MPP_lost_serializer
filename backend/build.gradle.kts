plugins {
    kotlin("jvm")
    application
}

apply {
    plugin("kotlinx-serialization")
}

dependencies {
    compile(project(":engine-jvm"))
    compile(project(":dto-jvm"))

    compile(kotlin("stdlib-jdk8"))
    compile(kotlin("reflect"))

}
