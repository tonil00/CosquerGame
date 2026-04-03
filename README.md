# Cosquer Game

## Story Background
Cosquer Cave is a real place! The cave is located in the Calanque de Morgiou in **Marseille, France**. The entrance to the cave is located 37 m (121 ft) underwater, due to the Holocene sea level rise. The cave contains various prehistoric rock art engravings. Its submarine entrance was discovered in 1985 by Henri Cosquer, a professional diver.
In this game you will be a fish exploring the cave. Your goal is to collect all the paintings. 💙🐟

## I Want to Play!
Just download the build and start playing — no setup required! 🥳

## I want to change things, code -> Prerequisites
1. **Java Development Kit (JDK) 17+** - Download from [Oracle](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html) or [OpenJDK](https://openjdk.java.net/).
2. **Gradle** - Install it via [Gradle's website](https://gradle.org/install/) or use a Gradle wrapper included in the project.

## Run using
```bash
gradle run
```
## Or use the prebuild .jar file
```bash
java -jar ./app/build/libs/CosquerGame-1.0.jar
```

In both cases ensure you are in the project folder.

## Build Native Installers (jpackage)

This project now includes Gradle tasks that use `jpackage` to create native installers.

Important:
- Build each installer on its target OS (macOS on Mac, Windows on Windows, Linux on Linux).
- Use JDK 17+ (must include `jpackage`).

### 1) Create the native installer for the current OS
```bash
./gradlew :app:packageNative
```

Output is generated in:
```bash
app/build/jpackage/dist
```

Default installer type by OS:
- macOS: `dmg`
- Windows: `exe`
- Linux: `deb`

You can override installer type with:
```bash
./gradlew :app:packageNative -PinstallerType=app-image
```

### 2) Platform-specific tasks
```bash
# macOS only
./gradlew :app:packageMac

# Windows only
./gradlew :app:packageWindows

# Linux only
./gradlew :app:packageLinux
```

### 3) Build an app image (no installer)
```bash
./gradlew :app:packageAppImage
```
