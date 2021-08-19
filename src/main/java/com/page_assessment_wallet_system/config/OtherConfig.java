package com.page_assessment_wallet_system.config;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class OtherConfig {

    @Bean
    private Gson getGson(){
        return new Gson();
    }
}
