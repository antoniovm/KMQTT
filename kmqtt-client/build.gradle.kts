plugins {
    kotlin("multiplatform")
    id("convention.publication")
    id("kotlinx-atomicfu")
}

val atomicfuVersion: String by project
val nodeWrapperVersion: String by project

kotlin {
    explicitApi()
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
    }
    js {
        nodejs {
            binaries.executable()
        }
    }
    mingwX64 {}
    linuxX64 {}
    linuxArm64 {}
    iosX64 {}
    iosArm64 {}
    iosSimulatorArm64 {}
    macosX64 {}
    macosArm64 {}
    tvosX64 {}
    tvosSimulatorArm64 {}
    tvosArm64 {}
    watchosArm32 {}
    watchosArm64 {}
    watchosSimulatorArm64 {}
    watchosX64 {}

    sourceSets {
        all {
            languageSettings.apply {
                optIn("kotlin.ExperimentalUnsignedTypes")
                optIn("kotlinx.cinterop.ExperimentalForeignApi")
            }
        }
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation(project(":kmqtt-common"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmMain by getting {}
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
            }
        }
        val jsMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin-wrappers:kotlin-node:$nodeWrapperVersion")
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
        val posixMain by creating {
            dependsOn(commonMain)
            dependencies {
                implementation("org.jetbrains.kotlinx:atomicfu:$atomicfuVersion")
            }
        }
        val mingwX64Main by getting {
            dependsOn(posixMain)
        }
        val linuxX64Main by getting {
            dependsOn(posixMain)
        }
        val linuxArm64Main by getting {
            dependsOn(posixMain)
        }
        val iosX64Main by getting {
            dependsOn(posixMain)
        }
        val iosArm64Main by getting {
            dependsOn(posixMain)
        }
        val iosSimulatorArm64Main by getting {
            dependsOn(posixMain)
        }
        val macosX64Main by getting {
            dependsOn(posixMain)
        }
        val macosArm64Main by getting {
            dependsOn(posixMain)
        }
        val tvosX64Main by getting {
            dependsOn(posixMain)
        }
        val tvosArm64Main by getting {
            dependsOn(posixMain)
        }
        val tvosSimulatorArm64Main by getting {
            dependsOn(posixMain)
        }
        val watchosX64Main by getting {
            dependsOn(posixMain)
        }
        val watchosArm32Main by getting {
            dependsOn(posixMain)
        }
        val watchosArm64Main by getting {
            dependsOn(posixMain)
        }
        val watchosSimulatorArm64Main by getting {
            dependsOn(posixMain)
        }
    }
}

publishing {
    repositories {
        maven {
            name = "github"
            url = uri("https://maven.pkg.github.com/davidepianca98/KMQTT")
            credentials(PasswordCredentials::class)
        }
    }
}

