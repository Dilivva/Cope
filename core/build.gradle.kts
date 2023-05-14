@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlinMpp)
    alias(libs.plugins.compose)
}

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
        binaries.executable()
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
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting{
            dependsOn(commonMain)
        }

        val jvmTest by getting
        val jsMain by getting {
            dependencies {
                implementation(libs.doodle.browser)
                implementation(compose.web.core)
            }
        }
        val jsTest by getting
    }
}
