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
    }

    object Coverage {
        const val jacoco = "org.jacoco:org.jacoco.core:${Versions.jacoco}"
    }

    object Test {
        const val jUnit = "junit:junit:${Versions.junit}"
    }
}