@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlinMpp)
    alias(libs.plugins.compose)
}

group = "io.coodle.core"
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
        browser {
            testTask {
                useKarma {
                    useFirefoxHeadless()
                }
            }
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.doodle.core)
                implementation(libs.doodle.theme)
                implementation(compose.runtime)
                implementation("org.kodein.di:kodein-di:7.0.0")
                implementation(libs.kotlinx.coroutines)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting
        val jvmTest by getting
        val jsMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(libs.doodle.browser)
                implementation(compose.web.core)
            }
        }
        val jsTest by getting{
            dependsOn(commonTest)
        }
    }
}
