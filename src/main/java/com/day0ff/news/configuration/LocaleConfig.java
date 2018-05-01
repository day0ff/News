package com.day0ff.news.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * The class configure the message source
 */
@Configuration
public class LocaleConfig {
    /**
     * property - set logger
     */
    final static Logger logger = LoggerFactory.getLogger(LocaleConfig.class);
    /**
     * The method create a new bean message source
     *
     * @return MessageSource
     */
    @Bean
    public MessageSource messageSource () {
       logger.info("Begin Message Source");
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("locale");
        messageSource.setDefaultEncoding("UTF-8");
        logger.info("End Message Source");
        return messageSource;
    }
}
