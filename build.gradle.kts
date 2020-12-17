import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
}

allprojects {
    apply(plugin = "kotlin")
}
// PLUGINS -- END

group = "com.home.rabbitmq-broker"
version = "0.0.1-SNAPSHOT"

// JAVA VERSION -- BEGIN
allprojects {
    java.sourceCompatibility = JavaVersion.VERSION_11

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
}
// JAVA VERSION -- END

// NULLABILITY -- BEGIN
allprojects {
    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
        }
    }
}
// NULLABILITY -- END


// JUNIT -- BEGIN
allprojects {
    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
// JUNIT -- END


// Dependencies -- BEGIN
allprojects {
    repositories {
        jcenter()
        mavenCentral()
        maven {
            setUrl("https://dl.bintray.com/serpro69/maven/")
        }
        maven {
            setUrl("https://dl.bintray.com/arrow-kt/arrow-kt/")
        }
    }

    dependencies {
        implementation(kotlin("reflect:1.4.10"))
    }
}