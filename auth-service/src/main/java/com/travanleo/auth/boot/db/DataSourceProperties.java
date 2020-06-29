package com.travanleo.auth.boot.db;

import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceProperties {

    private String host = "localhost";

    private String port = "3306";

    private String userName = "root";

    private String password = "mysql";

    private String databaseName = "travanleo_auth";

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }
}
