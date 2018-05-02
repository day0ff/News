package com.day0ff.news.security;

import com.day0ff.news.controller.AdminController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

import java.util.Locale;

/**
 * The class ResourceServerConfiguration hosts the resources [our REST API] the client is interested in. Resources are located
 * on user, editor and admin. @EnableResourceServer annotation, applied on OAuth2 Resource Servers, enables a Spring Security filter
 * that authenticates requests using an incoming OAuth2 token. Class ResourceServerConfigurerAdapter implements
 * ResourceServerConfigurer providing methods to adjust the access rules and paths that are protected by OAuth2 security.
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
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
     * property - of resource
     */
    private static final String RESOURCE_ID = "my_rest_api";

    /**
     * The method overrode resource for "my_rest_api". stateless() flag indicate that only token-based authentication
     * is allowed on these resources.
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        String message = messageSource.getMessage("begin", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("security.resource.server.security", null, "locale not found", Locale.getDefault());
        logger.info(message);

        resources.resourceId(RESOURCE_ID).stateless(false);

        message = messageSource.getMessage("end", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("security.resource.server.security", null, "locale not found", Locale.getDefault());
        logger.info(message);
    }

    /**
     * The method configures the access rules and request matchers (path) for protected resources using
     * the HttpSecurity class. Secure the URL paths.
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        String message = messageSource.getMessage("begin", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("security.resource.server.http.security", null, "locale not found", Locale.getDefault());
        logger.info(message);

        http.anonymous().disable().authorizeRequests()
                .antMatchers("/", "/*.html").permitAll()
                .antMatchers("/user**").hasAuthority("USER")
                .antMatchers("/editor**").hasAuthority("EDITOR")
                .antMatchers("/admin**").hasAuthority("ADMIN")
                .and()
                .exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());

        message = messageSource.getMessage("end", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("security.resource.server.http.security", null, "locale not found", Locale.getDefault());
        logger.info(message);
    }

}