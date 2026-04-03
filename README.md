# Cosquer Game

## Prerequisites
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