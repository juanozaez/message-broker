plugins {
    kotlin("jvm")
}

dependencies {
    implementation("io.github.microutils:kotlin-logging:1.7.8")
    implementation("com.rabbitmq:amqp-client:5.9.0")
    implementation(project(":library:message-broker"))
    implementation("com.fasterxml.jackson.core:jackson-databind:2.12.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.12.0")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.2")
    testImplementation("org.awaitility:awaitility:4.0.3")
}
