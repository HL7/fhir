
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
}

task("publish", JavaExec::class) {
    dependsOn(":printVersion")
    jvmArgs = listOf("-Dlogback.configurationFile=${properties["logback.configurationFile"]}")
    //jvmArgs = listOf("-Dlogback.configurationFile=publish-logback.xml")
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
