package com.travanleo.comment.boot;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;


@Configuration
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class,
		MongoAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class,
		FlywayAutoConfiguration.class })
@PropertySource(value = "classpath:META-INF/jdbc.properties")
@ImportResource({ "classpath*:META-INF/appContext.xml" })
public abstract class AbstractApplicationConfiguration {

}
