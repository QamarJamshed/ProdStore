pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "EcommerceApp"
include(":app")
include(":core:common")
include(":core:designsystem")
include(":core:network")
include(":core:database")
include(":domain")
include(":data")
include(":feature:home:home-api")
include(":feature:home:home-impl")
include(":feature:onboarding:onboarding-api")
include(":feature:onboarding:onboarding-impl")
include(":feature:productdetails:productdetails-api")
include(":feature:productdetails:productdetails-impl")
include(":feature:wishlist:wishlist-api")
include(":feature:wishlist:wishlist-impl")
