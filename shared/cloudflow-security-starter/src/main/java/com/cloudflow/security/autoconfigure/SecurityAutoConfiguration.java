package com.cloudflow.security.autoconfigure;

import com.cloudflow.security.config.SecurityConfiguration;
import com.cloudflow.security.properties.SecurityProperties;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@EnableConfigurationProperties(SecurityProperties.class)
@Import(SecurityConfiguration.class)
public class SecurityAutoConfiguration {

    @PostConstruct
    public void init() {
        System.out.println("CloudFlow Security Starter Loaded");
    }

}
