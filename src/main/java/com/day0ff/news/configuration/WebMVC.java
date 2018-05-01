package com.day0ff.news.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
/**
 * The class create and configure the web MVC
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.day0ff.news")
public class WebMVC extends WebMvcConfigurerAdapter {
    /**
     * property - set logger
     */
    final static Logger logger = LoggerFactory.getLogger(DataConfig.class);
    /**
     * property - set MessageSource bean
     */
    @Autowired
    private MessageSource messageSource;

    /**
     * The method create a new bean object message converter
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        String message = messageSource.getMessage("begin", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("config.web", null, "locale not found", Locale.getDefault());
        logger.info(message);
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(new ObjectMapper());
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        converters.add(converter);
        message = messageSource.getMessage("end", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("config.web", null, "locale not found", Locale.getDefault());
        logger.info(message);
    }

    /**
     * The method stores registrations of resource handlers for serving static resources such as images, css files and others through Spring MVC including setting cache headers optimized for efficient loading in a web browser. Resources can be served out of locations under web application root, from the classpath, and others.
     * To create a resource handler, use addResourceHandler(String...) providing the URL path patterns for which the handler should be invoked to serve static resources (e.g. "/resources/**").
     *
     * Then use additional methods on the returned ResourceHandlerRegistration to add one or more locations from which to serve static content from (e.g. {"/", "classpath:/META-INF/public-web-resources/"}) or to specify a cache period for served resources.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String message = messageSource.getMessage("begin", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("config.web.handler", null, "locale not found", Locale.getDefault());
        logger.info(message);
        registry.addResourceHandler("*").addResourceLocations("");
        message = messageSource.getMessage("end", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("config.web.handler", null, "locale not found", Locale.getDefault());
        logger.info(message);
    }

    /**
     * The method stores registrations of view controllers. A view controller does nothing more than return a specified view name. It saves you from having to write a controller when you want to forward the request straight through to a view such as a JSP.
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        String message = messageSource.getMessage("begin", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("config.web.view", null, "locale not found", Locale.getDefault());
        logger.info(message);
        registry.addViewController("/").setViewName("forward:/index.html");
        registry.addViewController("/home").setViewName("forward:/index.html");
        registry.addViewController("/Новости").setViewName("forward:/index.html");
        registry.addViewController("/news").setViewName("forward:/index.html");
        registry.addViewController("/news/detail/*").setViewName("forward:/index.html");
        registry.addViewController("/edit").setViewName("forward:/index.html");
        registry.addViewController("/edit/detail/*").setViewName("forward:/index.html");
        registry.addViewController("/category/*").setViewName("forward:/index.html");
        registry.addViewController("/person/info").setViewName("forward:/index.html");
        registry.addViewController("/persons").setViewName("forward:/index.html");
        registry.addViewController("/person/detail/*").setViewName("forward:/index.html");
        registry.addViewController("/registration").setViewName("forward:/index.html");
        message = messageSource.getMessage("end", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("config.web.view", null, "locale not found", Locale.getDefault());
        logger.info(message);
    }
}