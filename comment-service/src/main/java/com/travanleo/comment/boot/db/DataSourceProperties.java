package com.travanleo.comment.boot.db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
@PropertySource(value = "classpath:mongo.properties")
public class DataSourceProperties {

    @Value("${MONGO_DB_HOST:localhost}")
    private volatile @NotNull String host;

    @Value("${MONGO_DB_PORT:27017}")
    private volatile @NotNull int port;

    @Value("${DB:comment}")
    private volatile @NotNull String databaseName;

    public String getHost() {
        return this.host;
    }

    public int getPort() {
        return this.port;
    }

    public String getDatabaseName() {
        return this.databaseName;
    }
}
