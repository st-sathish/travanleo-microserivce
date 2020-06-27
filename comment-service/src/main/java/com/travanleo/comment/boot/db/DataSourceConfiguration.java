package com.travanleo.comment.boot.db;

import com.travanleo.comment.boot.JDBCDriverConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Configuration
public class DataSourceConfiguration extends AbstractMongoClientConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfiguration.class);

    @Autowired
    JDBCDriverConfig config;

    @Autowired
    DataSourceProperties dataSourceProperties;


    @Override
    protected String getDatabaseName() {
        return dataSourceProperties.getDatabaseName();
    }
}
