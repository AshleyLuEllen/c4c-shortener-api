package dev.c4c;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class C4CApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(C4CApiApplication.class);
    }

    // TODO: refractor to environment variable
    private static final String[] allowedOrigins = {
        "http://localhost:3000",
        "https://c4c-frontend.herokuapp.com"
    };

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
//                    .allowedOrigins("*")
//                    .allowedOrigins(allowedOrigins)
                ;
            }
        };
    }
}
