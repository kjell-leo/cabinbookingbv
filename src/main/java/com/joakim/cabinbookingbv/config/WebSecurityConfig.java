package com.joakim.cabinbookingbv.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin")) // Store password securely
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }

    // Password encoder to encrypt the password (used by Spring Security)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // You can choose another encoder if needed
    }

    // Configure HTTP Security (which defines which endpoints require authentication)
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**").permitAll()
                        .requestMatchers("/api/admin/**").authenticated()
                )
                .httpBasic(withDefaults());

        return http.build();
    }
}
