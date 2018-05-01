package com.day0ff.news.security;

import com.day0ff.news.controller.AdminController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.annotation.Resource;
import java.util.Locale;
import java.util.Properties;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class OAuth2SecurityConfiguration extends WebSecurityConfigurerAdapter {
    /**
     * property - set logger
     */
    final static Logger logger = LoggerFactory.getLogger(AdminController.class);
    /**
     * property - set MessageSource bean
     */
    @Autowired
    private MessageSource messageSource;


    @Resource(name = "userDetailsImpl")
    private UserDetailsService userDetailsService;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        String message = messageSource.getMessage("beginEnd", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("security.authentication.manager.bean", null, "locale not found", Locale.getDefault());
        logger.info(message);

        return super.authenticationManagerBean();
    }

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        String message = messageSource.getMessage("begin", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("security.authentication.manager.builder", null, "locale not found", Locale.getDefault());
        logger.info(message);

        auth.userDetailsService(userDetailsService);

        message = messageSource.getMessage("end", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("security.authentication.manager.builder", null, "locale not found", Locale.getDefault());
        logger.info(message);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String message = messageSource.getMessage("begin", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("security.http.security", null, "locale not found", Locale.getDefault());
        logger.info(message);

        http
                .csrf().disable()
                .anonymous().disable()
                .authorizeRequests()
                .antMatchers("/oauth/token").permitAll();

        message = messageSource.getMessage("end", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("security.http.security", null, "locale not found", Locale.getDefault());
        logger.info(message);
    }

    @Bean
    public TokenStore tokenStore() {
        String message = messageSource.getMessage("beginEnd", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("security.token.store", null, "locale not found", Locale.getDefault());
        logger.info(message);

        return new InMemoryTokenStore();
    }

}
