package com.travanleo.auth;

import com.travanleo.auth.boot.AbstractApplicationConfiguration;
import com.travanleo.auth.boot.db.DataSourceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Import;

public class AuthServerApplication {

    @Import({DataSourceConfiguration.class})
    private static class Configuration extends AbstractApplicationConfiguration { }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Configuration.class, args);
    }
}
