package com.profiles.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "userEntityManagerFactory",basePackages = {"com.profiles.user.repo"}, transactionManagerRef = "userTransactionManager")
@EnableTransactionManagement
@PropertySource("classpath:properties/user/${spring.profiles.active}/datasource.properties")

public class UserConfig {

@Primary
    @Bean(name = "datasources")
    @ConfigurationProperties(prefix = "user.datasource")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }



    @Primary
    @Bean(name = "userEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean userEntityManagerFactoryBean(@Autowired @Qualifier("datasources")DataSource dataSource, EntityManagerFactoryBuilder builder){
      return builder.dataSource(dataSource).packages("com.profiles.user.userEntity").build();
    }

    @Primary
    @Bean(name = "userTransactionManager")
    public PlatformTransactionManager busTransactionManager(@Autowired @Qualifier("userEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
//------------------------------------------------------------------------------------------------------------------------------------------------------
}
