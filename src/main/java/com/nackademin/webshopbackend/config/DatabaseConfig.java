package com.nackademin.webshopbackend.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
/**
 * Created by Ashkan Amiri
 * Date:  2021-04-13
 * Time:  18:10
 * Project: webshop-backend
 * Copyright: MIT
 * Class that helps Springboot with database config if error would occur.
 */
@Configuration
public class DatabaseConfig {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbUrl);
        config.setPassword(password);
        config.setUsername(username);
        return new HikariDataSource(config);
    }

    @Bean
    public RestTemplate getTemplate() {
        return new RestTemplate();
    }
}
