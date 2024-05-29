package com.tutorial.spring.infrastucture.security.configuration;

import com.tutorial.spring.domain.property.service.PropertyService;
import com.tutorial.spring.infrastucture.security.authentication.MyUserDetailsService;
import com.tutorial.spring.infrastucture.security.authorization.AuthorizationService;
import com.tutorial.spring.infrastucture.security.authorization.AuthorizationServiceImpl;
import com.tutorial.spring.infrastucture.security.filter.JwtRequestFilter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.CustomAutowireConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private PropertyService propertyService;

    @Bean
    public AuthenticationProvider authProvider(MyUserDetailsService userDetailService,
        PasswordEncoder bCryptPasswordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailService);
        authProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type", "Access-Control-Allow-Origin"));
        corsConfiguration.setAllowedOrigins(List.of("https://emil-proekt.popetsmaster.com"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE","OPTIONS","PATCH", "DELETE"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setExposedHeaders(List.of("Authorization","Cache-Control", "Content-Type", "Access-Control-Allow-Origin"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        http.cors((cors) -> cors
            .configurationSource(source))
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(
                authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
                    .requestMatchers("/authenticate")
                    .permitAll()
                    .requestMatchers("/users/all")
                    .permitAll()
                    .requestMatchers("/users/{id}")
                    .permitAll()
                    .requestMatchers("/images/**")
                    .permitAll()
                    .requestMatchers("/properties/all")
                    .permitAll()
                    .requestMatchers("/properties/{id}")
                    .permitAll()
                    .requestMatchers("/users/register")
                    .permitAll()
                    .requestMatchers("*/register")
                    .permitAll()
                    .anyRequest()
                    .authenticated())
            .exceptionHandling(Customizer.withDefaults()).httpBasic(Customizer.withDefaults())
            .headers(Customizer.withDefaults())
            .sessionManagement(
                httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS));

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthorizationService authorizationService() {
        return new AuthorizationServiceImpl(propertyService);
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/api/auth/**");
    }

}
