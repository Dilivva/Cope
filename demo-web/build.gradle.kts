
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlinMpp)
    alias(libs.plugins.compose)
}

group = "io.cherrio.coodle"
version = "unspecified"

repositories {
    mavenCentral()
}
kotlin{
    js(IR) {
        browser {
            runTask {
                devServer = devServer?.copy(port = 3500)
            }
            commonWebpackConfig {
                sourceMaps = false
            }
        }
        binaries.executable()
    }

    sourceSets{
        val jsMain by getting{
            dependencies {
                implementation(project(":foundation"))
                implementation(compose.runtime)
            }
        }
        val jsTest by getting
    }
}