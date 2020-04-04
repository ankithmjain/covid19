package com.corona.covid19.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration
{
    @Bean
    public WebMvcConfigurer corsConfigurer()
    {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:4200","https://coronastatonline.herokuapp.com","https://www.coronastat.online","http://www.coronastat.online","https://coronastat.online","http://coronastat.online","gocoronastat.com","http://gocoronastat.com","https://gocoronastat.com","http://www.gocoronastat.com","https://www.gocoronastat.com");
            }
        };
    }
}

