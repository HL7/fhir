
plugins {
    java
    application
}

repositories {
    jcenter()
    google()
    mavenLocal()
    mavenCentral()
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
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
