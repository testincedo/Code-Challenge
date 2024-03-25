package com.example.rqchallenge.config;

import com.example.rqchallenge.exception.RestExceptionHandler;
import com.example.rqchallenge.util.EmployeeUtil;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriTemplateHandler;

@Configuration
public class RestTemplateConfig {
 
   @Bean
   public RestTemplate restTemplateBean() {
       DefaultUriTemplateHandler defaultUriTemplateHandler = new DefaultUriTemplateHandler();
       defaultUriTemplateHandler.setBaseUrl(EmployeeUtil.BASE_URL);
       return new RestTemplateBuilder()
               .uriTemplateHandler(defaultUriTemplateHandler)
               .errorHandler(new RestExceptionHandler())
               .build();
    }

 
}