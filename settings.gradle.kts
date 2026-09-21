pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
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