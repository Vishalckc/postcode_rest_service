package com.example.restservice.config;

import com.example.restservice.controller.AppErrorController;
import com.example.restservice.controller.RestController;
import com.example.restservice.service.RestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EntityScan("com.example.restservice.model")
@Configuration
@ComponentScan(basePackageClasses = {RestServiceImpl.class, RestController.class})
@ComponentScan(basePackages = {"com.example.restservice.service"})
public class AppConfig {
    @Autowired
    private ErrorAttributes errorAttributes;

    @Bean
    public AppErrorController appErrorController() {
        return new AppErrorController(errorAttributes);
    }

}