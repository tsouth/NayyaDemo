package com.wellsaid.utilities.email;

import java.util.Map;

public class EmailCredentialUtility {

    public EmailCredentialUtility() {
    }

    public Map<String, String> getTrialUserCredentials() {
        return getCredentials("trialuser");
    }

    private Map<String, String> getCredentials(String user) {
        String email = System.getenv("WELLSAID_EMAIL");
        String[] splitEmail = email.split("@");
        email = splitEmail[0] + "+" + user + "@" + splitEmail[1];

        String password = System.getenv("WELLSAID_ACCOUNTS_PASSWORD");

        return Map.of("email", email, "password", password);
    }

    public Map<String, String> getGMailCredentials() {
        return Map.of("email", System.getenv("WELLSAID_EMAIL"), "password", System.getenv("WELLSAID_GMAIL_PASSWORD"));
    }
}
