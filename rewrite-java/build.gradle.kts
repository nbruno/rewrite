// run manually with -x compileKotlin when you need to regenerate
tasks.register<JavaExec>("generateAntlrSources") {
    main = "org.antlr.v4.Tool"

    args = listOf(
            "-o", "src/main/java/org/openrewrite/java/internal/grammar",
            "-package", "org.openrewrite.java.internal.grammar",
            "-visitor"
    ) + fileTree("src/main/antlr").matching { include("**/*.g4") }.map { it.path }

    classpath = sourceSets["main"].runtimeClasspath
}

dependencies {
    api(project(":rewrite-core"))

    implementation("org.antlr:antlr4:4.8-1")

    implementation("org.slf4j:slf4j-api:1.7.+")
    implementation("commons-lang:commons-lang:latest.release")

    implementation("com.koloboke:koloboke-api-jdk8:latest.release")
    implementation("com.koloboke:koloboke-impl-jdk8:latest.release")

    api("com.fasterxml.jackson.core:jackson-annotations:latest.release")
    implementation("com.fasterxml.jackson.core:jackson-databind:latest.release")

    implementation("com.esotericsoftware:kryo:4.0.1")

    implementation("org.ow2.asm:asm:latest.release")
    implementation("org.ow2.asm:asm-util:latest.release")

    testImplementation("org.yaml:snakeyaml:latest.release")
}

tasks.withType<Javadoc> {
    // generated ANTLR sources violate doclint
    (options as StandardJavadocDocletOptions).addStringOption("Xdoclint:none", "-quiet")

    exclude("**/JavaParser**")
}
