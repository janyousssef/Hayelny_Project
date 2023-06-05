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
        ds.setMinimumIdle(2);
        ds.setConnectionTimeout(5000);
        return ds;
    }
    @Profile("dev")
    @Bean
    public HikariDataSource devDataSource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:postgresql://containers-us-west-83.railway.app:5582/railway");
        ds.setConnectionTimeout(5000);
        ds.setMinimumIdle(2);
        ds.setUsername("postgres");
        ds.setPassword("zRLcqfpT2TUO1S0EYjKg");
        return ds;
    }
}
