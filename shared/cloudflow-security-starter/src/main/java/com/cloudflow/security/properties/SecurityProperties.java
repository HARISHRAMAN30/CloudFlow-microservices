package com.cloudflow.security.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ConfigurationProperties(prefix = "cloudflow.security")
public class SecurityProperties {

    /**
     * Master switch for the security starter.
     */
    private boolean enabled = true;

    /**
     * Enable authentication.
     */
    private boolean authenticationEnabled = true;

    /**
     * Enable CSRF protection.
     */
    private boolean csrfEnabled = true;

    /**
     * Enable CORS.
     */
    private boolean corsEnabled = true;

    /**
     * Stateless sessions (JWT).
     */
    private boolean stateless = true;

    /**
     * URLs accessible without authentication.
     */
    private List<String> publicUrls = new ArrayList<>();
}
