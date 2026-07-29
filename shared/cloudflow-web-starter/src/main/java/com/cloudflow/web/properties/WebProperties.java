package com.cloudflow.web.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "cloudflow.web")
@Getter
@Setter
public class WebProperties {

    private final CorrelationId correlationId = new CorrelationId();
    private final RequestLogging requestLogging =  new RequestLogging();
    private List<String> excludePaths = new ArrayList<>();

    @Setter
    @Getter
    public static class CorrelationId {
        private boolean enabled = true;
    }

    @Setter
    @Getter
    public static class RequestLogging {
        private boolean enabled = true;
    }
}
