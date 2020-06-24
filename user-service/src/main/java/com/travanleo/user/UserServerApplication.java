package com.travanleo.user;

import com.travanleo.user.boot.AbstractApplicationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class UserServerApplication {

    private static class Configuration extends AbstractApplicationConfiguration { }

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext ctx = SpringApplication.run(Configuration.class, args);
    }
}
