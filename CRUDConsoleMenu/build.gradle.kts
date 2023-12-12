plugins {
    id("java")
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
    implementation(project(":DatabaseManager"))
    implementation("org.postgresql:postgresql:42.2.5")
    implementation("javax.servlet:javax.servlet-api:4.0.1")
    compileOnly("javax.servlet:jstl:1.2")
    implementation("org.apache.tomcat.embed:tomcat-embed-core:9.0.50")
    implementation("org.apache.tomcat.embed:tomcat-embed-jasper:9.0.50")
}

tasks.test {
    useJUnitPlatform()
}