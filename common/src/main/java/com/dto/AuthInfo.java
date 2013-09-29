package com.dto;


import java.io.Serializable;

public class AuthInfo implements Serializable {
    private String login;
    private String password;
    private boolean auth;

    public AuthInfo(String login, String password) {
        this.login = login;
        this.password = password;
        auth = false;

    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    public boolean getAuth() {
        return auth;
    }

}
