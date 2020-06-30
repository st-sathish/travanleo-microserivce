package com.travanleo.auth;

import com.travanleo.auth.boot.AbstractApplicationConfiguration;
import com.travanleo.auth.boot.db.DataSourceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

public class AuthServerApplication {

    @Import({DataSourceConfiguration.class})
    @EnableResourceServer
    private static class Configuration extends AbstractApplicationConfiguration { }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Configuration.class, args);
    }
}
