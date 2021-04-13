package com.nackademin.webshopbackend.config;

import com.zaxxer.hikari.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import javax.sql.DataSource;
/**
 * Created by Ashkan Amiri
 * Date:  2021-04-13
 * Time:  18:10
 * Project: webshop-backend
 * Copyright: MIT
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
}
