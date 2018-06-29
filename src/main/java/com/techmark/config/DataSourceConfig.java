package com.techmark.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

import javax.sql.DataSource;

/**
 * Created by gurpreets on 29/06/18.
 */
@Configuration
public class DataSourceConfig {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceConfig.class);

    /** The env. */
    @Autowired
    private Environment env;

    /**
     *  5iveC Read write data source for dev environment. DB configuration is loaded from env-dev properties.
     *
     * @return the 5iveC Primary data source
     */
    @Profile("dev")
    @Primary
    @Bean(name = "techmarkDataSource", autowire = Autowire.BY_NAME)
    public DataSource fcRWDataSourceDev() {

        LOGGER.info("Loading  TechMark Test Data Source for Dev Environment");
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getProperty("com.techmark.spring.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("com.techmark.spring.datasource.url"));
        dataSource.setUsername(env.getProperty("com.techmark.spring.datasource.username"));
        dataSource.setPassword(env.getProperty("com.techmark.spring.datasource.password"));
        dataSource.setTestOnBorrow(true);
        dataSource.setValidationQuery("SELECT 1");
        LOGGER.info("Successfully loaded  TechMark Test Data Source for Dev Environment");
        return dataSource;
    }

}
