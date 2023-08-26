package com.job.common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static com.job.common.enums.Permission.*;
import static com.job.common.enums.Role.ADMIN;
import static com.job.common.enums.Role.CONSULTANT;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable().cors().and().authorizeHttpRequests()
                .requestMatchers(
                        "/api/v1/auth/**"
                )
                .permitAll()


                .requestMatchers("/api/v1/consultant-service/**").hasAnyRole(ADMIN.name(), CONSULTANT.name())


                .requestMatchers(GET, "/api/v1/consultant-service/**").hasAnyAuthority(ADMIN_READ.name(), CONSULTANT_READ.name())
                .requestMatchers(POST, "/api/v1/consultant-service/**").hasAnyAuthority(ADMIN_CREATE.name(), CONSULTANT_CREATE.name())
                .requestMatchers(PUT, "/api/v1/consultant-service/**").hasAnyAuthority(ADMIN_UPDATE.name(), CONSULTANT_UPDATE.name())
                .requestMatchers(DELETE, "/api/v1/consultant-service/**").hasAnyAuthority(ADMIN_DELETE.name(), CONSULTANT_DELETE.name())


                /* .requestMatchers("/api/v1/admin-service/**").hasRole(ADMIN.name())

                 .requestMatchers(GET, "/api/v1/admin-service/**").hasAuthority(ADMIN_READ.name())
                 .requestMatchers(POST, "/api/v1/admin-service/**").hasAuthority(ADMIN_CREATE.name())
                 .requestMatchers(PUT, "/api/v1/admin-service/**").hasAuthority(ADMIN_UPDATE.name())
                 .requestMatchers(DELETE, "/api/v1/admin-service/**").hasAuthority(ADMIN_DELETE.name())*/


                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("/api/v1/auth/logout")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
        ;

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
