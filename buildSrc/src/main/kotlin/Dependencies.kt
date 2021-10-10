object Dependencies {

    object Kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    }

    object Android {
        const val material = "com.google.android.material:material:${Versions.material}"
        const val testCore = "androidx.test:core:${Versions.androidTest}"
        const val testRunner = "androidx.test:runner:${Versions.androidTest}"
        const val composeRuntime = "androidx.compose.runtime:runtime:${Versions.compose}"
        const val activityCompose = "androidx.activity:activity-compose:${Versions.activity}"
        const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
        const val composeFoundation = "androidx.compose.foundation:foundation:${Versions.compose}"
        const val composeMaterial = "androidx.compose.material:material:${Versions.compose}"
        const val composeTooling = "androidx.compose.ui:ui-tooling${Versions.compose}"
    }

    object Coverage {
        const val jacoco = "org.jacoco:org.jacoco.core:${Versions.jacoco}"
    }

    object Test {
        const val jUnit = "junit:junit:${Versions.junit}"
    }
}