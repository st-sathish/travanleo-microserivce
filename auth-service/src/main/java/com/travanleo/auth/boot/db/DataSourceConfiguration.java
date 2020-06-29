package com.travanleo.auth.boot.db;

import com.travanleo.auth.boot.JDBCDriverConfig;
import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfiguration.class.getSimpleName());

    @Autowired
    private JDBCDriverConfig driverConfig;

    @Autowired
    private DataSourceProperties properties;

    @Bean
    public DataSource dataSource(){
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(driverConfig.getDriverClassName());
        String url = driverConfig.constructProtocol(properties.getHost(), properties.getPort(), properties.getDatabaseName());
        dataSourceBuilder.url(url);
        dataSourceBuilder.username(properties.getUserName());
        dataSourceBuilder.password(properties.getPassword());
        logger.info("Created DataSource Bean");
        return dataSourceBuilder.build();
    }
}
