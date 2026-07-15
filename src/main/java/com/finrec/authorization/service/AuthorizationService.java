package com.finrec.authorization.service;

import com.finrec.authorization.config.PolicyConfig;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorizationService {

    private final PolicyConfig policyConfig;

    public AuthorizationService(PolicyConfig policyConfig) {
        this.policyConfig = policyConfig;
    }

    public boolean authorize(String token, String resource, String action) {
        // TODO: Validate JWT with public key and extract roles
        List<String> userRoles = extractRolesFromToken(token);

        return policyConfig.getPolicies().stream()
                .filter(p -> p.getResource().equals(resource) && p.getAction().equals(action))
                .anyMatch(p -> userRoles.stream().anyMatch(p.getRoles()::contains));
    }

    private List<String> extractRolesFromToken(String token) {
        // Placeholder: parse JWT and return roles
        return List.of("ADMIN"); // demo
    }
}

