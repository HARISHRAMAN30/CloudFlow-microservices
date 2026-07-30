package com.cloudflow.jwt.autoconfigure;

import com.cloudflow.jwt.properties.JwtProperties;
import com.cloudflow.jwt.provider.JwtTokenProvider;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@EnableConfigurationProperties(JwtProperties.class)
public class JwtAutoConfiguration {

    @PostConstruct
    public void init() {
        System.out.println("CloudFlow JWT Starter Loaded");
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(
            prefix = "cloudflow.jwt",
            name = "enabled",
            havingValue = "true",
            matchIfMissing = true
    )
    public JwtTokenProvider jwtTokenProvider(JwtProperties jwtProperties) {
        return new JwtTokenProvider(jwtProperties);
    }

}
