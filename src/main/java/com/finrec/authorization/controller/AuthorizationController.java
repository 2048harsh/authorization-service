package com.finrec.authorization.controller;

import com.finrec.authorization.dto.AuthorizationRequest;
import com.finrec.authorization.dto.AuthorizationResponse;
import com.finrec.authorization.service.PolicyEvaluationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class AuthorizationController {

    private final PolicyEvaluationService policyEvaluationService;

    public AuthorizationController(PolicyEvaluationService policyEvaluationService) {
        this.policyEvaluationService = policyEvaluationService;
    }

    @PostMapping("/authorize")
    public ResponseEntity<AuthorizationResponse> authorize(@RequestBody AuthorizationRequest request) {
        boolean isAllowed = policyEvaluationService.evaluate(request);

        if (isAllowed) {
            return ResponseEntity.ok(new AuthorizationResponse(true, "Access Granted"));
        } else {
            return ResponseEntity.ok(new AuthorizationResponse(false, "Policy Violation: Insufficient Permissions"));
        }
    }
}
