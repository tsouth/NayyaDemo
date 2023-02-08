package com.songTradr.utilities.email;

import java.util.Map;

public class EmailCredentialUtility {

    public EmailCredentialUtility() {
    }

    public Map<String, String> getOrgAdminCredentials() {
        return getCredentials("orgadmin");
    }

    public Map<String, String> getEmployeeCredentials() {
        return getCredentials("employee");
    }

    private Map<String, String> getCredentials(String user) {
        String email = System.getenv("SONGTRADR_EMAIL");
        String[] splitEmail = email.split("@");
        email = splitEmail[0] + "+" + user + "@" + splitEmail[1];

        String password = System.getenv("SONGTRADR_ACCOUNTS_PASSWORD");

        return Map.of("email", email, "password", password);
    }

    public Map<String, String> getGMailCredentials() {
        return Map.of("email", System.getenv("SONGTRADR_EMAIL"), "password", System.getenv("SONGTRADR_GMAIL_PASSWORD"));
    }
}
