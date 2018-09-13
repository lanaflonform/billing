package com.vectree.billing.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Spring JPA confiruration.
 *
 * @version 0.1
 */
@Configuration
@ComponentScan("com.vectree.billing.domain")
@EnableJpaRepositories("com.vectree.billing.service.dao")
@PropertySource("/WEB-INF/properties/database.properties")
public class JpaConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private Environment env;

    // Declare a JPA Data Source
    @Bean
    public DataSource jpaDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driver"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));

        return dataSource;
    }

    // Declare Vendor Adapter
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(false);
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        hibernateJpaVendorAdapter.setDatabase(Database.POSTGRESQL);

        return hibernateJpaVendorAdapter;
    }

    // Declare a JPA Entity Manager Factory
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            DataSource jpaDataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(jpaDataSource);
        bean.setJpaVendorAdapter(jpaVendorAdapter);
        bean.setPackagesToScan("com.vectree.billing.domain");

        return bean;
    }

    // Declare a Transaction Manager
    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory(jpaDataSource(),
                jpaVendorAdapter()).getObject());

        return transactionManager;
    }

    @Bean(initMethod = "migrate")
    protected Flyway flyway() {
        Flyway flyway = new Flyway();
        flyway.setValidateOnMigrate(true);
        //flyway настойчиво просил init() если база пустая
        flyway.setInitOnMigrate(true);
        flyway.setLocations("classpath:db/migration");
        flyway.setDataSource(jpaDataSource());
        return flyway;
    }

    @Bean
    @DependsOn("flyway")
    protected EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(jpaDataSource());
        return bean.getObject();
    }
}
