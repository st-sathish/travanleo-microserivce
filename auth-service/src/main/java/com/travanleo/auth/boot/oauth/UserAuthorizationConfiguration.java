package com.travanleo.auth.boot.oauth;

import com.travanleo.auth.security.DefaultUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@Order(1)
public class UserAuthorizationConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource ds;

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        return new DefaultUserDetailsService();
    }

    @Bean(name = "authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // BCryptPasswordEncoder(4) is used for users.password column
        /*JdbcUserDetailsManagerConfigurer<AuthenticationManagerBuilder> cfg = auth.jdbcAuthentication()
                .passwordEncoder(passwordEncoder())
                .dataSource(ds);*/
        /*cfg.getUserDetailsService().setEnableGroups(false);
        cfg.getUserDetailsService().setEnableAuthorities(false);*/
        auth.authenticationProvider(authenticationProvider());
        /*auth.inMemoryAuthentication()
                .withUser("user")
                .password(passwordEncoder().encode("secret"))
                .roles("USER");
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder().encode("secret"))
                .roles("ADMIN");*/
    }
}
