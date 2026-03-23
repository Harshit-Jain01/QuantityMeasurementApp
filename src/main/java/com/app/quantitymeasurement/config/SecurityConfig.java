package com.app.quantitymeasurement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * SecurityConfig
 *
 * Spring Security configuration for the Quantity Measurement application.
 *
 * For this UC all requests are permitted without authentication, allowing development
 * and testing to proceed without credential management. In a production environment,
 * replace the open {@code permitAll()} rule with role-based access control using
 * mechanisms such as JWT tokens or OAuth2.
 *
 * Key settings:
 * <ul>
 *   <li><b>CSRF disabled</b> — stateless REST APIs do not require CSRF tokens.</li>
 *   <li><b>All requests permitted</b> — no authentication required.</li>
 *   <li><b>Frame options: sameOrigin</b> — required for the H2 console to render
 *       correctly in a browser, since it uses an iframe internally.</li>
 *   <li><b>HTTP Basic and form login disabled</b> — prevents unwanted browser
 *       authentication popups when accessing API endpoints.</li>
 * </ul>
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configures the application-wide HTTP security filter chain.
     *
     * @param http the {@link HttpSecurity} builder
     * @return the configured {@link SecurityFilterChain}
     * @throws Exception if the security configuration fails
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(
        		auth -> auth.anyRequest()
        					.permitAll()
            )
            .headers(
            	headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
            )
            .httpBasic(AbstractHttpConfigurer::disable)
            .formLogin(AbstractHttpConfigurer::disable);

        return http.build();
    }
}