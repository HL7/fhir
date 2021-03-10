
plugins {
    java
    application
}

repositories {
    jcenter()
    google()
    mavenCentral()
    maven {
        url = uri("https://dl.bintray.com/labra/maven")
    }
    maven {
        url = uri("https://jitpack.io")
    }
    maven {
        url = uri("https://plugins.gradle.org/m2/")
    }
    maven {
        url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
    }
    maven {
        url = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
    }
}

dependencies {
    implementation("org.hl7.fhir:kindling:${property("kindlingVersion")}")
    implementation("ca.uhn.hapi.fhir:org.hl7.fhir.utilities:${property("coreVersion")}")
    implementation("ca.uhn.hapi.fhir:org.hl7.fhir.dstu2:${property("coreVersion")}")
    implementation("ca.uhn.hapi.fhir:org.hl7.fhir.dstu2016may:${property("coreVersion")}")
    implementation("ca.uhn.hapi.fhir:org.hl7.fhir.dstu3:${property("coreVersion")}")
    implementation("ca.uhn.hapi.fhir:org.hl7.fhir.r4:${property("coreVersion")}")
    implementation("ca.uhn.hapi.fhir:org.hl7.fhir.r5:${property("coreVersion")}")
    implementation("ca.uhn.hapi.fhir:org.hl7.fhir.convertors:${property("coreVersion")}")
    implementation("ca.uhn.hapi.fhir:org.hl7.fhir.validation:${property("coreVersion")}")
}

task("publish", JavaExec::class) {
    dependsOn(":printVersion")
    main = "org.hl7.fhir.tools.publisher.Publisher"
    classpath = sourceSets["main"].compileClasspath
}

task("printVersion") {
    println("\nKicking off FHIR publishing job!" +
            "\n\n==============================" +
            "\nGenerating code using kindling version ${properties["kindlingVersion"]}" +
            "\nFor more information on kindling, and to check latest version, check here:" +
            "\nhttps://github.com/HL7/kindling" +
            "\n==============================")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
