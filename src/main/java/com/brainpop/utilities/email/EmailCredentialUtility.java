package com.brainpop.utilities.email;

import java.util.Map;

public class EmailCredentialUtility {

    public EmailCredentialUtility() {
    }

    public Map<String, String> getKidCredentials() {
        return getCredentials("kidUser");
    }

    private Map<String, String> getCredentials(String user) {
        String username = System.getenv("BRAINPOP_USERNAME");
        String password = System.getenv("BRAINPOP_PASSWORD");

        return Map.of("username", username, "password", password);
    }

    public Map<String, String> getGMailCredentials() {
        return Map.of("email", System.getenv("BRAINPOP_EMAIL"), "password", System.getenv("BRAINPOP_GMAIL_PASSWORD"));
    }
}
