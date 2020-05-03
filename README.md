# **PackApps**

## Introduction 🚀

Set of small applications for different purposes. Games, tools and even a store; they are just some options that PackApps has integrated, and yes, it has a dark mode.

#### New features!

  - Optical Character Recognition (OCR)
  - Integration with automated testing
  - Sending emails to any recipient

## Pre requirements 📋
- Java v1.8 update 151 or higher and JDK (environment variables configured).
- Eclipse IDE (Oxigen or Neon) or IntelliJ IDEA (v2018.3 or higher).
- Gradle v4.7 or higher (environment variables configured).
- Xampp and database.

## Installation 🔧
- To clone this repository locally, the following command must be run: 
```git clone https://github.com/Moncada25/PackApps.git``` 
- Import the project from Eclipse or IntelliJ IDE under the structure of an existing Gradle project. 
- Configure JRE System Library with JavaSE-1.8.
- Configure the encoding to UTF-8 to the project once it is imported.

## Compile the project and generate Wrapper 🔨
- To build the project you must run the command:
```gradle clean build -x test```
- To generate the project wrapper files, you must run the command:
```gradle wrapper --gradle-distribution-url https://services.gradle.org/distributions/gradle-5.2.1-all.zip```

## Web browsers 🌐
Automation currently runs in the following browsers:
- Google Chrome v80 or higher.

## Project structure 🚧

* ```src/main/java/com/bookverse/development/packapps```
``` 
+ automation
    Classes needed to perform automation.
    - exceptions
    - models
    - questions
    - runners
    - tasks
    - userinterfaces
    - utils

+ core
    Classes that contain general application settings.

+ models
    Classes with which the data models are built and the connection is made.

+ utils
    Classes that contain common functionalities.

+ views
    Classes that contain the design of the applications.
```

* ```src/main/resources```
``` 
+ data
    Contains the sql and properties file.

+ images
    Contains the images used in the application.
```

## Built with 🛠
PackApps was developed with:
 - MVC - Development strategy
 - Screenplay - To automation
 - Gradle - Dependency manager
 - Selenium Web Driver - Tool to automate actions in web browsers
 - Serenity BDD - Open source library for report generation
 - JTattoo - To add themes
 - Javax Mail - To send emails by Gmail
 - iTextPDF - To manage PDF files
 - MySQL - As a database engine
 - Tess4j - To use OCR
 - Commons-Pool2 - To create the connection pool
 - Poi - To manage XLS files
 - JUnit - To test
 
## Versioning 📌 
Git was used for version control, applying GitFlow 🔀

## Authors ✒

* **Santiago Moncada** - [zanti4020@gmail.com]()

## Taxpayers 🙂

Do you want to contribute? Great!
>You found a bug? tell me!

## To do 📋

 - Write more tests.
 - Add more applications.
 - Finish Tic Tac Toe game which is in beta version, in Player vs CPU option.