plugins {
    java
    application
}

repositories {

    google()
    mavenLocal()
    mavenCentral()
    maven {
        name = "Central Portal Snapshots"
        url = uri("https://central.sonatype.com/repository/maven-snapshots/")

        // Only search this repository for the specific dependency
        content {
            includeModule("org.hl7.fhir", "kindling")
            includeGroup("ca.uhn.hapi.fhir")
        }
    }
    maven {
        url = uri("https://jitpack.io")
    }
    maven {
        url = uri("https://plugins.gradle.org/m2/")
    }
}

dependencies {
    implementation("org.hl7.fhir:kindling:${property("kindlingVersion")}")
}

// -XX:-UseBASE64Intrinsics mitigates CVE-2026-114938 (Java4Shell). Remove it once the JDK is patched.
val cveMitigationJvmArgs = listOf("-XX:-UseBASE64Intrinsics")

fun publisherJvmArgs(): List<String> = cveMitigationJvmArgs + listOfNotNull(
    properties["logback.configurationFile"]?.let { "-Dlogback.configurationFile=$it" }
)

task("publish", JavaExec::class) {
    dependsOn(":printVersion")
    jvmArgs = publisherJvmArgs()
    main = "org.hl7.fhir.tools.publisher.Publisher"
    classpath = sourceSets["main"].compileClasspath
}

task("publishFull", JavaExec::class) {
    dependsOn(":printVersion")
    jvmArgs = publisherJvmArgs()
    main = "org.hl7.fhir.tools.publisher.Publisher"
    classpath = sourceSets["main"].compileClasspath
    args("-nopartial")
}

task("printVersion") {
    println("\nKicking off FHIR publishing job!" +
            "\n\n==============================" +
            "\nGenerating code using kindling version ${properties["kindlingVersion"]}" +
            "\nFor more information on kindling, and to check latest version, check here:" +
            "\nhttps://github.com/HL7/kindling" +
            "\n"+
            "\nVerbose or customized output can be further configured using the logback.configurationFile gradle property:"+
            "\n"+
            "\n  ./gradlew publish -Plogback.configurationFile=~/my-logback-config.xml"+
            "\n"+
            "\n==============================")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
