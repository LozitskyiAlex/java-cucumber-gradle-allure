# Java - Selenide - Cucumber - TestNG - Gradle - Allure report

## Getting Started

These instructions will help you to run project on your machine for testing purposes 

## Built With

* [Gradle](https://gradle.org) - Build tool
* [TestNG](https://testng.org/doc/) - Java testing framework
* [Selenide](https://selenide.org) - WebDriver wrapper framework
* [Cucumber](https://cucumber.io) - BDD testing tool
* [Allure](http://allure.qatools.ru) - Test report and cli tool
 
## Running the tests

### 1. clean, build and run tests
```console
$ ./gradlew clean test --info
```

### 2. generate allure-report
At first you need to [install Allure commandline](https://docs.qameta.io/allure/#_installing_a_commandline)
```console
$ allure serve
```