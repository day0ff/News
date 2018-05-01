package com.day0ff.news.configuration;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Locale;
import java.util.Properties;

/**
 * The class Spring data type configuration
 */
@Configuration
@EnableTransactionManagement
@ComponentScan("com.day0ff.news")
@PropertySource("classpath:app.properties")
@EnableJpaRepositories("com.day0ff.news.repository")
public class DataConfig {
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
     * property - set database driver
     */
    @Value("${db.driver}")
    private String propDatabaseDriver;
    /**
     * property - set database password
     */
    @Value("${db.password}")
    private String propDatabasePassword;
    /**
     * property - set database url
     */
    @Value("${db.url}")
    private String propDatabaseUrl;
    /**
     * property - set database user name
     */
    @Value("${db.username}")
    private String propDatabaseUserName;
    /**
     * property - set hibernate dialect
     */
    @Value("${db.hibernate.dialect}")
    private String propHibernateDialect;
    /**
     * property - set hibernate show sql
     */
    @Value("${db.hibernate.show_sql}")
    private String propHibernateShowSql;
    /**
     * property - set entity manager package
     */
    @Value("${db.entitymanager.packages.to.scan}")
    private String propEntityManagerPackagesToScan;
    /**
     * property - set hibernate hbm2ddl
     */
    @Value("${db.hibernate.hbm2ddl.auto}")
    private String propHibernateHbm2ddlAuto;
    /**
     * The method create a new bean object data source
     *
     * @return DataSource
     */
    @Bean
    public DataSource dataSource() {
        String message = messageSource.getMessage("begin", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("config.data.source", null, "locale not found", Locale.getDefault());
        logger.info(message);
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(propDatabaseDriver);
        dataSource.setUrl(propDatabaseUrl);
        dataSource.setUsername(propDatabaseUserName);
        dataSource.setPassword(propDatabasePassword);

        message = messageSource.getMessage("end", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("config.data.source", null, "locale not found", Locale.getDefault());
        logger.info(message);
        return dataSource;
    }

    /**
     * The method create a new bean object entity manager factory
     *
     * @return LocalContainerEntityManagerFactoryBean
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        String message = messageSource.getMessage("begin", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("config.data.entity", null, "locale not found", Locale.getDefault());
        logger.info(message);
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan(propEntityManagerPackagesToScan);
        entityManagerFactoryBean.setJpaProperties(getHibernateProperties());
        message = messageSource.getMessage("end", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("config.data.entity", null, "locale not found", Locale.getDefault());
        logger.info(message);
        return entityManagerFactoryBean;
    }

    /**
     * The method create a new bean object JPA transaction manager
     *
     * @return JpaTransactionManager
     */
    @Bean
    public JpaTransactionManager transactionManager() {
        String message = messageSource.getMessage("begin", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("config.data.transaction", null, "locale not found", Locale.getDefault());
        logger.info(message);
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        message = messageSource.getMessage("end", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("config.data.transaction", null, "locale not found", Locale.getDefault());
        logger.info(message);
        return transactionManager;
    }

    /**
     * The method сreate a new bean object JPA vendor adapter
     *
     * @return JpaVendorAdapter
     */
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    private Properties getHibernateProperties() {
        String message = messageSource.getMessage("begin", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("config.data.properties", null, "locale not found", Locale.getDefault());
        logger.info(message);
        Properties properties = new Properties();
        properties.put("hibernate.dialect", propHibernateDialect);
        properties.put("hibernate.show_sql", propHibernateShowSql);
        properties.put("hibernate.format_sql", propHibernateHbm2ddlAuto);
        message = messageSource.getMessage("end", null, "locale not found", Locale.getDefault())
                + " " + messageSource.getMessage("config.data.properties", null, "locale not found", Locale.getDefault());
        logger.info(message);
        return properties;
    }

}