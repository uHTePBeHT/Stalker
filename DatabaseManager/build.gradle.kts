plugins {
    id("java")
    id("application")
}

application {
    mainClass = "org.example.DatabaseManager"
}


group = "org.example"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation(project(":Model"))
    implementation(project(":Generator"))
    implementation(project(":Deserializer"))
    implementation("org.postgresql:postgresql:42.2.5")
}

tasks.test {
    useJUnitPlatform()
}