package com.day0ff.news.security;

import com.day0ff.news.controller.AdminController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import java.util.Locale;

/**
 * The class extends AuthorizationServerConfigurerAdapter and is responsible for generating tokens specific to a client.
 * The class use in-memory credentials with client as "my-trusted-client" and client secret as "secret".
 *
 * @EnableAuthorizationServer: Enables an authorization server.
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
    /**
     * property - set logger
     */
    final static Logger logger = LoggerFactory.getLogger(AdminController.class);
    /**
     * property - set MessageSource bean
     */
    @Autowired
    private MessageSource messageSource;
    /**
     * property - set TokenStore bean
     */
    @Autowired
    private TokenStore tokenStore;
    /**
     * property - set AuthenticationManager bean
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * The method register a client with client-id "my-trusted-client" and password "secret" and scope he is allowed for.
     * Specifies that any generated access token will be valid for only 300 seconds
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        String message = messageSource.getMessage("begin", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("security.client.details.service", null, "locale not found", Locale.getDefault());
        logger.info(message);

        clients.inMemory()
                .withClient("my-trusted-client")
                .authorizedGrantTypes("password")
                .scopes("read", "write", "edit")
                .secret("secret")
                .accessTokenValiditySeconds(300);

        message = messageSource.getMessage("end", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("security.client.details.service", null, "locale not found", Locale.getDefault());
        logger.info(message);
    }

    /**
     * The method allow authentication for clients. Enabled two endpoints for checking tokens.
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        String message = messageSource.getMessage("begin", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("security.authorization.server.security", null, "locale not found", Locale.getDefault());
        logger.info(message);

        oauthServer.allowFormAuthenticationForClients();

        message = messageSource.getMessage("end", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("security.authorization.server.security", null, "locale not found", Locale.getDefault());
        logger.info(message);
    }

    /**
     * The method configure AuthorizationServerEndpointsConfigurer defines the authorization and token endpoints
     * and the token services.extends WebSecurityConfigurerAdapter and provides usual.
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        String message = messageSource.getMessage("begin", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("security.authorization.server.endpoints", null, "locale not found", Locale.getDefault());
        logger.info(message);

        endpoints.tokenStore(tokenStore)
                .authenticationManager(authenticationManager);

        message = messageSource.getMessage("end", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("security.authorization.server.endpoints", null, "locale not found", Locale.getDefault());
        logger.info(message);
    }
}