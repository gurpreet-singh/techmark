package com.techmark.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by gurpreets on 29/06/18.
 */
@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = { "com.techmark.coredb.entities" })
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager", basePackages = { "com.techmark.coredb.repository" })
public class EntityManagerConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntityManagerConfig.class);
    @Autowired
    private Environment env;
    /** The 5iveC primary data source. */
    @Autowired
    @Qualifier("techmarkDataSource")
    private DataSource techMarkDataSource;

    @Value("${spring.profiles.active}")
    private String activeProfile;

    private static EntityManager entityManager;

    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(techMarkDataSource);
        factory.setJpaVendorAdapter(jpaVendorAdapter());
        factory.setPackagesToScan("com.techmark.entities");//Entities Packages to Scan
        factory.setJpaProperties(jpaProperties());
        factory.setPersistenceUnitName("techMarkPersistenceUnit");
        factory.afterPropertiesSet();
        factory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
        return factory;
    }

    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager fcTransactionManager(EntityManagerFactory factory) {

        return new JpaTransactionManager(factory);
    }

    private JpaVendorAdapter jpaVendorAdapter() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(Boolean.FALSE);
        vendorAdapter.setShowSql(Boolean.FALSE);
        return vendorAdapter;
    }

    private Properties jpaProperties() {

        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.connection.autoReconnect", true);
        jpaProperties.put("hibernate.connection.autoReconnectForPools", true);
        jpaProperties.put("hibernate.dialect", env.getProperty("com.techmark.spring.jpa.properties.hibernate.dialect"));
        jpaProperties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));

        if ( !StringUtils.isEmpty(env.getProperty("hibernate.hbm2ddl.auto"))) {
            jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        }
        return jpaProperties;
    }

    public EntityManager getEntityManager(){
        if(this.entityManager == null || ! this.entityManager.isOpen())
        {
            this.entityManager = entityManagerFactory().getObject().createEntityManager();
        }
        return this.entityManager ;
    }
    public void close(){
        if(this.entityManager != null && this.entityManager.isOpen())
        {
            entityManagerFactory().getObject().close();
        }
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
