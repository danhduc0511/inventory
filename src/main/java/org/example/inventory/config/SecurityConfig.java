package org.example.inventory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Tắt CSRF
                .authorizeHttpRequests(auth -> auth
                        // 👇 1. MỞ CỬA cho phép Đăng ký & Đăng nhập (Quan trọng nhất)
//                        .requestMatchers(HttpMethod.POST, "/api/v1/users/create").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/api/v1/**").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/api/v1/suppliers/create").permitAll()
//                        .requestMatchers("/api/v1/auth/**").permitAll()
                        // 2. Các request khác thì bắt buộc phải có Token
                        .anyRequest().permitAll()
                )
                // Cấu hình Stateless (Không lưu session)
                .sessionManagement(sessionManager -> sessionManager
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );
        return http.build();
    }
}
