package com.page_assessment_wallet_system.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class EnvironmentConfig {

    private final Environment env;

    public EnvironmentConfig(Environment env) {
        this.env = env;
    }

    public String getProperty(String propertyKey) {
        return env.getProperty(propertyKey);
    }
}
