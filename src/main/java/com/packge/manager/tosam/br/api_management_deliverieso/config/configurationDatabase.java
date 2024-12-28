package com.packge.manager.tosam.br.api_management_deliverieso.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class configurationDatabase {

    @Value("${spring.datasource.url}")
    String url;

    @Value("${spring.datasource.username}")
    String username;

    @Value("${spring.datasource.password}")
    String password;

    @Value("${spring.datasource.driver-class-name}")
    String driver;


    @Bean
    DataSource hikaryDatasource(){

        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setUsername(username);
        hikariConfig.setDriverClassName(driver);
        hikariConfig.setPassword(password);
        hikariConfig.setJdbcUrl(url);

        hikariConfig.setPoolName("api_management_deliverieso");
        hikariConfig.setMinimumIdle(1);
        hikariConfig.setMaximumPoolSize(20);
        hikariConfig.setMaxLifetime(20000);
        hikariConfig.setConnectionTestQuery("select 1");
        hikariConfig.setConnectionTimeout(1200);

        return new HikariDataSource(hikariConfig);
    }


}
