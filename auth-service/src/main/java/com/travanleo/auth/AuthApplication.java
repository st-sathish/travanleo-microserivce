package com.travanleo.auth;

import com.travanleo.auth.boot.AbstractApplicationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class AuthApplication {

    private static class Configuration extends AbstractApplicationConfiguration { }

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext ctx = SpringApplication.run(Configuration.class, args);
    }
}
