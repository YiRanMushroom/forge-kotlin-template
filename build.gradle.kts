import org.jetbrains.kotlin.ir.backend.js.compileIr
import org.spongepowered.asm.gradle.plugins.MixinExtension
import org.spongepowered.asm.gradle.plugins.struct.DynamicProperties
import java.text.SimpleDateFormat
import java.util.*

buildscript {
    repositories {
        mavenCentral()
        maven("https://maven.fabricmc.net/")
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0-Beta")
        classpath("org.spongepowered:mixingradle:0.7.+")
    }
}

apply(plugin = "kotlin")
apply(plugin = "org.spongepowered.mixin")

plugins {
    eclipse
    idea
    `maven-publish`
    id("net.minecraftforge.gradle") version "[6.0,6.2)"
    id("org.jetbrains.kotlin.jvm") version "1.8.22"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.22"
}

group = "com.pleahmacaka"
version = "1.20-0.1.0"

val modid = "examplemod"
val vendor = "pleahmacaka"

val minecraftVersion = "1.20.2"
val forgeVersion = "48.0.20"

java.toolchain.languageVersion.set(JavaLanguageVersion.of(17))

println(
    "Java: ${System.getProperty("java.version")} JVM: ${System.getProperty("java.vm.version")}(${
        System.getProperty(
            "java.vendor"
        )
    }) Arch: ${System.getProperty("os.arch")}"
)

minecraft {
    mappings("official", minecraftVersion)
    accessTransformer(file("src/main/resources/META-INF/accesstransformer.cfg"))

    runs.all {
        mods {
            workingDirectory(project.file("run"))
            property("forge.logging.markers", "REGISTRIES")
            property("forge.logging.console.level", "debug")
            property("forge.enabledGameTestNamespaces", modid)
            property("terminal.jline", "true")
            mods {
                create(modid) {
                    source(sourceSets.main.get())
                }
            }
        }
    }

    runs.run {
        create("client") {
            property("log4j.configurationFile", "log4j2.xml")
            jvmArg("-XX:+IgnoreUnrecognizedVMOptions")
            jvmArg("-XX:+AllowEnhancedClassRedefinition")
            jvmArg("-Dnet.minecraftforge.gradle.check.certs=false")
            args("--username", "Player")
        }

        create("server") {}
        create("gameTestServer") {}
        create("data") {
            workingDirectory(project.file("run"))
            args(
                "--mod",
                modid,
                "--all",
                "--output",
                file("src/generated/resources/"),
                "--existing",
                file("src/main/resources")
            )
        }
    }
}

sourceSets.main.configure { resources.srcDirs("src/generated/resources/") }

repositories {
    mavenLocal()
    mavenCentral()
    maven {
        name = "Kotlin for Forge"
        url = uri("https://thedarkcolour.github.io/KotlinForForge/")
    }

    // GTM Repos
    maven { url = uri("https://api.modrinth.com/maven") } // LazyDFU, Jade
    maven { url = uri("https://maven.terraformersmc.com/releases/") } // Mod Menu, EMI
    maven { url = uri("https://maven.shedaniel.me/") } // Cloth Config, REI
    maven {
        url = uri("https://cursemaven.com")
        content {
            includeGroup("curse.maven")
        }
    } // Curse Forge File
    maven {
        name = "Jared's maven"
        url = uri("https://maven.blamejared.com/")
    } // JEI
    maven {
        // location of a maven mirror for JEI files, as a fallback
        name = "ModMaven"
        url = uri("https://modmaven.dev")
    } // JEI mirror, AE2
    maven { url = uri("https://maven.parchmentmc.org") } // Parchment mappings
    maven { url = uri("https://maven.quiltmc.org/repository/release") } // Quilt Mappings
    maven { url = uri("https://maven.firstdarkdev.xyz/snapshots") } // LDLib
    maven { // Flywheel
        url = uri("https://maven.tterrag.com/")
        content {
            // need to be specific here due to version overlaps
            includeGroup("com.jozufozu.flywheel")
            includeGroup("com.tterrag.registrate")
            includeGroup("com.simibubi.create")
        }
    }
    maven { url = uri("https://maven.theillusivec4.top/") } // Curios
    maven { // TOP
        url = uri("https://maven.k-4u.nl")
    }
    maven {
        // saps.dev Maven (KubeJS and Rhino)
        url = uri("https://maven.saps.dev/minecraft")
        content {
            includeGroup("dev.latvian.mods")
        }
    }
    maven { url = uri("https://maven.jamieswhiteshirt.com/libs-release") } // Reach Entity Attributes
    maven { url = uri("https://jitpack.io") } // Mixin Extras, Fabric ASM
}

