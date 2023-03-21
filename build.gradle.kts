@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    //kotlin("multiplatform").version("1.7.21").apply(false)
    alias(libs.plugins.kotlinMpp).apply(false)
    //id("org.jetbrains.compose").version("1.3.0").apply(false)
    alias(libs.plugins.compose).apply(false)
    alias(libs.plugins.kotlinJs).apply(false)
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
