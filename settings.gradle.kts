pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.minecraftforge.net/")
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
    versionCatalogs {
        // Minecraft Version
        val minecraftVersion = "1.20.1"

        // Mod Dependencies Versions
        // Common
        val jeiVersion = "15.2.0.27"
        val reiVersion = "12.0.684"
        val emiVersion = "1.0.30"
        val ae2Version = "15.0.18"
        val kjsVersion = "2001.6.4-build.120"
        val auVersion = "1.20.1-0.6.0"

        // Forge
        val forgeVersion = "47.1.3"
        val registrateForgeVersion = "MC1.20-1.3.3"
        val createForgeVersion = "0.5.1.d-9"
        val flywheelForgeVersion = "0.6.9-5"
        val topForgeVersion = "1.20.1-10.0.1-3"
        val jadeForgeVersion = "11.6.3"
        val worldStripperForgeFile = "4578579"

        // Libs
        val quiltMappingsVersion = "5"  // https://lambdaurora.dev/tools/import_quilt.html
        val parchmentVersion = "2023.09.03" // https://parchmentmc.org/docs/getting-started
        val shadowVersion = "7.1.2"
        val architecturyPluginVersion = "3.4-SNAPSHOT"
        val architecturyLoomVersion = "1.3-SNAPSHOT"
        val vineFlowerVersion = "1.+"
        val macheteVersion = "1.+"
        val configurationVersion = "2.2.0"
        val ldLibVersion = "1.0.23.a"
        val mixinextrasVersion = "0.2.0"
        val shimmerVersion = "0.2.2"

        /*forge {
            version("forgeShortVersion", forgeVersion)
            val minecraftForge = version("minecratfForge", "${minecraftVersion}-${forgeVersion}")
            library("minecraftForge", "net.minecraftforge", "forge").versionRef(minecraftForge)

            val ldLib = version("ldlib", ldLibVersion)
            library("ldlib-forge", "com.lowdragmc.ldlib", "ldlib-forge-${minecraftVersion}").versionRef(ldLib)

            val shimmer = version("shimmer", "${minecraftVersion}-${shimmerVersion}")
            library("shimmer-forge", "com.lowdragmc.shimmer", "Shimmer-forge").versionRef(shimmer)

            val registrate = version("registrate", registrateForgeVersion)
            library("registrate", "com.tterrag.registrate", "Registrate").versionRef(registrate)

            val createForge = version("createForge", createForgeVersion)
            library("createForge", "com.simibubi.create", "create-${minecraftVersion}").versionRef(createForge)

            val flywheel = version("flywheel", flywheelForgeVersion)
            library("flywheel", "com.jozufozu.flywheel", "flywheel-forge-${minecraftVersion}").versionRef(flywheel)

            val jei = version("jei", jeiVersion)
            library("jei-common-api", "mezz.jei", "jei-${minecraftVersion}-common-api").versionRef(jei)
            library("jei-forge-api", "mezz.jei", "jei-${minecraftVersion}-forge-api").versionRef(jei)
            library("jei-forge-impl", "mezz.jei", "jei-${minecraftVersion}-forge").versionRef(jei)

            val rei = version("rei", reiVersion)
            library("rei-plugin", "me.shedaniel", "RoughlyEnoughItems-default-plugin-forge").versionRef(rei)
            library("rei-api", "me.shedaniel", "RoughlyEnoughItems-api-forge").versionRef(rei)
            library("rei-forge", "me.shedaniel", "RoughlyEnoughItems-forge").versionRef(rei)

            val emi = version("emi", "$emiVersion+$minecraftVersion")
            library("emi", "dev.emi", "emi-forge").versionRef(emi)

            val theoneprobe = version("theoneprobe", topForgeVersion)
            library("theoneprobe", "mcjty.theoneprobe", "theoneprobe").versionRef(theoneprobe)

            val jade = version("jade", jadeForgeVersion)
            library("jade", "maven.modrinth", "jade").versionRef(jade)

            val kubejs = version("kubejs", kjsVersion)
            library("kubejs", "dev.latvian.mods", "kubejs-forge").versionRef(kubejs)

            val mixinextras = version("mixinextras", mixinextrasVersion)
            library("mixinextras-forge", "io.github.llamalad7", "mixinextras-forge").versionRef(mixinextras)

            val configuration = version("configuration", configurationVersion)
            library("configuration", "dev.toma.configuration", "configuration-forge-1.20.1").versionRef(configuration)

            val worldStripper = version("worldStripper", worldStripperForgeFile)
            library("worldStripper", "curse.maven", "worldStripper-250603").versionRef(worldStripper)

            val ae2 = version("ae2", ae2Version)
            library("ae2", "appeng", "appliedenergistics2-forge").versionRef(ae2)

            val au = version("au", auVersion)
            library("almostUnified-forge", "com.almostreliable.mods", "almostunified-forge").versionRef(au)
        }

        libs {
            version("quiltMappings", quiltMappingsVersion)
            version("parchment", parchmentVersion)

            val minecraft = version("minecraft", minecraftVersion)
            library("minecraft", "com.mojang", "minecraft").versionRef(minecraft)

            val shadow = version("shadow", shadowVersion)
            plugin("shadow", "com.github.johnrengelman.shadow").versionRef(shadow)

            val architecturyPlugin = version("architectury", architecturyPluginVersion)
            plugin("architectury", "architectury-plugin").versionRef(architecturyPlugin)

            val architecturyLoom = version("architectury-loom", architecturyLoomVersion)
            plugin("architectury-loom", "dev.architectury.loom").versionRef(architecturyLoom)

            val vineFlower = version("vineFlower", vineFlowerVersion)
            plugin("vineFlower", "io.github.juuxel.loom-vineflower").versionRef(vineFlower)

            val machete = version("machete", macheteVersion)
            plugin("machete", "io.github.p03w.machete").versionRef(machete)
        }*/
    }
}