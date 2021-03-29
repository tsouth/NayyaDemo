package com.mindr.utilities.email;

import com.mindr.utilities.managers.PageManager;

import java.util.Map;

public class EmailCredentialUtility {

    public EmailCredentialUtility() {}

    public Map<String, String> getOrgAdminCredentials() {
        return getCredentials("testorgadmin");
    }

    public Map<String, String> getEmployeeCredentials() {
        return getCredentials("testemployee");
    }

    private Map<String, String> getCredentials(String user) {
        String email = System.getenv("MINDRQA_EMAIL");
        String[] splitEmail = email.split("@");
        email = splitEmail[0] + "+" + user + "@" + splitEmail[1];

        String password = System.getenv("MINDRQA_ACCOUNTS_PASSWORD");

        return Map.of(
                "email", email,
                "password", password
        );
    }
}
