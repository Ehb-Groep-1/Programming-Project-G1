package com.medialab.rental.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConfiguratieApplicatie implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry cr) {
        cr.addMapping("/**")
                .allowedOrigins("http://127.0.0.1:5500")
                .allowedMethods("GET", "POST", "DELETE", "PUT", "HEAD", "OPTION")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
