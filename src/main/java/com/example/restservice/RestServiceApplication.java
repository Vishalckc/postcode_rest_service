package com.example.restservice;

import com.example.restservice.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@ComponentScan(basePackageClasses = {AppConfig.class})
@SpringBootApplication
public class RestServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(RestServiceApplication.class, args);

    }

}

