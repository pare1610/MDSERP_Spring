package com.proelectricos.mdserp.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
//@PropertySource({"classpath:persistence-multiple-db-boot.properties"})
@EnableJpaRepositories(
        basePackages = "com.proelectricos.mdserp.repository.sqlfactory",
        entityManagerFactoryRef = "sqlFactoryEntityManager",
        transactionManagerRef = "sqlFactoryTransactionManager")
public class PersistenceSqlFactoryAutoConfiguration {

    @Autowired
    private Environment env;

    @Primary
    @Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource sqlFactoryDataSource() {
        return DataSourceBuilder.create().build();
    }
    // sqlFactoryEntityManager bean
    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean sqlFactoryEntityManager() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(sqlFactoryDataSource());
        em.setPackagesToScan(
                new String[] { "com.proelectricos.mdserp.model.entity.sqlfactory" });

        HibernateJpaVendorAdapter vendorAdapter
                = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.ddl-auto",
                env.getProperty("hibernate.ddl-auto"));
        properties.put("hibernate.dialect",
                env.getProperty("hibernate.dialect"));
        em.setJpaPropertyMap(properties);

        return em;
    }


   // userTransactionManager bean

    @Primary
    @Bean
    public PlatformTransactionManager sqlFactoryTransactionManager() {

        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                sqlFactoryEntityManager().getObject());
        return transactionManager;
    }
}