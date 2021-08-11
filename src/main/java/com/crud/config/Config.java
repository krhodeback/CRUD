package com.crud.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Properties;

@Configuration
@PropertySource(value = "classpath:db.properties")
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.crud.dao", "com.crud.service"})

public class Config {
    Environment env;


    @Bean
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan(env.getProperty("db.entityPackage"));
        entityManagerFactoryBean.setJpaProperties(getHibernateProperties());
        return entityManagerFactoryBean;
    }

    @Bean
    public EntityManager getEntityManager() {
        return getEntityManagerFactoryBean().getObject().createEntityManager();
    }


    @Bean
    public Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        properties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        properties.setProperty("hibernate.enable_lazy_load_no_trans",env.getProperty("hibernate.enable_lazy_load_no_trans"));
//        properties.setProperty("hibernate.connection.autocommit", env.getProperty("hibernate.connection.autocommit"));
//        properties.setProperty("hibernate.c3p0.min_size", env.getProperty("hibernate.c3p0.min_size"));
//        properties.setProperty("hibernate.c3p0.max_size", env.getProperty("hibernate.c3p0.max_size"));
//        properties.setProperty("hibernate.c3p0.timeout", env.getProperty("hibernate.c3p0.timeout"));

        return properties;
    }

    @Bean
    public DataSource dataSource() {
        HikariConfig dataSourceConfig = new HikariConfig();
        dataSourceConfig.setDriverClassName(env.getRequiredProperty("db.driver"));
        dataSourceConfig.setJdbcUrl(env.getRequiredProperty("db.url"));
        dataSourceConfig.setUsername(env.getRequiredProperty("db.username"));
        dataSourceConfig.setPassword(env.getRequiredProperty("db.password"));
        dataSourceConfig.setAutoCommit(true);
//        dataSourceConfig.addDataSourceProperty("autoReconnect", true);
        dataSourceConfig.setMaxLifetime(45000);
        return new HikariDataSource(dataSourceConfig);
    }

    @Bean
    public PlatformTransactionManager getTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(getEntityManagerFactoryBean().getObject());
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }





    @Autowired
    public void setEnv(Environment env) {
        this.env = env;
    }
}
