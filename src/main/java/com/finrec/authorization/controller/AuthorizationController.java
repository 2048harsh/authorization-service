package com.finrec.authorization.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.finrec.authorization.service.AuthorizationService;

import java.util.Map;

@RestController
@RequestMapping("/authorization")
public class AuthorizationController {

    private final AuthorizationService authService;

    public AuthorizationController(AuthorizationService authService) {
        this.authService = authService;
    }

    @PostMapping("/authorize")
    public ResponseEntity<?> authorize(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        String resource = request.get("resource");
        String action = request.get("action");

        boolean allowed = authService.authorize(token, resource, action);
        return ResponseEntity.ok(Map.of("allowed", allowed));
    }
}
