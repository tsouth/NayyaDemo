package com.nayya.utilities.email;

import java.util.Map;

public class EmailCredentialUtility {

    public EmailCredentialUtility() {
    }

    private Map<String, String> getCredentials(String user) {
        String username = System.getenv("nayya_USERNAME");
        String password = System.getenv("nayya_PASSWORD");

        return Map.of("username", username, "password", password);
    }

    public Map<String, String> getGMailCredentials() {
        return Map.of("email", System.getenv("nayya_EMAIL"), "password", System.getenv("nayya_GMAIL_PASSWORD"));
    }

    public String getRandomizedEmail(String identifier) {
        String email = System.getenv("SELENIUM_EMAIL");
        String[] emailSplit = email.split("@");

        return emailSplit[0] + "+" + identifier + "_" + System.currentTimeMillis() + "@" + emailSplit[1];
    }
}
