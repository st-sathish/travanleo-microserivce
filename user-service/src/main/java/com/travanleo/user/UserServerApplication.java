package com.travanleo.user;

import com.travanleo.user.boot.AbstractApplicationConfiguration;
import com.travanleo.user.boot.db.DataSourceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

public class UserServerApplication {

    @Import(DataSourceConfiguration.class)
    private static class Configuration extends AbstractApplicationConfiguration { }

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext ctx = SpringApplication.run(Configuration.class, args);
    }
}
