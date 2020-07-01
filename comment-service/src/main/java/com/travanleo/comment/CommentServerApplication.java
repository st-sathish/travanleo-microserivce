package com.travanleo.comment;

import com.travanleo.comment.boot.AbstractApplicationConfiguration;
import com.travanleo.comment.boot.db.DataSourceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

public class CommentServerApplication {

    @Import(DataSourceConfiguration.class)
    @EnableResourceServer
    private static class Configuration extends AbstractApplicationConfiguration{ }

    public static void main(String[] args) {
        SpringApplication.run(Configuration.class, args);
    }

}
