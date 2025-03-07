package com.giovani.helpdesk.config;

import com.giovani.helpdesk.service.DBService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class TestProfileConfig {

    private final DBService dbService;

    public TestProfileConfig(DBService dbService) {
        this.dbService = dbService;
    }

    @Bean
    public void initDb() {
        this.dbService.initDB();
    }
}
