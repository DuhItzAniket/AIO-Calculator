plugins {
    id("com.android.application") version "8.7.2" apply false
    id("org.jetbrains.kotlin.android") version "2.0.21" apply false
    id("com.google.devtools.ksp") version "2.0.21-1.0.14" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.21" apply false
}

allprojects {
    group = "com.aio.calculator"
    version = "1.0.0"
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}