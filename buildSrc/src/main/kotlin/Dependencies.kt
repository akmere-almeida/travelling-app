object Dependencies {

    object Kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    }

    object Android {
        const val material = "com.google.android.material:material:${Versions.material}"
        const val testCore = "androidx.test:core:${Versions.androidTest}"
        const val testRunner = "androidx.test:runner:${Versions.androidTest}"
    }

    object Coverage {
        const val jacoco = "org.jacoco:org.jacoco.core:${Versions.jacoco}"
    }

    object Test {
        const val jUnit = "junit:junit:${Versions.junit}"
    }
}