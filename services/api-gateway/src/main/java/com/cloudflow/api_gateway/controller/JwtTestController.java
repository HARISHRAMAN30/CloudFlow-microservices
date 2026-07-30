package com.cloudflow.api_gateway.controller;

import com.cloudflow.jwt.provider.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jwt")
public class JwtTestController {

    private final JwtTokenProvider jwtTokenProvider;

    public JwtTestController(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("/token")
    public String token() {
        return jwtTokenProvider.generateAccessToken("harish");
    }

    @GetMapping("/validate")
    public boolean validate(@RequestParam String token) {
        return jwtTokenProvider.validateAccessToken(token);
    }

    @GetMapping("/claims")
    public Claims claims(@RequestParam String token) {
        return jwtTokenProvider.extractClaims(token);
    }

    @GetMapping("/username")
    public String username(@RequestParam String token) {
        return jwtTokenProvider.extractUsername(token);
    }
}
