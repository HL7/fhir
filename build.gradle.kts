
plugins {
//    // Apply the java-library plugin for API and implementation separation.
//    `java-library`
//    id("org.sonatype.gradle.plugins.scan") version "2.0.2"
    java
    application
}
//
//ant.importBuild("build.xml") { oldTargetName ->
//    if (oldTargetName == "Publish") {
//        "ant_build"
//    } else if (oldTargetName == "clean") {
//        "ant_clean"
//    } else if (oldTargetName == "hello") {
//        "ant_hello"
//    } else {
//        oldTargetName
//    }
//}

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
    implementation("org.hl7.fhir", "kindling", "0.0.10-SNAPSHOT")
}

//configurations.all {
//    resolutionStrategy.cacheDynamicVersionsFor(0, "seconds")
//}

task("runExecutableJar", JavaExec::class) {
    main = "org.hl7.fhir.tools.publisher.Publisher"
    classpath = sourceSets["main"].compileClasspath
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
