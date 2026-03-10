// ini congigurasi untuk menghash password pengguna yang melakukan registrasi
// mengunakan spring-boor-starter-security

package com.Xuerns.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.Xuerns.backend.security.JwtAuthenticationFilter;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public PasswordEncoder PasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Matikan CSRF (Cross-Site-Request Forgery) karena kita buat REST API
                .csrf(csrf -> csrf.disable())

                // Atur Izin Akses ke endpoint (register)
                .authorizeHttpRequests(auth -> auth.requestMatchers("/api/users/register", "/api/users/login")
                        .permitAll().anyRequest().authenticated())
                // setting bahwa kita pakai token bukan sesion
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // pasang filter token
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
