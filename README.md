# PackApps by Bookverse 🤖 

## Introduction 🚀
This repository contains a collection of applications and utilities developed in Java, organized into various modules. It is designed to demonstrate concepts such as modular programming, data structures, algorithms, graphical user interface development, and automated testing. Additionally, it includes integrations with databases and other tools.

## Project Content ✅
### Apps and Utilities
Independent modules implementing various functionalities or interactive games:
- **Arrays:** Management and visualization of array operations.
- **Dices:** Dice game.
- **Feedback:** Feedback and email sending.
- **GuessNumber:** Number guessing game.
- **Hangman:** Hangman game.
- **Notes:** Notes management system.
- **Numbers:** Calculator, operations with prime numbers, and constants like Phi.
- **OCR:** Optical character recognition.
- **Puzzle:** Puzzle-solving.
- **QR:** QR code generation.
- **Queues and Stacks:** Implementation of basic data structures.
- **Store:** Inventory and user management system.
- **TicTacToe:** Tic-Tac-Toe game.
- **Whatsapp:** Basic messaging service simulation.
- **Customizable UI**: Switch between **different themes** and **wallpapers** dynamically for a personalized experience.
- **Data Management**: Export data from the database to various formats: **Excel**, **TXT**, and **PDF** files.

### Core
Entry point of the system with the main class to launch the application.

### Automation
Automated tests using Serenity to validate application functionalities. Includes:
- **Tasks:** Automated action flows.
- **Runners:** Main test classes.
- **Questions:** Validations for automated tests.
- **UserInterfaces:** Interface elements used in tests.
- **Utils:** General utilities and configurations for tests.

### Database
Module for database connection with queries and repositories, including specific examples such as note management.

### Resources
- **Configuration files:** Information such as `config.properties`.
- **Images:** Graphic resources for applications.
- **Tessdata:** Training data files for OCR recognition.
- **Data:** Sample data such as databases and JSON files.

## Project Structure 📂
```plaintext
PackApps/
├── README.md
├── gradlew, gradle-wrapper.properties
├── serenity.properties, sonar-project.properties
├── src/
│   ├── main/java/     # Source code for the apps.
│   ├── resources/     # Additional resources (images, databases, etc.).
├── libs/              # Libraries required for execution.
├── test/resources/    # Configuration for automated tests.
```

## Technologies and Tools 🛠️
This project utilizes a wide range of technologies and tools for its development, testing, and deployment:

- **Java:** The main programming language for the entire project, ensuring cross-platform compatibility.
- **Swing:** Used for building graphical user interfaces (GUIs) for the applications. This allows for interactive and user-friendly experiences in games and tools.
- **Gradle:** Build automation tool used for dependency management, building, and running the project.
- **Lombok:** A Java library that helps reduce boilerplate code through annotations like @Data or @SneakyThrows.
- **Serenity BDD:** A robust framework for writing and maintaining automated tests, providing detailed reports and reusable components for behavior-driven development (BDD).
- **SonarQube:** Tool for analyzing code quality, ensuring compliance with best practices and detecting bugs, code smells, and vulnerabilities.
- **MySQL:** Relational database management system used for storing data persistently.
- **OCR (Optical Character Recognition):** Integrated with Tesseract to enable text recognition capabilities within images, expanding the functionality of certain applications.
- **QRCode Generator:** Libraries such as ZXing are used for generating QR codes in the QR module.
- **Design Patterns:** Implements design patterns like Singleton for resource management and MVC (Model-View-Controller) for applications with GUIs, ensuring separation of concerns and maintainable code.
- **Java Native Libraries:** Utilized for specific functionalities like accessing file systems or manipulating images.
- **JUnit:** Framework for unit testing the core logic of applications, ensuring reliable and maintainable code.
- **Apache Commons Libraries:** Provides utility classes and methods for handling collections, IO operations, and string manipulations effectively.
- **Tessdata:** Training datasets for improving OCR recognition accuracy.


## Setup and Installation 🔧

### Clone the repository

```shell 
  git clone https://github.com/Moncada25/PackApps.git
``` 

### Java Configuration
Ensure you have Java 21 or a compatible version installed. Check the version with:

```shell 
  java -version
```

### Gradle Configuration

- Use the included gradlew script for Gradle setup.
- Alternatively, install Gradle manually if not already installed.
Verify Gradle installation with:

```shell 
  gradle -v
```

### Database Configuration

- MySQL is used for persistent data storage. Ensure the MySQL database file (.db) is present in src/resources/data/.
- If starting from scratch, initialize the database using the .sql scripts provided in the resources/data folder.
- Verify database paths in config.properties. Update this file if needed to point to the correct database file.

## Run the application 🔥
Navigate to the root directory and execute:
```shell 
  ./gradlew start
```

## Build and Packaging 📦

### Generate the Executable JAR File
To generate a runnable JAR file for the application:

1. Ensure all dependencies are included in `build/libs` by running: ```./gradlew clean build```
2. Locate the JAR file in build/libs. It will typically be named something like PackApps.jar.
3. Run the JAR file with the following command to verify it works: ```java -jar build/libs/PackApps.jar```

### Package the Application as a MacOS `.app`
Use jpackage to create a `.app` bundle for MacOS.

**Prerequisites:** <br>
- Java 14+ (for jpackage) and Java 21 (to run the application).
- Install jpackage if not included in your JDK.

**Command:** <br>
Run the following in the terminal from the root directory of the project:
```shell 
  jpackage \
  --input build/libs \
  --name "Bookverse" \
  --main-jar PackApps.jar \
  --main-class com.bookverse.packapps.core.Start \
  --type app-image \
  --vendor "Bookverse" \
  --icon src/main/resources/icons/cat.icns \
  --resource-dir src/main/resources \
  --resource-dir libs
```

**Output:** <br>
The `.app` bundle will be generated in the build directory. You can move it to `/Applications` or distribute it as needed.

### Package the Application as a Windows `.exe`
You can also create a Windows .exe installer using jpackage.

**Prerequisites:** <br>
- Java 14+ (with jpackage) installed.
- A Windows system for building the .exe.

**Command:** <br>
Run the following in the terminal (Command Prompt or PowerShell):
```shell 
  jpackage \
  --input build/libs \
  --name "Bookverse" \
  --main-jar PackApps.jar \
  --main-class com.bookverse.packapps.core.Start \
  --type exe \
  --vendor "Bookverse" \
  --icon src/main/resources/icons/cat.ico \
  --resource-dir src/main/resources \
  --resource-dir libs
```

**Output:** <br>
The `.exe` installer will be created in the `build` directory. Run this installer to set up the application on a Windows system.

## Collaborators 🤝
Do you want to contribute? Great! <br>
You found a bug? 🐛 tell me about it! or fix it yourself and open a pull request! 🚀

1. Fork the repository. 
2. Create a branch for your feature or fix: ```git checkout -b feature/new-feature```
3. Make your changes and commit them: ```git commit -m "Added new feature"```
4. Push your changes: ```git push origin feature/new-feature```
5. Open a Pull Request.

## Versioning 📌
This project implements Git as version control system, applying Trunk-based Development 🔀

## Author 🧙
**Santiago Moncada** <br>
*Software Development Engineer in Test at Rappi.*

## License 📄
This project is licensed under the terms of the MIT License.

## Contact 📧
For questions or feedback, feel free to reach out: <br>
📧 Email: [santiago.moncada.dev@gmail.com](mailto:santiago.moncada.dev@gmail.com) <br>
📚 LinkedIn: [Santiago Moncada](https://www.linkedin.com/in/santiagomoncadavelez/)