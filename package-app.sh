#!/bin/bash

PLATFORM="Mac"  # Change to "Windows" for Windows builds
APP_NAME="Bookverse"
FOLDER="build/libs"
JAR_FILE="PackApps.jar"
MAIN_CLASS="com.bookverse.packapps.core.Start"
VERSION="4.0"

# Create a .jar file.
./gradlew clean build

if [ "$PLATFORM" = "Mac" ]; then
    ICON_FILE="src/main/resources/icons/cat.icns"

    # Create a .app bundle for MacOS.
    jpackage \
      --type app-image \
      --name "${APP_NAME}" \
      --input "${FOLDER}" \
      --main-jar "${JAR_FILE}" \
      --main-class "${MAIN_CLASS}" \
      --java-options -Xmx2048m \
      --icon "${ICON_FILE}" \
      --app-version "${VERSION}" \
      --vendor "${APP_NAME}" \
      --copyright "Copyright © 2025" \
      --mac-package-name "${APP_NAME}" \
      --resource-dir src/main/resources \
      --resource-dir libs \
      --verbose

      # Create a .dmg for MacOS.
      jpackage \
        --type dmg \
        --name "${APP_NAME}" \
        --input "${FOLDER}" \
        --main-jar "${JAR_FILE}" \
        --main-class "${MAIN_CLASS}" \
        --java-options -Xmx2048m \
        --icon "${ICON_FILE}" \
        --app-version "${VERSION}" \
        --vendor "${APP_NAME}" \
        --copyright "Copyright © 2025" \
        --mac-package-name "${APP_NAME}" \
        --resource-dir src/main/resources \
        --resource-dir libs \
        --verbose
else
    ICON_FILE="src/main/resources/icons/cat.ico"

      # Create a .exe for Windows.
      jpackage \
        --type exe \
        --name "${APP_NAME}" \
        --input "${FOLDER}" \
        --main-jar "${JAR_FILE}" \
        --main-class "${MAIN_CLASS}" \
        --java-options -Xmx2048m \
        --icon "${ICON_FILE}" \
        --app-version "${VERSION}" \
        --vendor "${APP_NAME}" \
        --copyright "Copyright © 2025" \
        --resource-dir src/main/resources \
        --resource-dir libs \
        --verbose
fi