@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlinMpp)
    alias(libs.plugins.compose)
}

group = "io.coodle.foundation"
version = "0.0.1-SNAPSHOT"

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        withJava()
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    js(IR) {
        browser()
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":core"))
                implementation(libs.doodle.control)
                implementation(compose.runtime)
                implementation(libs.kotlinx.coroutines)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
        val jvmMain by getting{
            dependsOn(commonMain)
        }
        val jvmTest by getting
        val jsMain by getting {
            dependsOn(commonMain)
        }
        val jsTest by getting{
            dependsOn(commonTest)
        }
    }
}
