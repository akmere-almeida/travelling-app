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
        const val composeTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
        const val composeLiveData = "androidx.compose.runtime:runtime-livedata:${Versions.compose}"
        const val easyPermissions = "com.vmadalin:easypermissions-ktx:${Versions.easyPermissions}"
        const val servicesLocation = "com.google.android.gms:play-services-location:${Versions.servicesLocation}"
    }

    object Networking {
        const val apolloCoroutines = "com.apollographql.apollo:apollo-coroutines-support:${Versions.apolloClient}"
        const val apolloRuntime = "com.apollographql.apollo:apollo-runtime:${Versions.apolloClient}"
        const val okHttpClient = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
        const val okHttpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
        const val coil = "io.coil-kt:coil-compose:${Versions.coil}"
    }

    object Coverage {
        const val jacoco = "org.jacoco:org.jacoco.core:${Versions.jacoco}"
    }

    object Test {
        const val jUnit = "junit:junit:${Versions.junit}"
        const val mockkUnitTest = "io.mockk:mockk:${Versions.mockk}"
        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.okHttp}"
        const val archCore = "androidx.arch.core:core-testing:${Versions.archTesting}"
    }
}