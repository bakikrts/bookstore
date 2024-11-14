package com.task1.bookstore.config; // Bu kısım proje yapınıza göre değişebilir

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig  implements WebMvcConfigurer {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Tüm endpoint'ler için CORS ayarı yapıyoruz
                        .allowedOrigins("http://localhost:3000") // Frontend'in çalıştığı URL
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // İzin verilen HTTP metodları
                        .allowedHeaders("*"); // Tüm başlıklar
            }
        };
    }
}
