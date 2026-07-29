package com.cloudflow.web.autoconfigure;

import com.cloudflow.web.exception.GlobalExceptionHandler;
import com.cloudflow.web.filter.CorrelationIdFilter;
import com.cloudflow.web.filter.RequestLoggingFilter;
import com.cloudflow.web.properties.WebProperties;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableConfigurationProperties(WebProperties.class)
@Import(GlobalExceptionHandler.class)
public class WebAutoConfiguration {

    @PostConstruct
    public void init() {
        System.out.println("CloudFlow Web Starter Loaded");
    }

    @Bean
    @ConditionalOnProperty(
            prefix = "cloudflow.web.correlation-id",
            name = "enabled",
            havingValue = "true",
            matchIfMissing = true
    )
    public CorrelationIdFilter correlationIdFilter(WebProperties webProperties) {
        return new CorrelationIdFilter(webProperties);
    }

    @Bean
    @ConditionalOnProperty(
            prefix = "cloudflow.web.request-logging",
            name = "enabled",
            havingValue = "true",
            matchIfMissing = true
    )
    public RequestLoggingFilter requestLoggingFilter(WebProperties webProperties) {
        return new RequestLoggingFilter(webProperties);
    }
}
