package com.cloudflow.security.config;

import com.cloudflow.jwt.provider.JwtTokenProvider;
import com.cloudflow.security.filter.JwtAuthenticationFilter;
import com.cloudflow.security.handler.JwtAccessDeniedHandler;
import com.cloudflow.security.handler.JwtAuthenticationEntryPoint;
import com.cloudflow.security.properties.SecurityProperties;
import com.cloudflow.security.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public JwtAuthenticationEntryPoint authenticationEntryPoint() {
        return new JwtAuthenticationEntryPoint();
    }

    @Bean
    public JwtAccessDeniedHandler accessDeniedHandler() {
        return new JwtAccessDeniedHandler();
    }

    @Bean
    @ConditionalOnMissingBean
    public JwtAuthenticationFilter jwtAuthenticationFilter(ObjectProvider<JwtTokenProvider> jwtTokenProvider, UserDetailsService userDetailsService) {
        JwtTokenProvider provider = jwtTokenProvider.getIfAvailable();
        if (provider == null) {
            return null;
        }
        return new JwtAuthenticationFilter(provider, userDetailsService);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, ObjectProvider<JwtAuthenticationFilter> jwtFilterProvider) throws Exception {
        if (!securityProperties.isCsrfEnabled()) {
            http.csrf(AbstractHttpConfigurer::disable);
        }

        http
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(authenticationEntryPoint())
                        .accessDeniedHandler(accessDeniedHandler())
                )
                .authorizeHttpRequests(auth -> auth
                .requestMatchers(securityProperties.getPublicUrls().toArray(new String[0]))
                .permitAll()
                .anyRequest()
                .authenticated()
        );

        JwtAuthenticationFilter jwtFilter = jwtFilterProvider.getIfAvailable();
        if (jwtFilter != null) {
            http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);}
        return http.build();
    }
}