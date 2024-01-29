package com.example.payflow.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
<<<<<<< Updated upstream

/**
 * Konfiguracja zabezpieczeń aplikacji przy użyciu Spring Security.
=======
/**
 * Configuration class for security settings.
>>>>>>> Stashed changes
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {


    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
<<<<<<< Updated upstream

    /**
     * Konfiguruje łańcuch filtrów bezpieczeństwa dla aplikacji.
     *
     * @param httpSecurity Obiekt konfiguracji zabezpieczeń HTTP.
     * @return Łańcuch filtrów bezpieczeństwa.
     * @throws Exception Jeśli wystąpi błąd podczas konfiguracji zabezpieczeń.
=======
    /**
     * Configures security filter chain.
     *
     * @param httpSecurity HttpSecurity object to configure security settings
     * @return SecurityFilterChain object representing the configured security filter chain
     * @throws Exception If an error occurs during configuration
>>>>>>> Stashed changes
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/swagger-resources/**",
                                "/v3/api-docs/**")
                        .permitAll()
                        .anyRequest()
                        .permitAll()
                )
                .cors(Customizer.withDefaults())
                .sessionManagement(sess -> sess
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
