# Introduction
A sample project which demonstrates how to use RestAssured with Java.

# Tech Stack
 Following are common technologies which have been used in this project:

* https://www.java.com/en/ - is object-oriented programming language
* https://testng.org/doc/ - is a testing framework
* https://maven.apache.org/ - Dependency management and project management 
* https://circleci.com/ - Continuous Integration tool

# Prerequisites
This requires [Java JDK](https://www.oracle.com/java/technologies/javase-downloads.html) v1.8 and Maven.

# Clone the repo into a suitable folder:
```
 git clone https://github.com/dilshan5/freenow_sample_api.git
```

# Getting Started
In this project you can find two branches:
- Master - Contain up to date code
- Working - In progress development of the Test cases 

First you need to clone the 'master' branch of this project into your local machine. You can open the project by any IDE
(Select `pom.xml` file when opening the project).
You can update the project URIs in `/src/main/java/com/freenow/sample/api/common/URIs.java`

## Installation

Install the dependencies

```
 mvn clean install
```
# Project Test Structure
There are two types of tests.
* Functional Test
* End-To-End Test

# Running the Test Suite via XML file

Open `e2e.xml` or `functional.xml` test suite and run.

Note: You can change the Parameter values as you wish.

# Running the Test Suite via CLI
To run function api tests just simply type

```
 mvn clean test -P functional
```

To run e2e api tests just simply type
```
 mvn clean test -P e2e
```

# Running the Test Suite via CircleCI

Please navigate to following Link,

https://app.circleci.com/pipelines/github/dilshan5/freenow_sample_api?invite=true