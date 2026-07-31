package com.finrec.authorization.dto;

public class AuthorizationResponse {
    private boolean allowed;
    private String reason;

    public AuthorizationResponse(boolean allowed, String reason) {
        this.allowed = allowed;
        this.reason = reason;
    }

    public boolean isAllowed() { return allowed; }
    public String getReason() { return reason; }
}