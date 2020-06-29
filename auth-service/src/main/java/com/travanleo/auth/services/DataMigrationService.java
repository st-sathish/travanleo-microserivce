package com.travanleo.auth.services;

import com.travanleo.auth.boot.JDBCDriverConfig;
import com.travanleo.auth.boot.db.DataSourceProperties;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import org.flywaydb.core.internal.jdbc.DriverDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class DataMigrationService {

    /** The logger */
    private static final Logger logger = LoggerFactory.getLogger(DataMigrationService.class.getSimpleName());

    @Autowired
    private JDBCDriverConfig driverConfig;

    @Autowired
    private DataSourceProperties properties;

    @PostConstruct
    public void migrate() {
        String connectionProtocol = driverConfig.constructProtocol(properties.getHost(),
                properties.getPort(), properties.getDatabaseName()) ;
        DriverDataSource source = new DriverDataSource(getClass().getClassLoader(), driverConfig.getDriverClassName(),
                connectionProtocol, properties.getUserName(), properties.getPassword());
        Flyway flyway = Flyway.configure()
                .dataSource(source)
                .baselineOnMigrate(true)
                .outOfOrder(true)
                .load();
        try {
            flyway.migrate();
            logger.info("Flyway migration successfully done");
        } catch (FlywayException e) {
            logger.error("Flyway migration failed {}", e.getMessage());
            throw new FlywayException(e.getCause());
        }
    }
}
