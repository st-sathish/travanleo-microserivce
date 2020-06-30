package com.travanleo.auth.boot.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class ClientAuthorizationConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private DataSource ds;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer cfg) throws Exception {
        // This will enable /oauth/check_token access
        cfg.checkTokenAccess("isAuthenticated()");
        // BCryptPasswordEncoder() is used for oauth_client_details.user_secret
        cfg.passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(ds)
                .passwordEncoder(passwordEncoder);
        /*clients.inMemory()
                .withClient("client")
                .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
                .scopes("read", "write")
                .autoApprove(true)
                .secret(passwordEncoder.encode("password"));*/
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        /*endpoints.tokenStore(tokenStore());
        endpoints.authenticationManager(authenticationManager);
        endpoints.userDetailsService(userDetailsService);*/
        endpoints
                .authenticationManager(authenticationManager)
                .tokenStore(tokenStore())
                .userDetailsService(userDetailsService);
    }
}
