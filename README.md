### Getting Started

- Change mysql username and password in user-service/build.gradle
- Create database by executing the below command
```sh
gradle clean createDB --dbName=travanleo
```

### Build and Run
- create executeable jar file
```sh
gradle clean bootJar
```
- Run user service
```sh
java -jar user-service
```