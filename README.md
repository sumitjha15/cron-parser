## cron-parser
This Project parses a cron string and expands each field to show the times at which it will run.
We have considered only five time fields (minute, hour, day of month, month, and day of week) in our implementation.

## Getting Started


## Prerequisites
For building and running the application you need:

- [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven 3.5](https://maven.apache.org)
- [IntelliJ](https://www.jetbrains.com/idea/)


## Running the application locally

To run a Spring Boot application in our local, we have several options.
- Launch `main` method in class `com.cronparser.demo.CronParserApplication` using some IDE like IntelliJ, eclipse or terminal.
- We can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:
  ```shell
  mvn spring-boot:run
  ```
- Alternatively, use `java -jar` command to launch application from terminal.
  ```shell
  mvn package
  ```
  ```shell
  java -jar target/cron-parser-0.0.1-SNAPSHOT.jar "*/5 2 1,15 * * /usr/bin/find" 
  ```
- Similarly, we can run test cases as well via IDE or command line using maven command. 
  ```shell
  mvn test
  ```

## Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.3/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.3/maven-plugin/reference/html/#build-image)

