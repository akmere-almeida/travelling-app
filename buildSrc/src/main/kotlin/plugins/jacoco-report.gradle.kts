package plugins

tasks.withType<Test> {
    configure<JacocoTaskExtension> {
        isIncludeNoLocationClasses = true
        excludes = listOf("jdk.internal.*")
    }
}

val fileFilter = mutableSetOf(
    "**/R.class",
    "**/R\$*.class",
    "**/BuildConfig.*",
    "**/Manifest*.*",
    "**/*Test*.*",
    "android/**/*.*",
    "**/*\$Lambda$*.*", // Jacoco can not handle several "$" in class name.
    "**/*\$inlined$*.*" // Kotlin specific, Jacoco can not handle several "$" in class name.
)

private val classDirectoriesTree = fileTree(project.buildDir) {
    include(
        "**/classes/**/main/**",
        "**/intermediates/classes/debug/**",
        "**/intermediates/javac/debug/*/classes/**",
        "**/tmp/kotlin-classes/debug/**"
    )

    exclude(fileFilter)
}

private val sourceDirectoriesTree = fileTree("${project.buildDir}") {
    include(
        "src/main/java/**",
        "src/main/kotlin/**",
        "src/debug/java/**",
        "src/debug/kotlin/**"
    )
}

private val executionDataTree = fileTree(project.buildDir) {
    include(
        "outputs/code_coverage/**/*.ec",
        "jacoco/jacocoTestReportDebug.exec",
        "jacoco/testDebugUnitTest.exec",
        "jacoco/test.exec"
    )
}

@Suppress("DEPRECATION")
fun JacocoReportsContainer.reports() {
    xml.isEnabled = true
    html.isEnabled = true
    xml.destination = file("${buildDir}/reports/jacoco/jacocoTestReport/jacocoTestReport.xml")
    html.destination = file("${buildDir}/reports/jacoco/jacocoTestReport/html")
}

fun JacocoCoverageVerification.setDirectories() {
    sourceDirectories.setFrom(sourceDirectoriesTree)
    classDirectories.setFrom(classDirectoriesTree)
    executionData.setFrom(executionDataTree)
}

fun JacocoReport.setDirectories() {
    sourceDirectories.setFrom(sourceDirectoriesTree)
    classDirectories.setFrom(classDirectoriesTree)
    executionData.setFrom(executionDataTree)
}


if (tasks.findByName("jacocoAndroidTestReport") == null) {

    tasks.register<JacocoReport>("jacocoAndroidTestReport") {
        description = "Code coverage report for both Android and Unit tests."
        dependsOn("testDebugUnitTest", "createDebugCoverageReport")
        reports {
            reports()
        }
        setDirectories()
    }
}

if (tasks.findByName("jacocoAndroidCoverageVerification") == null) {
    tasks.register<JacocoCoverageVerification>("jacocoAndroidCoverageVerification") {
        description = "Code coverage verification for Android both Android and Unit tests."
        dependsOn("testDebugUnitTest", "createDebugCoverageReport")

        violationRules {
            rule {
                limit {
                    counter = "INSTRUCTION"
                    value = "COVEREDRATIO"
                    minimum = "0.8".toBigDecimal()
                }
            }
        }
        setDirectories()
    }
}

if (tasks.findByName("jacocoUnitTestCoverageVerification") == null) {
    val androidTestTask = tasks.findByName("app:connectedDebugAndroidTest")
    tasks.register<JacocoCoverageVerification>("jacocoUnitTestCoverageVerification") {
        description = "Code coverage verification for Unit tests."
        androidTestTask?.enabled = false
        dependsOn("testDebugUnitTest", "createDebugCoverageReport")
        violationRules {
            rule {
                limit {
                    counter = "INSTRUCTION"
                    value = "COVEREDRATIO"
                    minimum = "0.8".toBigDecimal()
                }
            }
        }
        setDirectories()
    }
}