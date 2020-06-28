package com.travanleo.comment.boot.db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Configuration
@PropertySource(value = "classpath:mongo.properties")
public class DataSourceProperties {

    @Value("${MONGO_DB_HOST:localhost}")
    private volatile @NotNull String host;

    @Value("${MONGO_DB_PORT:27017}")
    private volatile @NotNull int port;

    @Value("${DB:comment}")
    private volatile @NotNull String databaseName;

    @Value("${MONGO_DB_USERNAME:comment}")
    private volatile @NotNull String username;

    @Value("${MONGO_DB_PASSWORD:sa123}")
    private volatile @NotNull String password;

    public String getHost() {
        return this.host;
    }

    public int getPort() {
        return this.port;
    }

    public String getDatabaseName() {
        return this.databaseName;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
}
