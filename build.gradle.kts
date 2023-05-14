@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlinMpp).apply(false)
    alias(libs.plugins.compose).apply(false)
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
