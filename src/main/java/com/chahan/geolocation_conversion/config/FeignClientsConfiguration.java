package com.chahan.geolocation_conversion.config;

import com.squareup.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientsConfiguration {

    @Bean
    public OkHttpClient httpClient() {
        return new OkHttpClient();
    }
}
