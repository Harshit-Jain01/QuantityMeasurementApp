package com.app.quantitymeasurement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.app.quantitymeasurement.service.CustomOidcUserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomOidcUserService customOidcUserService;

    @Autowired
    private OAuthSuccessHandler successHandler;

    @Autowired
    private JwtFilter jwtFilter;

    public SecurityConfig(CustomOidcUserService customOidcUserService) {
        this.customOidcUserService = customOidcUserService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // ✅ disable csrf
            .csrf(AbstractHttpConfigurer::disable)

            // ✅ enable cors (VERY IMPORTANT)
            .cors(cors -> {})

            // ✅ authorize requests
            .authorizeHttpRequests(auth -> auth

                // ✅ allow preflight requests (MOST IMPORTANT FIX)
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                // ✅ public endpoints
                .requestMatchers(
                    "/h2-console/**",
                    "/api/**",
                    "/auth/**",
                    "/login/**",
                    "/oauth2/**",
                    "/v3/api-docs/**",
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/actuator/**"
                ).permitAll()

                // ✅ secure others
                .anyRequest().authenticated()
            )

            // ✅ OAuth login
            .oauth2Login(oauth -> oauth
                .userInfoEndpoint(userInfo -> userInfo
                    .oidcUserService(customOidcUserService)
                )
                .successHandler(successHandler)
            );

        // ✅ JWT filter
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}