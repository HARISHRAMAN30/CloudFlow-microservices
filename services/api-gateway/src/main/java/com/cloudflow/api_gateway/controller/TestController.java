package com.cloudflow.api_gateway.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/public")
    public String publicApi() {
        return "public";
    }

    @GetMapping("/private")
    public Object privateApi(Authentication authentication) {
        return authentication;
    }

}
