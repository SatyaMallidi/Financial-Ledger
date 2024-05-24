package com.example.dataworks.financialledger.Security;

import com.example.dataworks.financialledger.Filter.JwtAuthenticationFilter;
import com.example.dataworks.financialledger.service.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private JwtAuthenticationFilter jwtRequestFilter;
    private UserDetailsServiceImpl userDetailsServiceImpl;
    private CustomAccessDeniedHandler accessDeniedHandler;
    
    public SecurityConfig(JwtAuthenticationFilter jwtRequestFilter, UserDetailsServiceImpl userDetailsServiceImpl,
            CustomAccessDeniedHandler accessDeniedHandler) {
        this.jwtRequestFilter = jwtRequestFilter;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.accessDeniedHandler = accessDeniedHandler;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
        .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login/**","/register/**", "/refresh_token/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/public/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/public/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/public/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/public/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/user/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/user/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/user/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/user/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                ).userDetailsService(userDetailsServiceImpl)
                .exceptionHandling(e->e.accessDeniedHandler(accessDeniedHandler)
                   .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
