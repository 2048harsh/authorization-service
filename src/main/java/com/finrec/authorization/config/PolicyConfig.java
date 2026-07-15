package com.finrec.authorization.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "policies")
public class PolicyConfig {
    private List<Policy> policies;

    public List<Policy> getPolicies() { return policies; }
    public void setPolicies(List<Policy> policies) { this.policies = policies; }

    public static class Policy {
        private String resource;
        private String action;
        private List<String> roles;

        public String getResource() { return resource; }
        public void setResource(String resource) { this.resource = resource; }

        public String getAction() { return action; }
        public void setAction(String action) { this.action = action; }

        public List<String> getRoles() { return roles; }
        public void setRoles(List<String> roles) { this.roles = roles; }
    }
}
