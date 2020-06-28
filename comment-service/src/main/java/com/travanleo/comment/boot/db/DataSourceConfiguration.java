package com.travanleo.comment.boot.db;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.travanleo.comment.boot.JDBCDriverConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Collections;

@Configuration
public class DataSourceConfiguration extends AbstractMongoClientConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfiguration.class);

    @Autowired
    DataSourceProperties dataSourceProperties;

    @Autowired
    JDBCDriverConfig config;

    @Override
    protected String getDatabaseName() {
        return dataSourceProperties.getDatabaseName();
    }

    @Bean
    @Override
    public MongoClient mongoClient() {
        /**
         * uncomment this settings when you move to production and set username and password,
         * since I'm running mongodb without username and password. In this way, you'll have more control
         */
//        final MongoCredential credential = MongoCredential.createCredential(dataSourceProperties.getUsername(),
//                getDatabaseName(), dataSourceProperties.getPassword().toCharArray());
//
//        final MongoClientSettings settings = MongoClientSettings.builder()
//                .credential(credential)
//                .applyToSslSettings(builder -> builder.enabled(true))
//                .applyToClusterSettings(builder ->
//                        builder.hosts(Collections.singletonList(new ServerAddress(dataSourceProperties.getHost(), dataSourceProperties.getPort()))))
//                .build();
        final MongoClient client = MongoClients.create(new ConnectionString(config.getSubProtocol()+"://"+dataSourceProperties.getHost()));
        logger.info("Created New DataSource {}", client.getClusterDescription().toString());
        return client;
    }
}
