package com.cloudflow.jwt.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "cloudflow.jwt")
@Getter
@Setter
public class JwtProperties {

    private Boolean enabled;

    private String secret;

    private long accessTokenExpiration = 900000;

    private long refreshTokenExpiration = 604800000;

    private String issuer = "cloudflow";
}
