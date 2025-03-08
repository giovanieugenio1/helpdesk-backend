package com.giovani.helpdesk.config;

import com.giovani.helpdesk.service.DBService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("dev")
@Configuration
public class DevProfileConfig {

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String value;

    private final DBService dbService;

    public DevProfileConfig(DBService dbService) {
        this.dbService = dbService;
    }

    @Bean
    public boolean initDb() {
        if (value.equals("create")) {
            this.dbService.initDB();
        }
        return false;
    }
}
