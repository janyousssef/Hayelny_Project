package com.hayelny.core;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
public class DBConfig {
    @Profile("prod")
    @Bean
    public HikariDataSource prodDataSource() {
    HikariDataSource ds = new HikariDataSource();
    ds.setJdbcUrl("jdbc:postgresql://localhost:5432/hayelny");
    ds.setUsername("postgres");
    ds.setPassword("admin");
    return ds;
    }
    @Profile("dev")
    @Bean
    public HikariDataSource devDataSource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:postgresql://containers-us-west-4.railway.app:6845/railway");
        ds.setUsername("postgres");
        ds.setPassword("tifasvfVkycHfHRTCYcH");
        return ds;
    }
}