fun getProperty(name: String): String {
    return project.findProperty(name)?.toString() ?: System.getProperty(name)
}

dependencies {
    minecraft("net.minecraftforge:forge:$minecraftVersion-$forgeVersion")
    annotationProcessor("org.spongepowered:mixin:0.8.5:processor")
    implementation("thedarkcolour:kotlinforforge:4.3.0")

    implementation(fg.deobf("curse.maven:configuration-444699:5030089"))

    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")

    implementation("com.google.code.findbugs:jsr305:3.0.2")

    // tests
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.2")
    testImplementation("io.javalin:javalin:5.4.2")
    testImplementation("org.mockito:mockito-core:5.2.0")

    // From Forge

    // LDLib
    api("curse.maven:ldlib-626676:5421796") { }
//    include(forge.ldlib.forge)

    /*// Registrate
    api(compileOnly("com.tterrag.registrate:Registrate:${forge.versions.registrate.get()}"))

    // Create and its dependencies
    compileOnly("com.simibubi.create:create-${libs.versions.minecraft.get()}:${forge.versions.createForge.get()}:all") {

    }
    compileOnly(forge.flywheel)

    // JEI
    compileOnly(forge.jei.common.api) { }
    compileOnly(forge.jei.forge.api) { }
    compileOnly(forge.jei.forge.impl) { }

    // REI
    compileOnly(forge.rei.plugin)
    compileOnly(forge.rei.api)
    implementation(forge.rei.forge)

    // EMI
    implementation("dev.emi:emi-forge:${forge.versions.emi.get()}:api")
    compileOnly(forge.emi)

    // TOP
    compileOnly(forge.theoneprobe) { transitive = false }

    // Jade
    implementation(forge.jade)

    // AE2


    //AlmostUnified
    implementation(forge.almostUnified.forge)

    // KJS
    implementation(forge.kubejs)

    // Mixin Extras
    implementation(annotationProcessor("io.github.llamalad7:mixinextras-common:${this.forge.versions.mixinextras.get()}")!!)

    // Configuration
    api(forge.configuration)
//    include(forge.configuration)

    // Shimmer
    api(forge.shimmer.forge) {  }*/

    implementation("curse.maven:ae2-223794:5401948") { }

    implementation("maven.modrinth:embeddium:0.2.10+mc1.20.1")
    implementation("maven.modrinth:oculus:1.20.1-1.6.9")
}

val Project.mixin: MixinExtension
    get() = extensions.getByType()

mixin.run {
    add(sourceSets.main.get(), "examplemod.mixins.refmap.json")
    config("examplemod.mixins.json")
    val debug = this.debug as DynamicProperties
    debug.setProperty("verbose", true)
    debug.setProperty("export", true)
    setDebug(debug)
}

tasks.withType<Jar> {
    archiveBaseName.set(modid)
    manifest {
        attributes(
            mapOf(
                "Specification-Title" to modid,
                "Specification-Vendor" to vendor,
                "Specification-Version" to "1",
                "Implementation-Title" to project.name,
                "Implementation-Version" to project.version.toString(),
                "Implementation-Vendor" to vendor,
                "Implementation-Timestamp" to SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(Date())
            )
        )
    }
    finalizedBy("reobfJar")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
    repositories {
        maven {
            url = uri("file://${project.projectDir}/mcmodsrepo")
        }
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17"
    }
}