// Fix for AGP AndroidLocationsException when both ANDROID_PREFS_ROOT and ANDROID_USER_HOME environment variables are set
try {
    val unsafeField = Class.forName("sun.misc.Unsafe").getDeclaredField("theUnsafe")
    unsafeField.isAccessible = true
    val unsafe = unsafeField.get(null)
    val getObjectMethod = unsafe.javaClass.getMethod("getObject", Any::class.java, Long::class.javaPrimitiveType)
    val staticFieldBaseMethod = unsafe.javaClass.getMethod("staticFieldBase", java.lang.reflect.Field::class.java)
    val staticFieldOffsetMethod = unsafe.javaClass.getMethod("staticFieldOffset", java.lang.reflect.Field::class.java)

    val peClass = Class.forName("java.lang.ProcessEnvironment")
    for (fieldName in listOf("theEnvironment", "theUnmodifiableEnvironment", "theCaseInsensitiveEnvironment")) {
        try {
            val field = peClass.getDeclaredField(fieldName)
            val base = staticFieldBaseMethod.invoke(unsafe, field)
            val offset = staticFieldOffsetMethod.invoke(unsafe, field) as Long
            @Suppress("UNCHECKED_CAST")
            val map = getObjectMethod.invoke(unsafe, base, offset) as? MutableMap<String, String>
            map?.remove("ANDROID_PREFS_ROOT")
        } catch (_: Throwable) {}
    }
} catch (_: Throwable) {
}

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "AIO Calculator"

include(":app")
include(":core:common")
include(":core:design")
include(":core:navigation")
include(":core:math")
include(":core:units")
include(":core:formula")
include(":core:database")
include(":core:datastore")
include(":core:network")
include(":core:currency")
include(":core:formatting")
include(":feature:calculator")
