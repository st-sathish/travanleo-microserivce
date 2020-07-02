### Getting Started

- Create two databases in MySQL
```sh
CREATE DATABASE travanleo CHARACTER SET utf8 COLLATE utf8_general_ci
CREATE DATABASE travanleo_auth CHARACTER SET utf8 COLLATE utf8_general_ci
```

- Create Database in MongoDB
```sh
use travanleo
```

### Build and Run
- create executeable jar file
```sh
./gradlew clean build
```

### Note
- The above command create 3 jar files
~ After gradle command executed, the jar files were located inside project root directory/build/libs
- database table were created by flyway in runtime
- create mysql username/password as root/mysql.In case, if you want to change it then you need to do changes in auth-service and user-service respectively
- Run those service in order

### Files to change mysql username and password
- auth-service com.travanleo.auth.boot.db.DataSourceProperties
- user-service com.travanleo.user.boot.db.DataSourceProperties
- user-service build.gradle
- comment-service doesn't require username/password for mongodb connection

#### Run Oauth service in port 8080
```sh
java -jar auth-service-0.0.1-SNAPSHOT.jar
```

#### Run Comment service in port 8081
```sh
java -jar comment-service-0.0.1-SNAPSHOT.jar
```

#### Run user service in port 7070
```sh
java -jar user-service-0.0.1-SNAPSHOT.jar
```

## How to check those API's. Run all the API's in POSTMAN tool
- Run auth-service API to get token by setting authorization header as Authorization, select Basic Auth
and set username:appclient and password:appclient@123 in the body give the below form-data
- POST http://localhost:8080/oauth/token
```sh
username: john
password: secret
grant_type: password
```
- You will get the below result
```sh
{
"access_token": "0421f518-8bc2-4651-84ac-da22cceeb176",
"token_type": "bearer",
"refresh_token": "ceea6454-6979-4640-826d-cb5f3e773de5",
"expires_in": 999999,
"scope": "read write"
}
```

- Please find all the screenshots inside docs folder