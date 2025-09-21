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
            includeModule("ca.uhn.hapi.fhir","org.hl7.fhir.core")
            includeModule("ca.uhn.hapi.fhir","org.hl7.fhir.utilities")
            includeModule("ca.uhn.hapi.fhir", "org.hl7.fhir.dstu2")
            includeModule("ca.uhn.hapi.fhir", "org.hl7.fhir.dstu2016may")
            includeModule("ca.uhn.hapi.fhir", "org.hl7.fhir.dstu3")
            includeModule("ca.uhn.hapi.fhir", "org.hl7.fhir.dstu3.support")
            includeModule("ca.uhn.hapi.fhir", "org.hl7.fhir.r4")
            includeModule("ca.uhn.hapi.fhir", "org.hl7.fhir.r4b")
            includeModule("ca.uhn.hapi.fhir", "org.hl7.fhir.r5")
            includeModule("ca.uhn.hapi.fhir","org.hl7.fhir.convertors")
            includeModule("ca.uhn.hapi.fhir", "org.hl7.fhir.validation")
            includeModule("ca.uhn.hapi.fhir","org.hl7.fhir.model")
            includeModule("ca.uhn.hapi.fhir","org.hl7.fhir.support")
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

task("publish", JavaExec::class) {
    dependsOn(":printVersion")
    if (properties["logback.configurationFile"] != null) {
        jvmArgs = listOf("-Dlogback.configurationFile=${properties["logback.configurationFile"]}")
    }
    main = "org.hl7.fhir.tools.publisher.Publisher"
    classpath = sourceSets["main"].compileClasspath
}

task("publishFull", JavaExec::class) {
    dependsOn(":printVersion")
    if (properties["logback.configurationFile"] != null) {
        jvmArgs = listOf("-Dlogback.configurationFile=${properties["logback.configurationFile"]}")
    }
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
