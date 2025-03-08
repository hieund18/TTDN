package com.haui.bookshopwebsite.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    private final String CLOUD_NAME = "dasnmrqjk";
    private final String API_KEY = "116245812934643";
    private final String API_SECRET = "AJVImHnyIeTbGsZ3TboI7e5YzKA";

    @Bean
    public Cloudinary configCloudinary() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", CLOUD_NAME);
        config.put("api_key", API_KEY);
        config.put("api_secret", API_SECRET);
        return new Cloudinary(config);
    }

}
