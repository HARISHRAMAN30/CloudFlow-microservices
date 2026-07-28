package com.cloudflow.api_gateway.controller;

import com.cloudflow.common.response.ApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/system")
public class SystemController {

    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping("/info")
    public ApiResponse<Map<String, Object>> getSystemInfo() {
        return ApiResponse.success("Gateway is running",
                                    Map.of("applicationName", applicationName,
                                            "status", "UP",
                                            "env", "local",
                                            "version", "1.0.0")

        );
    }
}
