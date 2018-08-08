package com.vectree.billing.config;

import com.vectree.billing.domain.Account;
import com.vectree.billing.domain.User;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Properties;
import javax.sql.DataSource;

/**
 * Hibernate configuration.
 *
 * @version 0.1
 */
@Configuration
@SuppressWarnings("unused")
@EnableTransactionManagement
@ComponentScan("com.vectree.billing.domain")
@PropertySource("/WEB-INF/properties/database.properties")
public class HibernateConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private Environment env;

    // Declare a JPA Data Source
    @Bean
    public DataSource dataSourceHibernate() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driver"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSourceHibernate());
        sessionFactory.setPackagesToScan(new String[]{"com.vectree.billing.domain"});
        sessionFactory.setHibernateProperties(hibernateProperties());
        sessionFactory.setAnnotatedClasses(new Class[]{Account.class, User.class});
        return sessionFactory;
    }

    @Autowired
    @Bean(name = "transactionManager")
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", env.getProperty("jdbc.dialect"));
        properties.setProperty("hibernate.globally_quoted_identifiers", "true");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.connection.characterEncoding", "utf8");
        properties.setProperty("hibernate.connection.CharSet", "utf8");
        //properties.setProperty("hibernate.hbm2ddl.auto", "update");
        //properties.setProperty("hibernate.cache.use_second_level_cache", "true);
        //properties.setProperty("hibernate.connection.autocommit", "true");
        return properties;
    }
}
