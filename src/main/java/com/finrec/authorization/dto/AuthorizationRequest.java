package com.finrec.authorization.dto;

public class AuthorizationRequest {
    private String userId;
    private String roles;
    private String resource;
    private String action;

    public AuthorizationRequest() {}

    public AuthorizationRequest(String userId, String roles, String resource, String action) {
        this.userId = userId;
        this.roles = roles;
        this.resource = resource;
        this.action = action;
    }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getRoles() { return roles; }
    public void setRoles(String roles) { this.roles = roles; }

    public String getResource() { return resource; }
    public void setResource(String resource) { this.resource = resource; }

    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }
}