package com.finrec.authorization.security;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.security.KeyFactory;
import java.util.Base64;

@Component
public class PublicKeyProvider {
    private final RestTemplate restTemplate = new RestTemplate();
    private PublicKey cachedKey;

    public PublicKey getPublicKey(String url) throws Exception {
        if (cachedKey == null) {
            var response = restTemplate.getForObject(url, PublicKeyResponse.class);
            byte[] decoded = Base64.getDecoder().decode(response.getKey());
            X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
            cachedKey = KeyFactory.getInstance("RSA").generatePublic(spec);
        }
        return cachedKey;
    }

    static class PublicKeyResponse {
        private String key;
        public String getKey() { return key; }
        public void setKey(String key) { this.key = key; }
    }
}
