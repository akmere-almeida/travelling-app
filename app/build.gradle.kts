plugins {
    id("com.android.application")
    kotlin("android")
    id("jacoco")
    id("plugins.jacoco-report")
    id("org.jlleitschuh.gradle.ktlint")
    id("io.gitlab.arturbosch.detekt").version(Versions.detekt)
    id("com.apollographql.apollo").version(Versions.apolloClient)
}

android {
    compileSdk = Config.compileSdkVersion
    buildToolsVersion = Config.buildToolsVersion

    defaultConfig {
        applicationId = Config.applicationId
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSdkVersion
        versionCode = Config.versionCode
        versionName = Config.versionName
        testInstrumentationRunner = Config.androidTestInstrumentation
    }

    buildTypes {
        getByName("release") {
            isTestCoverageEnabled = true
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        getByName("debug") {
            isTestCoverageEnabled = true
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    testCoverage {
        jacocoVersion = Versions.jacoco
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true
    }

    useLibrary("android.test.runner")

    lint {
        disable(
            "UnsafeExperimentalUsageError",
            "UnsafeExperimentalUsageWarning"
        )
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Dependencies.Kotlin.stdlib)
    implementation(Dependencies.Android.material)
    implementation(Dependencies.Coverage.jacoco)
    implementation(Dependencies.Android.composeRuntime)
    implementation(Dependencies.Android.composeUi)
    implementation(Dependencies.Android.composeFoundation)
    implementation(Dependencies.Android.composeMaterial)
    implementation(Dependencies.Android.composeTooling)
    implementation(Dependencies.Android.activityCompose)
    implementation(Dependencies.Android.composeLiveData)
    implementation(Dependencies.Networking.apolloRuntime)

    testImplementation(Dependencies.Test.jUnit)

    androidTestImplementation(Dependencies.Android.testRunner)
    androidTestImplementation(Dependencies.Android.testCore)
}

ktlint {
    android.set(true)
    outputColorName.set("RED")
}

detekt {
    buildUponDefaultConfig = true
    allRules = false
    config = files("$rootDir/buildSrc/config/detekt.yml")
    baseline = file("$rootDir/buildSrc/config/baseline.xml")

    reports {
        html.enabled = true
        xml.enabled = true
        txt.enabled = true
        sarif.enabled = true
    }
}

apollo {
    generateKotlinModels.set(true)
}
