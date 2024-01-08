package com.example.base.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author AnhNHH.
 * Class cấu hình chung cho Spring Boot.
 */
@Configuration
public class SpringConfig {

    /**
     * Cung cấp một triển khai của ObjectMapper cho việc mapping dữ liệu giữa 2 objects.
     *
     * @return triển khai ObjectMapper.
     */
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return objectMapper;
    }

    /**
     * Cung cấp một triển khai của ModelMapper cho việc mapping dữ liệu giữa 2 objects.
     *
     * @return triển khai ModelMapper.
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
