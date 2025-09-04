package com.project.bug_tracking_system.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MySqlConfig {

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://shortline.proxy.rlwy.net:19301/railway?useSSL=false&allowPublicKeyRetrieval=true")
                .username("root")
                .password("ONurHvKrCjZIcFdoCnoNvcLElJvrrrAr")
                .build();
    }
}
