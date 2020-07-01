package com.travanleo.comment.boot;

import com.sun.jersey.spi.spring.container.servlet.SpringServlet;
import com.travanleo.comment.filters.ResponseCorsFilter;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Servlet;

/**
 * This Configuration replaces what formerly was in web.xml.
 *
 * @see <a
 *      href="http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#howto-convert-an-existing-application-to-spring-boot">#howto-convert-an-existing-application-to-spring-boot</a>
 */
@Configuration
public class WebXmlConfiguration {

    @Bean
    public ServletRegistrationBean jersey() {
        Servlet jerseyServlet = new SpringServlet();
        ServletRegistrationBean jerseyServletRegistration = new ServletRegistrationBean();
        jerseyServletRegistration.setServlet(jerseyServlet);
        jerseyServletRegistration.addUrlMappings("/api/v1/*");
        jerseyServletRegistration.setName("jersey-servlet");
        jerseyServletRegistration.setLoadOnStartup(1);
        jerseyServletRegistration.addInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true");
        jerseyServletRegistration.addInitParameter("com.sun.jersey.spi.container.ContainerResponseFilters",
                ResponseCorsFilter.class.getName());
        jerseyServletRegistration.addInitParameter("com.sun.jersey.config.feature.DisableWADL", "true");
        // debugging for development:
        // jerseyServletRegistration.addInitParameter("com.sun.jersey.spi.container.ContainerRequestFilters",
        // LoggingFilter.class.getName());
        return jerseyServletRegistration;
    }
}