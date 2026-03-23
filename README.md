# BusyProject - Mobile App Automation Framework

## Overview
This project is a mobile automation framework built with Appium + TestNG + Java using Page Object Model (POM) and configuration-driven execution.

## Prerequisites
- Java 17
- Maven 3.6+
- Appium server
- Android SDK
- Device/emulator or iOS simulator

## Setup
1. `mvn clean install`
2. Place `General-Store.apk` under `apps/`
3. Configure `src/test/resources/config.properties` values
4. Start Appium server: `appium --address 127.0.0.1 --port 4723`

## Run
- `mvn test` (default)
- `mvn test -P Regression`
- `mvn test -Dplatform=android`

## Test structure
- `src/main/java/com/catalyst/mobile/config` - config reader
- `src/main/java/com/catalyst/mobile/driver` - driver factory
- `src/main/java/com/catalyst/mobile/page` - page objects
- `src/test/java/com/catalyst/mobile/basetest` - base test class
- `src/test/java/com/catalyst/mobile/logintest` - test classes

## Notes
- Use `-Dplatform=ios` or `-Dplatform=android` at runtime.
- `log4j2.xml` controls logs.
- `testNG.xml` is parallelized for speed.