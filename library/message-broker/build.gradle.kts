plugins {
    kotlin("jvm")
}

dependencies {
    implementation("io.github.microutils:kotlin-logging:1.7.8")
    implementation ("com.rabbitmq:amqp-client:5.9.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.2")
}
