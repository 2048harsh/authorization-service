package com.finrec.authorization.service;


import com.finrec.authorization.dto.AuthorizationRequest;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PolicyEvaluationService {

    public boolean evaluate(AuthorizationRequest request) {
        List<String> userRoles = Arrays.asList(request.getRoles().split(","));
        String path = request.getResource();
        String method = request.getAction();

        // 1. ADMINs have full access across all endpoints
        if (userRoles.contains("ROLE_ADMIN")) {
            return true;
        }

        // 2. Account Master Service Rules
        if (path.startsWith("/api/v1/masters/account-master")) {
            // Read actions permitted for USER, ANALYST, and ADMIN
            if ("GET".equalsIgnoreCase(method)) {
                return userRoles.contains("ROLE_USER") || userRoles.contains("ROLE_ANALYST");
            }
            // Mutation actions (POST, PUT, DELETE) restricted to ANALYST and ADMIN
            if ("POST".equalsIgnoreCase(method) || "PUT".equalsIgnoreCase(method) || "DELETE".equalsIgnoreCase(method)) {
                return userRoles.contains("ROLE_ANALYST");
            }
        }

        // 3. Manual Recon Service Rules
        if (path.startsWith("/api/v1/operations/manual-recon")) {
            return userRoles.contains("ROLE_ANALYST");
        }

        // Default Deny (Zero-Trust)
        return false;
    }
}