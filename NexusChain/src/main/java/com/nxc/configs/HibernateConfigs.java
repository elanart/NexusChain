package com.nxc.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.*;

@Configuration
@PropertySource("classpath:database.properties")
public class HibernateConfigs {
    @Autowired
    private Environment env;

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean sessionFactory
                = new LocalSessionFactoryBean();
        sessionFactory.setPackagesToScan(new String[]{
                "com.nxc.pojo",
                "com.nxc.enums"
        });
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource
                = new DriverManagerDataSource();
        dataSource.setDriverClassName(
                Objects.requireNonNull(env.getProperty("hibernate.connection.driverClass")));
        dataSource.setUrl(env.getProperty("hibernate.connection.url"));
        dataSource.setUsername(
                env.getProperty("hibernate.connection.username"));
        dataSource.setPassword(
                env.getProperty("hibernate.connection.password"));
        return dataSource;
    }

    private Properties hibernateProperties() {
        Properties props = new Properties();
        props.put(DIALECT, env.getProperty("hibernate.dialect"));
        props.put(SHOW_SQL, env.getProperty("hibernate.showSql"));

        props.put(HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));
        return props;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager
                = new HibernateTransactionManager();
        transactionManager.setSessionFactory(
                getSessionFactory().getObject());
        return transactionManager;
    }
}
