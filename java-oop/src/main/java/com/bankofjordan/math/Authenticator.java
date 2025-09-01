package com.bankofjordan.math;

public class Authenticator {

    public boolean authenticate(String username, String password) {
        return username.equals("admin") && password.equals("");
    }

    public boolean authenticate(Credentials credentials) {
        return authenticate(credentials.username, credentials.password);
    }
}
