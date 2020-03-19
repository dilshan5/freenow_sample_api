# Introduction
A sample project which demonstrates how to use RestAssured with Java.

### Tech
This test uses a number of open source projects to work properly:

* https://testng.org/doc/ - is a testing framework
* https://maven.apache.org/ - Dependency management and project management 
* https://circleci.com/ - Continuous Integration tool
* https://jsonplaceholder.typicode.com/ - Endpoints that are used in this porject

##Clone the repo into a suitable folder:
```sh
$ git clone https://github.com/dilshan5/freenow_sample_api.git
```

##Branches available:
In this project you can find two branches:
 * Matser - Contain up to date code
 * Working - In progress development of the Test cases 

### Installation
This requires [Java JDK](https://www.oracle.com/java/technologies/javase-downloads.html) v1.8 to run.

Install the dependencies

```sh
$ mvn clean install
```
### Project Test Structure
There are two types of tests.
* Functional Test
* End-To-End Test

### How to trigger API tests
To run function api tests just simply type

```sh
$ mvn clean test -P functional
```

To run e2e api tests just simply type

```sh
$ mvn clean test -P e2e
```